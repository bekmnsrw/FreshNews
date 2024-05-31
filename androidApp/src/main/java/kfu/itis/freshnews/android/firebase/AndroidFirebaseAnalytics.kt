package kfu.itis.freshnews.android.firebase

import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import kfu.itis.freshnews.core.firebase.FirebaseAnalyticsBinding
import kfu.itis.freshnews.utils.lazyUnsafe

class AndroidFirebaseAnalytics : FirebaseAnalyticsBinding {

    private val analytics by lazyUnsafe {
        Firebase.analytics
    }

    override fun logOpenScreen(screenName: String) {
        analytics.logEvent(OPEN_SCREEN) {
            param(SCREEN_NAME, screenName)
        }
    }

    override fun logSignIn(login: String) {
        analytics.logEvent(SIGN_IN) {
            param(LOGIN, login)
        }
    }

    override fun logSignUp(login: String) {
        analytics.logEvent(SIGN_UP) {
            param(LOGIN, login)
        }
    }

    override fun logAddingToFavorites(title: String, userId: Long) {
        analytics.logEvent(ADD_TO_FAVORITES) {
            param(TITLE, title)
            param(USER_ID, userId)
        }
    }

    override fun logRemovingFromFavorites(title: String, userId: Long) {
        analytics.logEvent(REMOVE_FROM_FAVORITES) {
            param(TITLE, title)
            param(USER_ID, userId)
        }
    }

    private companion object {

        const val ADD_TO_FAVORITES = "add_to_favorites"
        const val REMOVE_FROM_FAVORITES = "remove_from_favorites"
        const val TITLE = "title"
        const val USER_ID = "userId"

        const val OPEN_SCREEN = "open_screen"
        const val SCREEN_NAME = "screen_name"

        const val SIGN_UP = "sign_up"
        const val SIGN_IN = "sign_in"
        const val LOGIN = "login"
    }
}
