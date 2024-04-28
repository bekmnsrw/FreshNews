package kfu.itis.freshnews.feature.home.domain.usecase

import kfu.itis.freshnews.feature.home.domain.model.FavoritesArticle
import kotlinx.coroutines.flow.Flow

interface GetAllFavoritesArticlesUseCase {

    suspend operator fun invoke(): Flow<List<FavoritesArticle>>
}
