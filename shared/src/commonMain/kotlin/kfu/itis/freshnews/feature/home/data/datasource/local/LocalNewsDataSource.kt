package kfu.itis.freshnews.feature.home.data.datasource.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import kfu.itis.freshnews.FavoritesNews
import kfu.itis.freshnews.FreshNews
import kfu.itis.freshnews.feature.home.domain.model.FavoritesArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class LocalNewsDataSource(
    private val database: FreshNews,
) {

    suspend fun addFavoritesNews(favoritesArticle: FavoritesArticle) = withContext(Dispatchers.IO) {
        database.freshNewsQueries.addFavoritesNews(
            id = null,
            image_url = favoritesArticle.imageUrl,
            title = favoritesArticle.title,
            description = favoritesArticle.description,
            source = favoritesArticle.source,
            published_at = favoritesArticle.publishedAt,
        )
    }

    /*
     * TODO: Move to FavoritesFeature
     */
    fun getAllFavoritesNews(): Flow<List<FavoritesNews>> {
        return database.freshNewsQueries.getAllFavoritesNews()
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    /*
     * TODO: Move to FavoritesFeature
     */
    fun getFavoritesNewsById(id: Int): Flow<FavoritesNews> {
        return database.freshNewsQueries.getFavoritesNewsById(id.toLong())
            .asFlow()
            .mapToOne(Dispatchers.IO)
    }

    suspend fun removeFavoritesNewsById(id: Int) = withContext(Dispatchers.IO) {
        database.freshNewsQueries.removeFavoritesNewsById(id.toLong())
    }
}
