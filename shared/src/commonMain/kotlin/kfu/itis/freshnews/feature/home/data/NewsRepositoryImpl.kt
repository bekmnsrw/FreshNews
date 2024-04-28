package kfu.itis.freshnews.feature.home.data

import kfu.itis.freshnews.feature.home.data.mapper.toArticles
import kfu.itis.freshnews.feature.home.domain.NewsRepository
import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory

internal class NewsRepositoryImpl(
    private val remoteNewsDataSource: RemoteNewsDataSource
): NewsRepository {

    override suspend fun getTopHeadlines(): List<Article> {
        return remoteNewsDataSource
            .getTopHeadlines()
            .articleResponses
            .toArticles()
    }

    override suspend fun getTopHeadlinesByCategory(category: ArticleCategory): List<Article> {
        return remoteNewsDataSource
            .getTopHeadlinesByCategory(category = category)
            .articleResponses
            .toArticles()
    }

    override suspend fun searchTopHeadlinesByPhrase(phrase: String): List<Article> {
        return remoteNewsDataSource
            .searchTopHeadlinesByPhrase(phrase = phrase)
            .articleResponses
            .toArticles()
    }
}
