package kfu.itis.freshnews.feature.favorites.presentation

import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle

data class FavoritesState(
    val favoritesArticles: List<FavoritesArticle> = emptyList(),
    val error: Throwable? = null,
    val isUserAuthenticated: Boolean = false,
)

sealed class FavoritesEvent {
    object OnInit : FavoritesEvent()
    class OnArticleClick(val id: Long) : FavoritesEvent()
    object OnAuthButtonClick : FavoritesEvent()
}

sealed class FavoritesAction {
    class NavigateDetails(val id: Long) : FavoritesAction()
    object ShowError : FavoritesAction()
    object NavigateAuth : FavoritesAction()
}
