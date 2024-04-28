package kfu.itis.freshnews.feature.home.domain.usecase

import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory

interface GetTopHeadlinesByCategoryUseCase {

    suspend operator fun invoke(category: ArticleCategory): List<Article>
}
