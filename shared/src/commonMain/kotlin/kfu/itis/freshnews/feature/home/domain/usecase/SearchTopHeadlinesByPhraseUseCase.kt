package kfu.itis.freshnews.feature.home.domain.usecase

import kfu.itis.freshnews.feature.home.domain.model.Article

interface SearchTopHeadlinesByPhraseUseCase {

    suspend operator fun invoke(phrase: String): List<Article>
}
