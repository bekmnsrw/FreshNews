package kfu.itis.freshnews.core.firebase

import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory

interface FirebaseAnalyticsBinding {

    fun logAddingToFavoritesEvent(category: ArticleCategory, source: String)
}
