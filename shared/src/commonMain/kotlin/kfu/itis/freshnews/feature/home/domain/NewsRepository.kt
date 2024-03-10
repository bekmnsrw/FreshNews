package kfu.itis.freshnews.feature.home.domain

import kfu.itis.freshnews.feature.home.domain.model.News

interface NewsRepository {

    suspend fun getTopHeadlines(): News
}
