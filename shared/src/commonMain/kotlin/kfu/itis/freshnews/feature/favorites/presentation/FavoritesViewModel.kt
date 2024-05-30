package kfu.itis.freshnews.feature.favorites.presentation

import kfu.itis.freshnews.core.di.PlatformSDK
import kfu.itis.freshnews.core.firebase.FirebaseAnalyticsBinding
import kfu.itis.freshnews.core.firebase.FirebaseCrashlyticsBinding
import kfu.itis.freshnews.core.viewmodel.BaseViewModel
import kfu.itis.freshnews.feature.favorites.domain.usecase.GetAllFavoritesArticlesUseCase
import kotlinx.coroutines.launch

class FavoritesViewModel : BaseViewModel<FavoritesState, FavoritesAction, FavoritesEvent>(
    initialState = FavoritesState(),
) {

    private val firebaseCrashlyticsBinding: FirebaseCrashlyticsBinding by PlatformSDK.lazyInstance()
    private val firebaseAnalyticsBinding: FirebaseAnalyticsBinding by PlatformSDK.lazyInstance()
    private val getAllFavoritesArticleUseCase: GetAllFavoritesArticlesUseCase by PlatformSDK.lazyInstance()

    override fun handleEvent(event: FavoritesEvent) = when (event) {
        is FavoritesEvent.OnArticleClick -> onArticleClick(event.id)
    }

    init {
        loadFavoriteArticles()
        logOpenScreen()
    }

    private fun loadFavoriteArticles() {
        scope.launch {
            try {
                getAllFavoritesArticleUseCase()
                    .collect { favorites ->
                        state = state.copy(favoritesArticles = favorites)
                    }
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun onArticleClick(id: Int) {
        action = FavoritesAction.NavigateDetails(id)
    }

    private fun logOpenScreen() {
        firebaseAnalyticsBinding.logOpenScreen("favorites_screen")
    }

    private fun handleError(e: Throwable) {
        state = state.copy(error = e)
        action = FavoritesAction.ShowError("Oops, something went wrong")
        firebaseCrashlyticsBinding.sendNonFatalErrorReport(e)
    }
}
