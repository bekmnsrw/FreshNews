import Shared
import FirebaseCrashlytics
import FirebaseAnalytics

final class IOSFirebaseAnalyticsBinding: FirebaseAnalyticsBinding {
    
    func logAddingToFavoritesEvent(category: ArticleCategory, source: String) {
        // TODO: Спросить у Илюши куда это должно идти
        print("Log category: \(category.name) from source: \(source)")
    }
}
