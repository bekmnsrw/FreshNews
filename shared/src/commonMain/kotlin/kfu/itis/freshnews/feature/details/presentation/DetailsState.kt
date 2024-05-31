package kfu.itis.freshnews.feature.details.presentation

import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails

data class DetailsState(
    val articleDetails: ArticleDetails? = null,
    val isFavorite: Boolean = false,
    val error: Throwable? = null,
    val isUserAuthenticated: Boolean = false,
)

sealed class DetailsEvent {
    class OnInit(
        val articleDetails: ArticleDetails?,
        val favoriteArticleId: Long?,
    ) : DetailsEvent()
    object OnArrowBackClick : DetailsEvent()
    object OnAddToFavoritesClick : DetailsEvent()
}

sealed class DetailsAction {
    object NavigateBack : DetailsAction()
    class ShowError(val errorMessage: String) : DetailsAction()
}
