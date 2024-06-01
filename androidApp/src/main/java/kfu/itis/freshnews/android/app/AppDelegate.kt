package kfu.itis.freshnews.android.app

import android.app.Application
import kfu.itis.freshnews.core.di.initKoin

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        initShared()
        initKoin(appDeclaration = {})
    }
}
