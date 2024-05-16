package kfu.itis.freshnews.feature.home.presentation

import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory

data class HomeState(
    val isLatestArticlesLoading: Boolean = false,
    val isArticlesOfCategoryLoading: Boolean = false,
    val isSearchedArticlesLoading: Boolean = false,
    val error: Throwable? = null,
    val latestArticles: List<Article> = listOf(),
    val selectedArticleCategory: ArticleCategory = ArticleCategory.HEALTH,
    val articlesOfCategory: List<Article> = listOf(),
    val searchQuery: String = "",
    val searchedArticles: List<Article> = listOf(),
    val isSearchActive: Boolean = false,
)

sealed class HomeEvent {
    object OnInit : HomeEvent()
    class OnArticleClick(val name: String) : HomeEvent()
    class OnQueryChange(val query: String) : HomeEvent()
    class OnArticleCategoryClick(val category: ArticleCategory) : HomeEvent()
    class OnActiveChange(val isSearchActive: Boolean) : HomeEvent()
    class OnSearch(val query: String) : HomeEvent()
}

sealed class HomeAction {
    class NavigateDetails(val name: String) : HomeAction()
    class ShowError(val errorMessage: String) : HomeAction()
}
