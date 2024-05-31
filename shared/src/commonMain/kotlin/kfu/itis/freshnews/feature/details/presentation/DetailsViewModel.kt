package kfu.itis.freshnews.feature.details.presentation

import kfu.itis.freshnews.core.di.PlatformSDK
import kfu.itis.freshnews.core.firebase.FirebaseAnalyticsBinding
import kfu.itis.freshnews.core.firebase.FirebaseCrashlyticsBinding
import kfu.itis.freshnews.core.viewmodel.BaseViewModel
import kfu.itis.freshnews.feature.auth.domain.usecase.GetUserIdUseCase
import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails
import kfu.itis.freshnews.feature.favorites.data.mapper.toArticleDetails
import kfu.itis.freshnews.feature.favorites.domain.usecase.AddFavoritesArticleUseCase
import kfu.itis.freshnews.feature.favorites.domain.usecase.GetFavoritesArticleByIdUseCase
import kfu.itis.freshnews.feature.favorites.domain.usecase.GetFavoritesArticleByTitleUseCase
import kfu.itis.freshnews.feature.favorites.domain.usecase.RemoveFavoritesArticleUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DetailsViewModel : BaseViewModel<DetailsState, DetailsAction, DetailsEvent>(
    initialState = DetailsState(),
) {

    private val firebaseCrashlyticsBinding: FirebaseCrashlyticsBinding by PlatformSDK.lazyInstance()
    private val addFavoritesArticleUseCase: AddFavoritesArticleUseCase by PlatformSDK.lazyInstance()
    private val getFavoritesArticleByTitleUseCase: GetFavoritesArticleByTitleUseCase by PlatformSDK.lazyInstance()
    private val firebaseAnalyticsBinding: FirebaseAnalyticsBinding by PlatformSDK.lazyInstance()
    private val getFavoritesArticleByIdUseCase: GetFavoritesArticleByIdUseCase by PlatformSDK.lazyInstance()
    private val removeFavoritesArticleUseCase: RemoveFavoritesArticleUseCase by PlatformSDK.lazyInstance()
    private val getUserIdUseCase: GetUserIdUseCase by PlatformSDK.lazyInstance()

    override fun handleEvent(event: DetailsEvent) = when (event) {
        is DetailsEvent.OnInit -> onInit(event.articleDetails, event.favoriteArticleId)
        DetailsEvent.OnAddToFavoritesClick -> onAddToFavoritesClick()
        DetailsEvent.OnArrowBackClick -> onArrowBackClick()
    }

    private fun onInit(
        articleDetails: ArticleDetails?,
        favoriteArticleId: Long?,
    ) {
        scope.launch {
            try {
                logOpenScreen()
                val userId = getUserIdUseCase()
                articleDetails?.let { article ->
                    val isFavorite = if (userId != null) {
                        getFavoritesArticleByTitleUseCase(article.title, userId).first() != null
                    } else {
                        false
                    }
                    state = state.copy(
                        isFavorite = isFavorite,
                        articleDetails = article,
                        isUserAuthenticated = true,
                    )
                }
                favoriteArticleId?.let { articleId ->
                    if (userId != null) {
                        val favoritesArticle = getFavoritesArticleByIdUseCase(articleId, userId).first()
                        state = state.copy(
                            articleDetails = favoritesArticle.toArticleDetails(),
                            isFavorite = true,
                            isUserAuthenticated = true
                        )
                    } else {
                        state = state.copy(
                            isUserAuthenticated = false,
                            isFavorite = false,
                        )
                    }
                }
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun onAddToFavoritesClick() {
        scope.launch {
            try {
                val userId = getUserIdUseCase()
                if (userId != null) {
                    state.articleDetails?.let { article ->
                        if (state.isFavorite) {
                            removeFavoritesArticleUseCase(article.title, userId)
                            firebaseAnalyticsBinding.logRemovingFromFavorites(article.title, userId)
                        } else {
                            addFavoritesArticleUseCase(article, userId)
                            firebaseAnalyticsBinding.logAddingToFavorites(article.title, userId)
                        }
                    }
                    state = state.copy(
                        isFavorite = !state.isFavorite,
                        isUserAuthenticated = true,
                    )
                } else {
                    state = state.copy(isUserAuthenticated = false)
                }
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun onArrowBackClick() {
       action = DetailsAction.NavigateBack
    }

    private fun logOpenScreen() {
        firebaseAnalyticsBinding.logOpenScreen("details_screen")
    }

    private fun handleError(e: Throwable) {
        state = state.copy(error = e)
        action = DetailsAction.ShowError("Oops, something went wrong")
        firebaseCrashlyticsBinding.sendNonFatalErrorReport(e)
    }
}
