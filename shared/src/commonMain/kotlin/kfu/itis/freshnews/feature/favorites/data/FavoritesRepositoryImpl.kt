package kfu.itis.freshnews.feature.favorites.data

import kfu.itis.freshnews.feature.details.data.mapper.toFavoritesArticle
import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails
import kfu.itis.freshnews.feature.favorites.domain.FavoritesRepository
import kfu.itis.freshnews.feature.favorites.data.datasource.local.LocalFavoritesDataSource
import kfu.itis.freshnews.feature.favorites.data.mapper.toFavoritesArticle
import kfu.itis.freshnews.feature.favorites.data.mapper.toFavoritesArticleList
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FavoritesRepositoryImpl(
    private val localFavoritesDataSource: LocalFavoritesDataSource,
) : FavoritesRepository {

    override suspend fun addFavoritesArticle(articleDetails: ArticleDetails) {
        localFavoritesDataSource.addFavoritesNews(articleDetails.toFavoritesArticle())
    }

    override suspend fun removeFavoritesArticle(id: Int) {
        localFavoritesDataSource.removeFavoritesNewsById(id)
    }

    override fun getAllFavoritesArticle(): Flow<List<FavoritesArticle>> {
        return localFavoritesDataSource.getAllFavoritesNews()
            .map { favoritesNews -> favoritesNews.toFavoritesArticleList() }
    }

    override fun getFavoritesArticleById(id: Int): Flow<FavoritesArticle> {
        return localFavoritesDataSource.getFavoritesNewsById(id)
            .map { favoritesNews -> favoritesNews.toFavoritesArticle() }
    }

    override fun getFavoritesArticleByTitle(title: String): Flow<FavoritesArticle?> {
        return localFavoritesDataSource.getFavoritesNewsByTitle(title)
            .map { favoritesNews -> favoritesNews?.toFavoritesArticle() }
    }
}
