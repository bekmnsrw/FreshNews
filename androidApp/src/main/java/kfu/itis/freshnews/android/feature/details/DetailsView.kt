package kfu.itis.freshnews.android.feature.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kfu.itis.freshnews.android.theme.FreshNewsIcons
import kfu.itis.freshnews.android.theme.ThemeProvider
import kfu.itis.freshnews.android.utils.toDate
import kfu.itis.freshnews.android.widget.FreshNewsImage
import kfu.itis.freshnews.feature.details.domain.model.ArticleDetails
import kfu.itis.freshnews.feature.details.presentation.DetailsEvent
import kfu.itis.freshnews.feature.details.presentation.DetailsState

@Composable
fun DetailsView(
    state: DetailsState,
    eventHandler: (DetailsEvent) -> Unit,
) {
    Scaffold(
        content = { paddingValues ->
            DetailsContent(
                scaffoldPadding = paddingValues,
                state = state,
                eventHandler = eventHandler,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { eventHandler(DetailsEvent.OnAddToFavoritesClick) },
                contentColor = ThemeProvider.colors.primary,
                containerColor = ThemeProvider.colors.accent,
            ) {
                Icon(
                    imageVector = if (state.isFavorite) {
                        FreshNewsIcons.FAVORITE_FILL
                    } else {
                        FreshNewsIcons.FAVORITE_BORDER
                    },
                    contentDescription = null,
                )
            }
        }
    )
}

@Composable
private fun DetailsContent(
    scaffoldPadding: PaddingValues,
    state: DetailsState,
    eventHandler: (DetailsEvent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(scaffoldPadding)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                ArticleDetailsContent(
                    articleDetails = state.articleDetails,
                )
            }
        }
        ArrowBack(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            onClick = { eventHandler(DetailsEvent.OnArrowBackClick) },
        )
    }
}

@Composable
private fun ArticleDetailsContent(
    articleDetails: ArticleDetails?,
) {
    articleDetails?.let { article ->
        Column {
            Box(modifier = Modifier.wrapContentSize()) {
                FreshNewsImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    imageUrl = article.urlToImage,
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = article.publishedAt.toDate(),
                    style = ThemeProvider.typography.date,
                    color = ThemeProvider.colors.onPrimaryVariant,
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    text = article.title,
                    style = ThemeProvider.typography.cardTitle,
                    color = ThemeProvider.colors.onPrimaryVariant,
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    text = article.source,
                    style = ThemeProvider.typography.cardSupportingText,
                    color = ThemeProvider.colors.onPrimaryVariant,
                )
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
                Text(
                    text = article.description,
                    style = ThemeProvider.typography.newsDescription,
                    color = ThemeProvider.colors.onPrimary,
                )
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
            }
        }
    }
}

@Composable
fun ArrowBack(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    SmallFloatingActionButton(
        modifier = modifier,
        containerColor = ThemeProvider.colors.cardTransparent,
        contentColor = ThemeProvider.colors.onPrimary,
        onClick = onClick,
    ) {
        Icon(
            imageVector = FreshNewsIcons.ARROW_BACK,
            contentDescription = null,
        )
    }
}
