import Shared
import FirebaseCrashlytics
import FirebaseAnalytics

final class IOSFirebaseAnalyticsBinding: FirebaseAnalyticsBinding {

    func logAddingToFavorites(title: String, userId: Int64) {
        
    }
    
    func logOpenScreen(screenName: String) {
        
    }
    
    func logRemovingFromFavorites(title: String, userId: Int64) {
        
    }
    
    func logSignIn(login: String) {
        
    }
    
    func logSignUp(login: String) {
        
    }
    
    func logAddingToFavoritesEvent(category: ArticleCategory, source: String) {
        print("Log category: \(category.name) from source: \(source)")
    }
}
