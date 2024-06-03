package kfu.itis.freshnews.feature.home.presentation

import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory

data class HomeState(
    val isLatestArticlesLoading: Boolean = false,
    val isArticlesOfCategoryLoading: Boolean = false,
    val error: Throwable? = null,
    val latestArticles: List<Article> = listOf(),
    val selectedArticleCategory: ArticleCategory = ArticleCategory.HEALTH,
    val articlesOfCategory: List<Article> = listOf(),
)

sealed class HomeEvent {
    class OnArticleClick(val article: Article) : HomeEvent()
    class OnArticleCategoryClick(val category: ArticleCategory) : HomeEvent()
    object OnOpenWiFiSettingsClick : HomeEvent()
    object ReloadNews : HomeEvent()
}

sealed class HomeAction {
    class NavigateDetails(val article: Article) : HomeAction()
    object ShowError : HomeAction()
    object OpenWiFiSettings : HomeAction()
}
