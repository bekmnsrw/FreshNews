package kfu.itis.freshnews.feature.home.presentation

import kfu.itis.freshnews.core.di.PlatformSDK
import kfu.itis.freshnews.core.firebase.FirebaseCrashlyticsBinding
import kfu.itis.freshnews.core.viewmodel.BaseViewModel
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

    override fun handleEvent(event: HomeEvent) = when (event) {
        HomeEvent.OnInit -> onInit()
        is HomeEvent.OnArticleClick -> onArticleClick(event.name)
        is HomeEvent.OnQueryChange -> onQueryChange(event.query)
    }

    private fun onInit() {
        scope.launch {
            try {
                val latestArticles = getTopHeadlinesUseCase()
                val articlesByCategory = getTopHeadlinesByCategoryUseCase(state.selectedArticleCategory)
                state = state.copy(
                    latestArticles = latestArticles,
                    articlesByCategory = articlesByCategory,
                )
            } catch (error: Throwable) {
                state = state.copy(error = error)
                action = HomeAction.ShowError("Oops, something went wrong")
                firebaseCrashlyticsBinding.sendNonFatalErrorReport(error)
            } finally {
                state = state.copy(isLoading = false)
            }
        }
    }

    private fun onArticleClick(name: String) {
        scope.launch {
            try {
                action = HomeAction.NavigateDetails(name)
            } catch (error: Throwable) {
                state = state.copy(error = error)
                action = HomeAction.ShowError("Oops, something went wrong")
                firebaseCrashlyticsBinding.sendNonFatalErrorReport(error)
            }
        }
    }

    private fun onQueryChange(query: String) {
        scope.launch {
            try {
                state = state.copy(searchQuery = query)
            } catch (error: Throwable) {
                state = state.copy(error = error)
                action = HomeAction.ShowError("Oops, something went wrong")
                firebaseCrashlyticsBinding.sendNonFatalErrorReport(error)
            }
        }
    }
}
