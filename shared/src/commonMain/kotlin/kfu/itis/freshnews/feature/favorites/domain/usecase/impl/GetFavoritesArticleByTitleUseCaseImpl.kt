package kfu.itis.freshnews.feature.favorites.domain.usecase.impl

import kfu.itis.freshnews.feature.favorites.domain.FavoritesRepository
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kfu.itis.freshnews.feature.favorites.domain.usecase.GetFavoritesArticleByTitleUseCase
import kotlinx.coroutines.flow.Flow

internal class GetFavoritesArticleByTitleUseCaseImpl(
    private val favoritesRepository: FavoritesRepository,
) : GetFavoritesArticleByTitleUseCase {

    override fun invoke(
        title: String,
        userId: Long,
    ): Flow<FavoritesArticle?> {
        return favoritesRepository.getFavoritesArticleByTitle(title, userId)
    }
}
