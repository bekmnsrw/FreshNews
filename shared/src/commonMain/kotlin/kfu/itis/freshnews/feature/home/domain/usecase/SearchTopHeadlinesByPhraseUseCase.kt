package kfu.itis.freshnews.feature.home.domain.usecase

import kfu.itis.freshnews.feature.home.domain.model.News

interface SearchTopHeadlinesByPhraseUseCase {

    suspend operator fun invoke(phrase: String): News
}
