package kfu.itis.freshnews.feature.home.domain.model

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize

@Parcelize
data class Article(
    val descr: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val urlToImage: String,
) : Parcelable
