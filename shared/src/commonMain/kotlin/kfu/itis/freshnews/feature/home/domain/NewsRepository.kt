package kfu.itis.freshnews.feature.home.domain

import kfu.itis.freshnews.feature.home.domain.model.News
import kfu.itis.freshnews.feature.home.domain.model.TopHeadlinesCategory

interface NewsRepository {

    suspend fun getTopHeadlines(): News
    suspend fun getTopHeadlinesByCategory(category: TopHeadlinesCategory): News
    suspend fun searchTopHeadlinesByPhrase(phrase: String): News
}
