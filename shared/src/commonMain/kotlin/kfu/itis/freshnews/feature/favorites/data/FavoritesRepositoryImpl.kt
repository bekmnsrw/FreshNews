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

    override suspend fun addFavoritesArticle(articleDetails: ArticleDetails, userId: Long) {
        return localFavoritesDataSource.addFavoritesNews(articleDetails.toFavoritesArticle(userId))
    }

    override suspend fun removeFavoritesArticle(title: String, userId: Long) {
        return localFavoritesDataSource.removeFavoritesNewsByTitle(title, userId)
    }

    override fun getAllFavoritesArticle(userId: Long): Flow<List<FavoritesArticle>> {
        return localFavoritesDataSource.getAllFavoritesNews(userId)
            .map { favoritesNews -> favoritesNews.toFavoritesArticleList() }
    }

    override fun getFavoritesArticleById(articleId: Long, userId: Long): Flow<FavoritesArticle> {
        return localFavoritesDataSource.getFavoritesNewsById(articleId, userId)
            .map { favoritesNews -> favoritesNews.toFavoritesArticle() }
    }

    override fun getFavoritesArticleByTitle(title: String, userId: Long): Flow<FavoritesArticle?> {
        return localFavoritesDataSource.getFavoritesNewsByTitle(title, userId)
            .map { favoritesNews -> favoritesNews?.toFavoritesArticle() }
    }
}
