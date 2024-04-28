package kfu.itis.freshnews.feature.home.domain.usecase.impl

import kfu.itis.freshnews.feature.home.domain.NewsRepository
import kfu.itis.freshnews.feature.home.domain.model.FavoritesArticle
import kfu.itis.freshnews.feature.home.domain.usecase.GetAllFavoritesArticlesUseCase
import kotlinx.coroutines.flow.Flow

class GetAllFavoritesArticlesUseCaseImpl(
    private val newsRepository: NewsRepository,
): GetAllFavoritesArticlesUseCase {

    override suspend fun invoke(): Flow<List<FavoritesArticle>> {
        return newsRepository.getAllFavoritesArticle()
    }
}
