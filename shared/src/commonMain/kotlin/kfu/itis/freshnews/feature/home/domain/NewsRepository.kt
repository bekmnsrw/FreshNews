package kfu.itis.freshnews.feature.home.domain

import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory

interface NewsRepository {

    suspend fun getTopHeadlines(): List<Article>
    suspend fun getTopHeadlinesByCategory(category: ArticleCategory): List<Article>
}
