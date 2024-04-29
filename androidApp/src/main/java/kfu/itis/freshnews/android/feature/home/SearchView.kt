package kfu.itis.freshnews.android.feature.home

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kfu.itis.freshnews.android.R
import kfu.itis.freshnews.android.theme.FreshNewsIcons
import kfu.itis.freshnews.android.widget.FreshNewsIcon
import kfu.itis.freshnews.android.widget.FreshNewsIconButton
import kfu.itis.freshnews.feature.home.domain.model.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchToolbar(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    isSearchActive: Boolean,
    onActiveChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
    onArticleClick: (String) -> Unit,
    searchedArticles: List<Article>,
    isLoading: Boolean,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = if (isSearchActive) 0.dp else 16.dp,
                end = if (isSearchActive) 0.dp else 16.dp,
                bottom = if (isSearchActive) 0.dp else 8.dp,
            ),
        query = searchQuery,
        onQueryChange = { query -> onQueryChange(query) },
        onSearch = { query ->
            onSearch(query)
            keyboardController?.hide()
        },
        active = isSearchActive,
        onActiveChange = { isActive -> onActiveChange(isActive) },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
            )
        },
        leadingIcon = {
            when (isSearchActive) {
                true -> FreshNewsIconButton(
                    onClick = {
                        onActiveChange(false)
                        onQueryChange("")
                    },
                    icon = FreshNewsIcons.ARROW_BACK,
                )
                false -> FreshNewsIcon(
                    icon = FreshNewsIcons.SEARCH,
                )
            }
        },
        trailingIcon = {
            if (isSearchActive && searchQuery.isNotEmpty()) {
                FreshNewsIconButton(
                    onClick = { onQueryChange("") },
                    icon = FreshNewsIcons.CLOSE,
                )
            }
        },
    ) {
        when {
            isLoading -> SearchedNewsLoadingView()
            searchedArticles.isEmpty() -> NoSearchedNews()
            searchedArticles.isNotEmpty() -> SearchedNews(
                searchedArticles = searchedArticles,
                onArticleClick = onArticleClick,
            )
        }
    }
}

@Composable
private fun SearchedNews(
    searchedArticles: List<Article>,
    onArticleClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            items = searchedArticles,
            key = { article -> article.title },
        ) { article ->
            SearchedNewsItem(
                article = article,
                onClick = { onArticleClick(article.title) },
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun SearchedNewsItem(
    article: Article,
    onClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(article.title) },
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                ),
            text = article.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        HorizontalDivider()
    }
}

@Composable
private fun NoSearchedNews() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.no_search_results),
        )
    }
}
