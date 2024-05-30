package kfu.itis.freshnews.android.firebase

import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import kfu.itis.freshnews.core.firebase.FirebaseAnalyticsBinding
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory
import kfu.itis.freshnews.utils.lazyUnsafe

class AndroidFirebaseAnalytics : FirebaseAnalyticsBinding {

    private val analytics by lazyUnsafe {
        Firebase.analytics
    }

    override fun logAddingToFavoritesEvent(category: ArticleCategory, source: String) {
        analytics.logEvent(ADD_TO_FAVORITES) {
            param(ARTICLE_CATEGORY, category.name)
            param(ARTICLE_SOURCE, source)
        }
    }

    override fun logOpenScreen(screenName: String) {
        analytics.logEvent(OPEN_SCREEN) {
            param(SCREEN_NAME, screenName)
        }
    }

    private companion object {

        const val ADD_TO_FAVORITES = "add_to_favorites"
        const val ARTICLE_CATEGORY = "article_category"
        const val ARTICLE_SOURCE = "article_source"

        const val OPEN_SCREEN = "open_screen"
        const val SCREEN_NAME = "screen_name"
    }
}
