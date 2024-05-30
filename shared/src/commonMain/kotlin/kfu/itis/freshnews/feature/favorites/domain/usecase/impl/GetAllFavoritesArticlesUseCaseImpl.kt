package kfu.itis.freshnews.feature.favorites.domain.usecase.impl

import kfu.itis.freshnews.feature.favorites.domain.FavoritesRepository
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kfu.itis.freshnews.feature.favorites.domain.usecase.GetAllFavoritesArticlesUseCase
import kotlinx.coroutines.flow.Flow

internal class GetAllFavoritesArticlesUseCaseImpl(
    private val favoritesRepository: FavoritesRepository,
) : GetAllFavoritesArticlesUseCase {

    override fun invoke(): Flow<List<FavoritesArticle>> {
        return favoritesRepository.getAllFavoritesArticle()
    }
}
