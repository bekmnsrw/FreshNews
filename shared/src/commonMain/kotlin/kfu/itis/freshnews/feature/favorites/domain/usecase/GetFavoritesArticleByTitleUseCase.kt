package kfu.itis.freshnews.feature.favorites.domain.usecase

import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kotlinx.coroutines.flow.Flow

interface GetFavoritesArticleByTitleUseCase {

    operator fun invoke(
        title: String,
        userId: Long,
    ): Flow<FavoritesArticle?>
}
