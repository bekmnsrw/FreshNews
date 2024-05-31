package kfu.itis.freshnews.feature.favorites.domain.usecase

import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails

interface AddFavoritesArticleUseCase {

    suspend operator fun invoke(
        articleDetails: ArticleDetails,
        userId: Long,
    )
}
