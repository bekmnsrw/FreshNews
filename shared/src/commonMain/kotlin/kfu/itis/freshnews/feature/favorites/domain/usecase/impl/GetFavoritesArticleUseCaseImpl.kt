package kfu.itis.freshnews.feature.favorites.domain.usecase.impl

import kfu.itis.freshnews.feature.favorites.domain.FavoritesRepository
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kfu.itis.freshnews.feature.favorites.domain.usecase.GetFavoritesArticleUseCase
import kotlinx.coroutines.flow.Flow

internal class GetFavoritesArticleUseCaseImpl(
    private val favoritesRepository: FavoritesRepository,
) : GetFavoritesArticleUseCase {

    override suspend fun invoke(id: Int): Flow<FavoritesArticle> {
        return favoritesRepository.getFavoritesArticleById(id)
    }
}
