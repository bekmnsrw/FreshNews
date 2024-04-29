package kfu.itis.freshnews.android.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun String.toDate(): String = Instant
    .parse(this)
    .toLocalDateTime(TimeZone.currentSystemDefault())
    .date
    .toString()
