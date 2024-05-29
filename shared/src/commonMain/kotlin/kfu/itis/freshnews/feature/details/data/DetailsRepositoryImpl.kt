package kfu.itis.freshnews.feature.details.data

import kfu.itis.freshnews.feature.details.data.datasource.remote.RemoteDetailsDataSource
import kfu.itis.freshnews.feature.details.data.mapper.toArticleDetails
import kfu.itis.freshnews.feature.details.domain.DetailsRepository
import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails

internal class DetailsRepositoryImpl(
    private val remoteDetailsDataSource: RemoteDetailsDataSource,
) : DetailsRepository {

    override suspend fun getArticleDetails(title: String): ArticleDetails {
        return remoteDetailsDataSource
            .getArticleDetails(title)
            .articleDetails
            .first()
            .toArticleDetails()
    }
}
