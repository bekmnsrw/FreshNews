package kfu.itis.freshnews.core.firebase

interface FirebaseCrashlyticsBinding {

    fun sendNonFatalErrorReport(error: Throwable)
    fun setParams(key: String, value: String)
}
