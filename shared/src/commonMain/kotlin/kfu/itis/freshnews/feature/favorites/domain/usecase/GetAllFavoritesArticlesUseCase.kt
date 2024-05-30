package kfu.itis.freshnews.feature.favorites.domain.usecase

import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kotlinx.coroutines.flow.Flow

interface GetAllFavoritesArticlesUseCase {

    operator fun invoke(): Flow<List<FavoritesArticle>>
}
