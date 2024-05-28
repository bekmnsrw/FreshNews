package kfu.itis.freshnews.feature.home.data

import kfu.itis.freshnews.feature.home.data.datasource.local.LocalNewsDataSource
import kfu.itis.freshnews.feature.home.data.datasource.remote.RemoteNewsDataSource
import kfu.itis.freshnews.feature.home.data.mapper.toArticles
import kfu.itis.freshnews.feature.home.data.mapper.toFavoritesArticleList
import kfu.itis.freshnews.feature.home.domain.NewsRepository
import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory
import kfu.itis.freshnews.feature.home.domain.model.FavoritesArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class NewsRepositoryImpl(
    private val remoteNewsDataSource: RemoteNewsDataSource,
    private val localNewsDataSource: LocalNewsDataSource,
): NewsRepository {

    override suspend fun getTopHeadlines(): List<Article> {
        return remoteNewsDataSource
            .getTopHeadlines()
            .articleResponses
            .filterNot { articleResponse -> articleResponse.title == REMOVED_ARTICLE }
            .toArticles()
    }

    override suspend fun getTopHeadlinesByCategory(category: ArticleCategory): List<Article> {
        return remoteNewsDataSource
            .getTopHeadlinesByCategory(category = category)
            .articleResponses
            .filterNot { articleResponse -> articleResponse.title == REMOVED_ARTICLE }
            .toArticles()
    }

    override suspend fun searchTopHeadlinesByPhrase(phrase: String): List<Article> {
        return remoteNewsDataSource
            .searchTopHeadlinesByPhrase(phrase = phrase)
            .articleResponses
            .filterNot { articleResponse -> articleResponse.title == REMOVED_ARTICLE }
            .toArticles()
    }

    override suspend fun addFavoritesArticle(favoritesArticle: FavoritesArticle) {
        localNewsDataSource.addFavoritesNews(favoritesArticle)
    }

    override suspend fun removeFavoritesArticle(id: Int) {
        localNewsDataSource.removeFavoritesNewsById(id)
    }

    override suspend fun getAllFavoritesArticle(): Flow<List<FavoritesArticle>> {
        return localNewsDataSource.getAllFavoritesNews()
            .map { it.toFavoritesArticleList() }
    }

    private companion object {

        const val REMOVED_ARTICLE = "[Removed]"
    }
}
