package kfu.itis.freshnews.android.firebase

import com.google.firebase.crashlytics.FirebaseCrashlytics
import kfu.itis.freshnews.core.firebase.FirebaseCrashlyticsBinding
import kfu.itis.freshnews.utils.lazyUnsafe

class AndroidFirebaseCrashlyticsBinding : FirebaseCrashlyticsBinding {

    private val crashlytics by lazyUnsafe {
        FirebaseCrashlytics.getInstance()
    }

    override fun sendNonFatalErrorReport(error: Throwable) {
        crashlytics.recordException(error)
    }

    override fun setParams(key: String, value: String) {
        crashlytics.setCustomKey(key, value)
    }
}
