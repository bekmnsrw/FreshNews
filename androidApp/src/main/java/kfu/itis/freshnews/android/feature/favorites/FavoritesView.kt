package kfu.itis.freshnews.android.feature.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kfu.itis.freshnews.android.theme.ThemeProvider
import kfu.itis.freshnews.android.utils.ColumnSpacer
import kfu.itis.freshnews.android.utils.LazyColumnSpacer
import kfu.itis.freshnews.android.utils.toDate
import kfu.itis.freshnews.android.widget.FreshNewsImage
import kfu.itis.freshnews.feature.favorites.domain.model.FavoritesArticle
import kfu.itis.freshnews.feature.favorites.presentation.FavoritesEvent
import kfu.itis.freshnews.feature.favorites.presentation.FavoritesState

@Composable
fun FavoritesView(
    state: FavoritesState,
    eventHandler: (FavoritesEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            FavoritesContent(
                scaffoldPadding = paddingValues,
                favoriteArticles = state.favoritesArticles,
                onArticleClick = { eventHandler(FavoritesEvent.OnArticleClick(it)) }
            )
        }
    )
}

@Composable
private fun FavoritesContent(
    scaffoldPadding: PaddingValues,
    favoriteArticles: List<FavoritesArticle>,
    onArticleClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding)
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 82.dp,
            ),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        LazyColumnSpacer(16.dp)
        items(
            items = favoriteArticles,
            key = { article -> article.id!! }
        ) { article ->
            FavoritesArticleItem(
                favoritesArticle = article,
                onItemClick = onArticleClick,
            )
        }
    }
}

@Composable
private fun FavoritesArticleItem(
    favoritesArticle: FavoritesArticle,
    onItemClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { favoritesArticle.id?.let(onItemClick) },
        colors = CardDefaults.cardColors(
            containerColor = ThemeProvider.colors.primary,
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            FreshNewsImage(
                modifier = Modifier
                    .height(128.dp)
                    .clip(RoundedCornerShape(8.dp)),
                imageUrl = favoritesArticle.imageUrl,
            )
            ColumnSpacer(16.dp)
            Text(
                text = favoritesArticle.publishedAt.toDate(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = ThemeProvider.typography.date,
                color = ThemeProvider.colors.onPrimaryVariant,
            )
            ColumnSpacer(8.dp)
            Text(
                text = favoritesArticle.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = ThemeProvider.typography.cardTitle,
                color = ThemeProvider.colors.onPrimary,
            )
            if (favoritesArticle.description.isNotEmpty()) {
                ColumnSpacer(8.dp)
            }
            Text(
                text = favoritesArticle.description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = ThemeProvider.typography.newsDescription,
                color = ThemeProvider.colors.onPrimary,
            )
            ColumnSpacer(8.dp)
            Text(
                text = favoritesArticle.source,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = ThemeProvider.typography.cardSupportingText,
                color = ThemeProvider.colors.onPrimaryVariant,
            )
        }
    }
}
