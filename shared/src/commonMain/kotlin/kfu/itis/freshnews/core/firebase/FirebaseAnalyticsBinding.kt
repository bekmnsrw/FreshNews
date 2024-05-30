package kfu.itis.freshnews.core.firebase

import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory

interface FirebaseAnalyticsBinding {

    fun logAddingToFavoritesEvent(category: ArticleCategory, source: String)
    fun logOpenScreen(screenName: String)
    fun logSignIn(login: String)
    fun logSignUp(login: String)
}
