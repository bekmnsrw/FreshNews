package kfu.itis.freshnews.feature.home.data

import kfu.itis.freshnews.feature.home.data.mapper.toNews
import kfu.itis.freshnews.feature.home.domain.NewsRepository
import kfu.itis.freshnews.feature.home.domain.model.News
import kfu.itis.freshnews.feature.home.domain.model.TopHeadlinesCategory

internal class NewsRepositoryImpl(
    private val remoteNewsDataSource: RemoteNewsDataSource
): NewsRepository {

    override suspend fun getTopHeadlines(): News {
        return remoteNewsDataSource
            .getTopHeadlines()
            .toNews()
    }

    override suspend fun getTopHeadlinesByCategory(category: TopHeadlinesCategory): News {
        return remoteNewsDataSource
            .getTopHeadlinesByCategory(category = category)
            .toNews()
    }

    override suspend fun searchTopHeadlinesByPhrase(phrase: String): News {
        return remoteNewsDataSource
            .searchTopHeadlinesByPhrase(phrase = phrase)
            .toNews()
    }
}
