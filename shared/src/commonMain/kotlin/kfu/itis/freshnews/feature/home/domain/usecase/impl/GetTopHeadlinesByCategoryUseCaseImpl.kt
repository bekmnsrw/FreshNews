package kfu.itis.freshnews.feature.home.domain.usecase.impl

import kfu.itis.freshnews.feature.home.domain.NewsRepository
import kfu.itis.freshnews.feature.home.domain.model.News
import kfu.itis.freshnews.feature.home.domain.model.TopHeadlinesCategory
import kfu.itis.freshnews.feature.home.domain.usecase.GetTopHeadlinesByCategoryUseCase

class GetTopHeadlinesByCategoryUseCaseImpl(
    private val newsRepository: NewsRepository
): GetTopHeadlinesByCategoryUseCase {

    override suspend fun invoke(
        category: TopHeadlinesCategory
    ): News = newsRepository.getTopHeadlinesByCategory(
        category = category
    )
}
