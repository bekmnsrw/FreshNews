package kfu.itis.freshnews.feature.details.domain.usecase

import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails

interface GetArticleDetailsUseCase {

    suspend operator fun invoke(title: String): ArticleDetails
}
