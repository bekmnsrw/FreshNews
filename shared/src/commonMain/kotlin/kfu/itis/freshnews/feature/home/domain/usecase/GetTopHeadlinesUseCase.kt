package kfu.itis.freshnews.feature.home.domain.usecase

import kfu.itis.freshnews.feature.home.domain.model.News

interface GetTopHeadlinesUseCase {

    suspend operator fun invoke(): News
}
