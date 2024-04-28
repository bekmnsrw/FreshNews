package kfu.itis.freshnews.feature.home.domain.usecase

import kfu.itis.freshnews.feature.home.domain.model.Article

interface GetTopHeadlinesUseCase {

    suspend operator fun invoke(): List<Article>
}
