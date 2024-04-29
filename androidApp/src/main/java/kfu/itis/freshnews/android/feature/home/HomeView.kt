package kfu.itis.freshnews.android.feature.home

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kfu.itis.freshnews.android.R
import kfu.itis.freshnews.android.utils.LazyColumnSpacer
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
        topBar = {
            SearchToolbar(
                searchQuery = state.searchQuery,
                onQueryChange = { eventHandler(HomeEvent.OnQueryChange(it)) },
                isSearchActive = state.isSearchActive,
                onActiveChange = { eventHandler(HomeEvent.OnActiveChange(it)) },
                onSearch = { eventHandler(HomeEvent.OnSearch(it)) },
                onArticleClick = { eventHandler(HomeEvent.OnArticleClick(it)) },
                searchedArticles = state.searchedArticles,
                isLoading = state.isSearchedArticlesLoading,
            )
        },
        content = { paddingValues ->
            HomeContent(
                scaffoldPadding = paddingValues,
                state = state,
                eventHandler = eventHandler,
            )
        },
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
        )
    }
}

@Composable
private fun LatestNews(
    latestNews: List<Article>,
    onArticleClick: (String) -> Unit,
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
                modifier = Modifier
                    .height(240.dp)
                    .width(320.dp),
                article = article,
                onClick = { onArticleClick(article.title) },
            )
        }
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
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
    ) {
        Column {
            FreshNewsImage(
                modifier = Modifier.weight(2f),
                imageUrl = article.urlToImage,
            )
            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                Text(
                    text = article.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = article.source.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
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

    Surface {
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
        border = if (isSelected) BorderStroke(1.dp, Color.Blue) else null,
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
    onArticleClick: (String) -> Unit,
) {
    items(
        items = articlesByCategory,
        key = { article -> article.title },
    ) { article ->
        NewsItem(
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            article = article,
            onClick = { onArticleClick(article.title) },
        )
        Spacer(
            modifier = Modifier.height(8.dp),
        )
    }
}
