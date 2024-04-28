package kfu.itis.freshnews.feature.home.domain.usecase.impl

import kfu.itis.freshnews.feature.home.domain.NewsRepository
import kfu.itis.freshnews.feature.home.domain.model.FavoritesArticle
import kfu.itis.freshnews.feature.home.domain.usecase.AddFavoritesArticleUseCase

class AddFavoritesArticleUseCaseImpl(
    private val newsRepository: NewsRepository,
): AddFavoritesArticleUseCase {

    override suspend fun invoke(favoritesArticle: FavoritesArticle) {
        newsRepository.addFavoritesArticle(favoritesArticle)
    }
}
