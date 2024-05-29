package kfu.itis.freshnews.feature.favorites.domain.usecase

import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle

interface AddFavoritesArticleUseCase {

    suspend operator fun invoke(favoritesArticle: FavoritesArticle)
}
