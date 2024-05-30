package kfu.itis.freshnews.feature.details.domain

import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails

interface DetailsRepository {

    suspend fun getArticleDetails(title: String): ArticleDetails
}
