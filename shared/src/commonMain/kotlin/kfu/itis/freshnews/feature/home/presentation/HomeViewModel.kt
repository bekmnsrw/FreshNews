package kfu.itis.freshnews.feature.home.presentation

import kfu.itis.freshnews.core.di.PlatformSDK
import kfu.itis.freshnews.core.firebase.FirebaseAnalyticsBinding
import kfu.itis.freshnews.core.firebase.FirebaseCrashlyticsBinding
import kfu.itis.freshnews.core.viewmodel.BaseViewModel
import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory
import kfu.itis.freshnews.feature.home.domain.usecase.GetTopHeadlinesByCategoryUseCase
import kfu.itis.freshnews.feature.home.domain.usecase.GetTopHeadlinesUseCase
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel<HomeState, HomeAction, HomeEvent>(
    initialState = HomeState(),
) {

    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase by PlatformSDK.lazyInstance()
    private val getTopHeadlinesByCategoryUseCase: GetTopHeadlinesByCategoryUseCase by PlatformSDK.lazyInstance()
    private val firebaseCrashlyticsBinding: FirebaseCrashlyticsBinding by PlatformSDK.lazyInstance()
    private val firebaseAnalyticsBinding: FirebaseAnalyticsBinding by PlatformSDK.lazyInstance()

    override fun handleEvent(event: HomeEvent) = when (event) {
        is HomeEvent.OnArticleClick -> onArticleClick(event.article)
        is HomeEvent.OnArticleCategoryClick -> onArticleCategoryClick(event.category)
        HomeEvent.OnOpenWiFiSettingsClick -> onOpenWiFiSettingsClick()
        HomeEvent.ReloadNews -> reloadNews()
    }

    init {
        loadLatestArticles()
        loadArticlesOfCategory()
        logOpenScreen()
    }

    private fun loadLatestArticles() {
        scope.launch {
            try {
                state = state.copy(isLatestArticlesLoading = true)
                val latestArticles = getTopHeadlinesUseCase()
                state = state.copy(
                    latestArticles = latestArticles,
                    error = null,
                )
            } catch (e: Throwable) {
                handleError(e)
            } finally {
                state = state.copy(isLatestArticlesLoading = false)
            }
        }
    }

    private fun loadArticlesOfCategory() {
        scope.launch {
            try {
                state = state.copy(isArticlesOfCategoryLoading = true)
                val articlesOfCategory = getTopHeadlinesByCategoryUseCase(state.selectedArticleCategory)
                state = state.copy(
                    articlesOfCategory = articlesOfCategory,
                    error = null,
                )
            } catch (e: Throwable) {
                handleError(e)
            } finally {
                state = state.copy(isArticlesOfCategoryLoading = false)
            }
        }
    }

    private fun onArticleClick(article: Article) {
        action = HomeAction.NavigateDetails(article)
    }

    private fun onArticleCategoryClick(category: ArticleCategory) {
        scope.launch {
            try {
                state = state.copy(selectedArticleCategory = category)
                loadArticlesOfCategory()
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun onOpenWiFiSettingsClick() {
        action = HomeAction.OpenWiFiSettings
    }

    private fun reloadNews() {
        loadLatestArticles()
        loadArticlesOfCategory()
    }

    private fun logOpenScreen() {
        firebaseAnalyticsBinding.logOpenScreen("home_screen")
    }

    private fun handleError(e: Throwable) {
        state = state.copy(error = e)
        action = HomeAction.ShowError
        firebaseCrashlyticsBinding.sendNonFatalErrorReport(e)
    }
}
