package kfu.itis.freshnews.feature.home.domain.usecase

import kfu.itis.freshnews.feature.home.domain.model.FavoritesArticle

interface AddFavoritesArticleUseCase {

    suspend operator fun invoke(favoritesArticle: FavoritesArticle)
}
