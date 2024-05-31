package kfu.itis.freshnews.feature.favorites.domain.usecase

import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kotlinx.coroutines.flow.Flow

interface GetFavoritesArticleByIdUseCase {

    operator fun invoke(
        articleId: Long,
        userId: Long,
    ): Flow<FavoritesArticle>
}
