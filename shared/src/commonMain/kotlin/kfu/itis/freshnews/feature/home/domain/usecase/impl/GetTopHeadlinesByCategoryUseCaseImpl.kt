package kfu.itis.freshnews.feature.home.domain.usecase.impl

import kfu.itis.freshnews.feature.home.domain.NewsRepository
import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory
import kfu.itis.freshnews.feature.home.domain.usecase.GetTopHeadlinesByCategoryUseCase

internal class GetTopHeadlinesByCategoryUseCaseImpl(
    private val newsRepository: NewsRepository,
) : GetTopHeadlinesByCategoryUseCase {

    override suspend fun invoke(category: ArticleCategory): List<Article> {
        return newsRepository.getTopHeadlinesByCategory(category)
    }
}
