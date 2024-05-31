package kfu.itis.freshnews.feature.auth.data.datasource.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kfu.itis.freshnews.FreshNews
import kfu.itis.freshnews.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class LocalAuthDataSource(
    private val database: FreshNews,
) {

    suspend fun signUp(login: String, password: String) = withContext(Dispatchers.IO) {
        database.freshNewsQueries.signUp(
            id = null,
            login = login,
            password = password,
        )
    }

    fun signIn(login: String, password: String): Flow<Profile?> {
        return database.freshNewsQueries.signIn(login, password)
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)
    }

    fun isProfileExists(login: String): Flow<Boolean> {
        return database.freshNewsQueries.isProfileExists(login)
            .asFlow()
            .mapToOne(Dispatchers.IO)
    }
}
