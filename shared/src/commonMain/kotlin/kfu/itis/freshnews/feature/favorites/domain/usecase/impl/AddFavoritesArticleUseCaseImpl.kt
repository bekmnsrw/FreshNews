package kfu.itis.freshnews.feature.favorites.domain.usecase.impl

import kfu.itis.freshnews.feature.favorites.domain.FavoritesRepository
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kfu.itis.freshnews.feature.favorites.domain.usecase.AddFavoritesArticleUseCase

internal class AddFavoritesArticleUseCaseImpl(
    private val favoritesRepository: FavoritesRepository,
) : AddFavoritesArticleUseCase {

    override suspend fun invoke(favoritesArticle: FavoritesArticle) {
        return favoritesRepository.addFavoritesArticle(favoritesArticle)
    }
}
