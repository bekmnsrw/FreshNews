package kfu.itis.freshnews.android.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kfu.itis.freshnews.android.R
import kfu.itis.freshnews.android.utils.ColumnSpacer
import kfu.itis.freshnews.android.utils.LazyColumnSpacer
import kfu.itis.freshnews.android.utils.toDate
import kfu.itis.freshnews.android.widget.FreshNewsImage
import kfu.itis.freshnews.feature.home.domain.model.Article
import kfu.itis.freshnews.feature.home.domain.model.ArticleCategory
import kfu.itis.freshnews.feature.home.presentation.HomeEvent
import kfu.itis.freshnews.feature.home.presentation.HomeState

@Composable
fun HomeView(
    state: HomeState,
    eventHandler: (HomeEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            HomeContent(
                scaffoldPadding = paddingValues,
                state = state,
                eventHandler = eventHandler,
            )
        },
//        containerColor = ThemeProvider.colors.primary,
    )
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun HomeContent(
    scaffoldPadding: PaddingValues,
    state: HomeState,
    eventHandler: (HomeEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding),
    ) {
        LazyColumnSpacer(36.dp)

        item { LatestNewsTitle() }

        item {
            when (state.isLatestArticlesLoading) {
                true -> LatestNewsLoadingView()
                false -> LatestNews(
                    latestNews = state.latestArticles,
                    onArticleClick = { eventHandler(HomeEvent.OnArticleClick(it)) },
                )
            }
        }

        LazyColumnSpacer(16.dp)

        stickyHeader {
            NewsCategories(
                selectedCategory = state.selectedArticleCategory,
                onCategoryClick = { eventHandler(HomeEvent.OnArticleCategoryClick(it)) },
            )
        }

        LazyColumnSpacer(8.dp)

        when (state.isArticlesOfCategoryLoading) {
            true -> NewsOfCategoryLoadingView()
            false -> NewsOfCategory(
                articlesByCategory = state.articlesOfCategory,
                onArticleClick = { eventHandler(HomeEvent.OnArticleClick(it)) },
            )
        }

        LazyColumnSpacer(80.dp)
    }
}

@Composable
private fun LatestNewsTitle() {
    Column {
        Text(
            modifier = Modifier
                .padding(
                    bottom = 4.dp,
                    start = 16.dp,
                ),
            text = stringResource(id = R.string.latest_news),
//            style = ThemeProvider.typography.screenTitle,
//            color = ThemeProvider.colors.onPrimary,
        )
    }
}

@Composable
private fun LatestNews(
    latestNews: List<Article>,
    onArticleClick: (Article) -> Unit,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = latestNews,
            key = { article -> article.title },
        ) { article ->
            NewsItem(
                modifier = Modifier.width(320.dp),
                article = article,
                onClick = { onArticleClick(article) },
            )
        }
    }
}

@Composable
private fun NewsCategories(
    selectedCategory: ArticleCategory,
    onCategoryClick: (ArticleCategory) -> Unit,
) {
    val categories = listOf(
        stringResource(id = R.string.health) to ArticleCategory.HEALTH,
        stringResource(id = R.string.business) to ArticleCategory.BUSINESS,
        stringResource(id = R.string.entertainment) to ArticleCategory.ENTERTAINMENT,
        stringResource(id = R.string.general) to ArticleCategory.GENERAL,
        stringResource(id = R.string.science) to ArticleCategory.SCIENCE,
        stringResource(id = R.string.sports) to ArticleCategory.SPORTS,
        stringResource(id = R.string.technology) to ArticleCategory.TECHNOLOGY,
    )

    Surface(
//        color = ThemeProvider.colors.primary,
    ) {
        LazyRow(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = categories,
                key = { category -> category.second },
            ) { category ->
                NewsCategoryItem(
                    isSelected = selectedCategory == category.second,
                    name = category.first,
                    onClick = { onCategoryClick(category.second) },
                )
            }
        }
    }
}

@Composable
private fun NewsCategoryItem(
    isSelected: Boolean,
    name: String,
    onClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
//            containerColor = ThemeProvider.colors.primary,
        ),
//        border = BorderStroke(
//            width = 1.dp,
//            color = when (isSelected) {
//                true -> ThemeProvider.colors.accent
//                false -> ThemeProvider.colors.outline
//            }
//        ),
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
            text = name,
        )
    }
}

private fun LazyListScope.NewsOfCategory(
    articlesByCategory: List<Article>,
    onArticleClick: (Article) -> Unit,
) {
    items(
        items = articlesByCategory,
        key = { article -> article.title },
    ) { article ->
        NewsItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            article = article,
            onClick = { onArticleClick(article) },
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun NewsItem(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        colors = CardDefaults.cardColors(
//            containerColor = ThemeProvider.colors.primary,
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            FreshNewsImage(
                modifier = Modifier
                    .height(128.dp)
                    .clip(RoundedCornerShape(8.dp)),
                imageUrl = article.urlToImage,
            )
            ColumnSpacer(16.dp)
            Text(
                text = article.publishedAt.toDate(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
//                style = ThemeProvider.typography.date,
//                color = ThemeProvider.colors.onPrimaryVariant,
            )
            ColumnSpacer(8.dp)
            Text(
                text = article.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
//                style = ThemeProvider.typography.cardTitle,
//                color = ThemeProvider.colors.onPrimary,
            )
            if (article.descr.isNotEmpty()) {
                ColumnSpacer(8.dp)
            }
            Text(
                text = article.descr,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
//                style = ThemeProvider.typography.newsDescription,
//                color = ThemeProvider.colors.onPrimary,
            )
            ColumnSpacer(8.dp)
            Text(
                text = article.source,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
//                style = ThemeProvider.typography.cardSupportingText,
//                color = ThemeProvider.colors.onPrimaryVariant,
            )
        }
    }
}
