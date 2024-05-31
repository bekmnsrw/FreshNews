package kfu.itis.freshnews.feature.favorites.presentation

import kfu.itis.freshnews.core.di.PlatformSDK
import kfu.itis.freshnews.core.firebase.FirebaseAnalyticsBinding
import kfu.itis.freshnews.core.firebase.FirebaseCrashlyticsBinding
import kfu.itis.freshnews.core.viewmodel.BaseViewModel
import kfu.itis.freshnews.feature.auth.domain.usecase.GetUserIdUseCase
import kfu.itis.freshnews.feature.favorites.domain.usecase.GetAllFavoritesArticlesUseCase
import kotlinx.coroutines.launch

class FavoritesViewModel : BaseViewModel<FavoritesState, FavoritesAction, FavoritesEvent>(
    initialState = FavoritesState(),
) {

    private val firebaseCrashlyticsBinding: FirebaseCrashlyticsBinding by PlatformSDK.lazyInstance()
    private val firebaseAnalyticsBinding: FirebaseAnalyticsBinding by PlatformSDK.lazyInstance()
    private val getAllFavoritesArticleUseCase: GetAllFavoritesArticlesUseCase by PlatformSDK.lazyInstance()
    private val getUserIdUseCase: GetUserIdUseCase by PlatformSDK.lazyInstance()

    override fun handleEvent(event: FavoritesEvent) = when (event) {
        is FavoritesEvent.OnArticleClick -> onArticleClick(event.id)
        FavoritesEvent.OnAuthButtonClick -> onAuthButtonClick()
    }

    init {
        loadFavoriteArticles()
        logOpenScreen()
    }

    private fun loadFavoriteArticles() {
        scope.launch {
            try {
                val userId = getUserIdUseCase()
                if (userId == null) {
                    state = state.copy(isUserAuthenticated = false)
                } else {
                    getAllFavoritesArticleUseCase(userId)
                        .collect { favorites ->
                            state = state.copy(
                                favoritesArticles = favorites,
                                isUserAuthenticated = true,
                            )
                        }
                }
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun onArticleClick(id: Long) {
        action = FavoritesAction.NavigateDetails(id)
    }

    private fun onAuthButtonClick() {
        action = FavoritesAction.NavigateAuth
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
