package kfu.itis.freshnews.feature.profile.data.datasource.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kfu.itis.freshnews.FreshNews
import kfu.itis.freshnews.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

internal class LocalProfileDataSource(
    private val database: FreshNews,
) {

    fun getProfile(id: Long): Flow<Profile?> {
        return database.freshNewsQueries.getProfileById(id)
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)
    }

    fun deleteProfile(id: Long) {
        database.freshNewsQueries.deleteProfile(id)
    }
}
