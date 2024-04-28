package kfu.itis.freshnews.feature.home.domain.usecase.impl

import kfu.itis.freshnews.feature.home.domain.NewsRepository
import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.usecase.SearchTopHeadlinesByPhraseUseCase

class SearchTopHeadlinesByPhraseUseCaseImpl(
    private val newsRepository: NewsRepository
): SearchTopHeadlinesByPhraseUseCase {

    override suspend fun invoke(
        phrase: String
    ): List<Article> = newsRepository.searchTopHeadlinesByPhrase(
        phrase = phrase
    )
}