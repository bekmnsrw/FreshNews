package kfu.itis.freshnews.feature.favorites.domain.usecase.impl

import kfu.itis.freshnews.feature.favorites.domain.FavoritesRepository
import kfu.itis.freshnews.feature.favorites.domain.usecase.RemoveFavoritesArticleUseCase

internal class RemoveFavoritesArticleUseCaseImpl(
    private val favoritesRepository: FavoritesRepository,
) : RemoveFavoritesArticleUseCase {

    override suspend fun invoke(
        title: String,
        userId: Long,
    ) {
        favoritesRepository.removeFavoritesArticle(title, userId)
    }
}
