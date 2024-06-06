import Shared
import FirebaseCrashlytics
import FirebaseAnalytics

final class IOSFirebaseAnalyticsBinding: FirebaseAnalyticsBinding {

    func logAddingToFavorites(title: String, userId: Int64) {
        Analytics.logEvent(AnalyticsEventScreenView, parameters: [
            "title": title,
            "userId": userId
        ])
    }
    
    func logOpenScreen(screenName: String) {
        Analytics.logEvent(AnalyticsEventScreenView, parameters: [
            "screenName": screenName
        ])
    }
    
    func logRemovingFromFavorites(title: String, userId: Int64) {
        Analytics.logEvent(AnalyticsEventScreenView, parameters: [
            "title": title,
            "userId": userId
        ])
    }
    
    func logSignIn(login: String) {
        Analytics.logEvent(AnalyticsEventScreenView, parameters: [
            "login": login
        ])
    }
    
    func logSignUp(login: String) {
        Analytics.logEvent(AnalyticsEventScreenView, parameters: [
            "login": login
        ])
    }
    
    func logAddingToFavoritesEvent(category: ArticleCategory, source: String) {
        Analytics.logEvent(AnalyticsEventScreenView, parameters: [
            "category": category,
            "source": source
        ])
    }
}
