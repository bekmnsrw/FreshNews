package kfu.itis.freshnews.feature.home.presentation

import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory

data class HomeState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val latestArticles: List<Article> = listOf(),
    val selectedArticleCategory: ArticleCategory = ArticleCategory.HEALTH,
    val articlesByCategory: List<Article> = listOf(),
    val searchQuery: String = "",
    val searchedArticles: List<Article> = listOf(),
)

sealed class HomeEvent {
    object OnInit : HomeEvent()
    class OnArticleClick(val name: String) : HomeEvent()
    class OnQueryChange(val query: String) : HomeEvent()
}

sealed class HomeAction {
    class NavigateDetails(val name: String) : HomeAction()
    class ShowError(val errorMessage: String) : HomeAction()
}
