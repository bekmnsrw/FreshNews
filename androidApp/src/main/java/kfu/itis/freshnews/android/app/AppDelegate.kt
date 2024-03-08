package kfu.itis.freshnews.android.app

import android.app.Application

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        initShared()
    }
}
