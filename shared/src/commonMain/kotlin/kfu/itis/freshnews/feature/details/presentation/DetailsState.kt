package kfu.itis.freshnews.feature.details.presentation

import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails

data class DetailsState(
    val articleDetails: ArticleDetails? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null,
)

sealed class DetailsEvent {
    class OnInit(val title: String) : DetailsEvent()
    object OnArrowBackClick : DetailsEvent()
    object OnAddToFavoritesClick : DetailsEvent()
}

sealed class DetailsAction {
    object NavigateBack : DetailsAction()
    class ShowError(val errorMessage: String) : DetailsAction()
}
