package kfu.itis.freshnews.feature.home.domain.usecase.impl

import kfu.itis.freshnews.feature.home.domain.NewsRepository
import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.usecase.GetTopHeadlinesUseCase

internal class GetTopHeadlinesUseCaseImpl(
    private val newsRepository: NewsRepository,
) : GetTopHeadlinesUseCase {

    override suspend fun invoke(): List<Article> {
        return newsRepository.getTopHeadlines()
    }
}
