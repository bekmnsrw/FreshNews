package kfu.itis.freshnews.feature.favorites.data.datasource.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kfu.itis.freshnews.FavoritesNews
import kfu.itis.freshnews.FreshNews
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class LocalFavoritesDataSource(
    private val database: FreshNews,
) {

    suspend fun addFavoritesNews(favoritesArticle: FavoritesArticle) = withContext(Dispatchers.IO) {
        database.freshNewsQueries.addFavoritesNews(
            id = favoritesArticle.id,
            image_url = favoritesArticle.imageUrl,
            title = favoritesArticle.title,
            description = favoritesArticle.descr,
            source = favoritesArticle.source,
            published_at = favoritesArticle.publishedAt,
            profile_id = favoritesArticle.profileId,
        )
    }

    fun getAllFavoritesNews(userId: Long): Flow<List<FavoritesNews>> {
        return database.freshNewsQueries.getAllFavoritesNews(userId)
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    fun getFavoritesNewsById(articleId: Long, userId: Long): Flow<FavoritesNews> {
        return database.freshNewsQueries.getFavoritesNewsById(articleId, userId)
            .asFlow()
            .mapToOne(Dispatchers.IO)
    }

    fun getFavoritesNewsByTitle(title: String, userId: Long): Flow<FavoritesNews?> {
        return database.freshNewsQueries.getFavoritesNewsByTitle(title, userId)
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)
    }

    suspend fun removeFavoritesNewsByTitle(title: String, userId: Long) = withContext(Dispatchers.IO) {
        database.freshNewsQueries.removeFavoritesNewsByTitle(title, userId)
    }
}
