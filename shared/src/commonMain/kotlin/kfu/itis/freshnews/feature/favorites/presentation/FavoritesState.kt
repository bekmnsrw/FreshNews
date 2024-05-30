package kfu.itis.freshnews.feature.favorites.presentation

import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle

data class FavoritesState(
    val favoritesArticles: List<FavoritesArticle> = emptyList(),
    val error: Throwable? = null,
)

sealed class FavoritesEvent {
    class OnArticleClick(val id: Int) : FavoritesEvent()
}

sealed class FavoritesAction {
    class NavigateDetails(val id: Int) : FavoritesAction()
    class ShowError(val errorMessage: String) : FavoritesAction()
}
