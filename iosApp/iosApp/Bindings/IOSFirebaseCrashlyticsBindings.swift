import Shared
import FirebaseCrashlytics

final class IOSFirebaseCrashlyticsBindings: FirebaseCrashlyticsBinding {

    func sendNonFatalErrorReport(error: KotlinThrowable) {
        Crashlytics.crashlytics().record(error: error.asError())
    }
    
    func setParams(key: String, value: String) {
        Crashlytics.crashlytics().setCustomValue(value, forKey: key)
    }
}

