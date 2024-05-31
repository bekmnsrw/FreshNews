package kfu.itis.freshnews.feature.favorites.domain.usecase.impl

import kfu.itis.freshnews.feature.favorites.domain.FavoritesRepository
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kfu.itis.freshnews.feature.favorites.domain.usecase.GetFavoritesArticleByIdUseCase
import kotlinx.coroutines.flow.Flow

internal class GetFavoritesArticleByIdUseCaseImpl(
    private val favoritesRepository: FavoritesRepository,
) : GetFavoritesArticleByIdUseCase {

    override fun invoke(
        articleId: Long,
        userId: Long,
    ): Flow<FavoritesArticle> {
        return favoritesRepository.getFavoritesArticleById(articleId, userId)
    }
}
