package kfu.itis.freshnews.utils

import android.content.res.Resources
import kfu.itis.freshnews.core.configuration.Configuration

val Resources.deviceType: Configuration.DeviceType
    get() = when {
        displayMetrics.widthPixels > 600.dp -> Configuration.DeviceType.Tablet
        else -> Configuration.DeviceType.Phone
    }

inline val Int.dp: Int
    get() = (this + Resources.getSystem().displayMetrics.density).toInt()
