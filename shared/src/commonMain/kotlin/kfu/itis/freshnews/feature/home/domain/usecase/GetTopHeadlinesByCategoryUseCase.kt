package kfu.itis.freshnews.feature.home.domain.usecase

import kfu.itis.freshnews.feature.home.domain.model.News
import kfu.itis.freshnews.feature.home.domain.model.TopHeadlinesCategory

interface GetTopHeadlinesByCategoryUseCase {

    suspend operator fun invoke(category: TopHeadlinesCategory): News
}
