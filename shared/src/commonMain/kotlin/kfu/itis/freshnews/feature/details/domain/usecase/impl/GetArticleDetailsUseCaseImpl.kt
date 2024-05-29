package kfu.itis.freshnews.feature.details.domain.usecase.impl

import kfu.itis.freshnews.feature.details.domain.DetailsRepository
import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails
import kfu.itis.freshnews.feature.details.domain.usecase.GetArticleDetailsUseCase

internal class GetArticleDetailsUseCaseImpl(
    private val detailsRepository: DetailsRepository,
) : GetArticleDetailsUseCase {

    override suspend fun invoke(title: String): ArticleDetails {
        return detailsRepository.getArticleDetails(title)
    }
}
