package kfu.itis.freshnews.core.firebase

interface FirebaseAnalyticsBinding {

    fun logOpenScreen(screenName: String)
    fun logSignIn(login: String)
    fun logSignUp(login: String)
    fun logAddingToFavorites(title: String, userId: Long)
    fun logRemovingFromFavorites(title: String, userId: Long)
}
