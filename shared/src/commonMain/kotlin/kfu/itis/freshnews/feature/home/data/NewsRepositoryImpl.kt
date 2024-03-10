package kfu.itis.freshnews.feature.home.data

import kfu.itis.freshnews.feature.home.data.mapper.toNews
import kfu.itis.freshnews.feature.home.domain.NewsRepository
import kfu.itis.freshnews.feature.home.domain.model.News

internal class NewsRepositoryImpl(
    private val remoteNewsDataSource: RemoteNewsDataSource
): NewsRepository {

    override suspend fun getTopHeadlines(): News {
        return remoteNewsDataSource
            .getTopHeadlines()
            .toNews()
    }
}
