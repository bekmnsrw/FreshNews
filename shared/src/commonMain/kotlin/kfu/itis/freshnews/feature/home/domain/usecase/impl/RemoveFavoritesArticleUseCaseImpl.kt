package kfu.itis.freshnews.feature.home.domain.usecase.impl

import kfu.itis.freshnews.feature.home.domain.NewsRepository
import kfu.itis.freshnews.feature.home.domain.usecase.RemoveFavoritesArticleUseCase

class RemoveFavoritesArticleUseCaseImpl(
    private val newsRepository: NewsRepository,
): RemoveFavoritesArticleUseCase {

    override suspend fun invoke(id: Int) {
        newsRepository.removeFavoritesArticle(id)
    }
}
