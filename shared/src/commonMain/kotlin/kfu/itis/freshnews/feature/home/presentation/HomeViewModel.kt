package kfu.itis.freshnews.feature.home.presentation

import kfu.itis.freshnews.core.di.PlatformSDK
import kfu.itis.freshnews.core.firebase.FirebaseAnalyticsBinding
import kfu.itis.freshnews.core.firebase.FirebaseCrashlyticsBinding
import kfu.itis.freshnews.core.viewmodel.BaseViewModel
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory
import kfu.itis.freshnews.feature.home.domain.usecase.GetTopHeadlinesByCategoryUseCase
import kfu.itis.freshnews.feature.home.domain.usecase.GetTopHeadlinesUseCase
import kfu.itis.freshnews.feature.home.domain.usecase.SearchTopHeadlinesByPhraseUseCase
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel<HomeState, HomeAction, HomeEvent>(
    initialState = HomeState(),
) {

    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase by PlatformSDK.lazyInstance()
    private val searchTopHeadlinesByPhraseUseCase: SearchTopHeadlinesByPhraseUseCase by PlatformSDK.lazyInstance()
    private val getTopHeadlinesByCategoryUseCase: GetTopHeadlinesByCategoryUseCase by PlatformSDK.lazyInstance()
    private val firebaseCrashlyticsBinding: FirebaseCrashlyticsBinding by PlatformSDK.lazyInstance()
    private val firebaseAnalyticsBinding: FirebaseAnalyticsBinding by PlatformSDK.lazyInstance()

    override fun handleEvent(event: HomeEvent) = when (event) {
        is HomeEvent.OnArticleClick -> onArticleClick(event.title)
        is HomeEvent.OnQueryChange -> onQueryChange(event.query)
        is HomeEvent.OnActiveChange -> onActiveChange(event.isSearchActive)
        is HomeEvent.OnArticleCategoryClick -> onArticleCategoryClick(event.category)
        is HomeEvent.OnSearch -> onSearch(event.query)
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
                state = state.copy(latestArticles = latestArticles)
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
                state = state.copy(articlesOfCategory = articlesOfCategory)
            } catch (e: Throwable) {
                handleError(e)
            } finally {
                state = state.copy(isArticlesOfCategoryLoading = false)
            }
        }
    }

    private fun onArticleClick(title: String) {
        action = HomeAction.NavigateDetails(title)
    }

    private fun onQueryChange(query: String) {
        if (query.isEmpty()) state = state.copy(searchedArticles = emptyList())
        state = state.copy(searchQuery = query)
    }

    private fun onActiveChange(isSearchActive: Boolean) {
        state = state.copy(isSearchActive = isSearchActive)
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

    private fun onSearch(query: String) {
        scope.launch {
            try {
                state = state.copy(isSearchedArticlesLoading = true)
                val searchedArticles = searchTopHeadlinesByPhraseUseCase(query)
                state = state.copy(
                    searchQuery = query,
                    searchedArticles = searchedArticles,
                )
            } catch (e: Throwable) {
                handleError(e)
            } finally {
                state = state.copy(isSearchedArticlesLoading = false)
            }
        }
    }

    private fun logOpenScreen() {
        firebaseAnalyticsBinding.logOpenScreen("home_screen")
    }

    private fun handleError(e: Throwable) {
        state = state.copy(error = e)
        action = HomeAction.ShowError("Oops, something went wrong")
        firebaseCrashlyticsBinding.sendNonFatalErrorReport(e)
    }
}
