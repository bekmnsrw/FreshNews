package kfu.itis.freshnews.android.feature.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import kfu.itis.freshnews.android.designsystem.icon.FreshNewsIcons
import kfu.itis.freshnews.android.designsystem.theme.ThemeProvider
import kfu.itis.freshnews.android.utils.ColumnSpacer
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
        modifier = Modifier.fillMaxSize(),
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
                contentColor = ThemeProvider.colors.buttonContent,
                containerColor = ThemeProvider.colors.buttonContainer,
            ) {
                Icon(
                    imageVector = if (state.isFavorite) FreshNewsIcons.FAVORITE_FILL else FreshNewsIcons.FAVORITE_BORDER,
                    contentDescription = null,
                )
            }
        },
        containerColor = ThemeProvider.colors.background,
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
        LazyColumn(Modifier.fillMaxSize()) {
            item { ArticleDetailsContent(state.articleDetails) }
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
private fun ArticleDetailsContent(articleDetails: ArticleDetails?) {
    articleDetails?.let { article ->
        Column {
            Box(Modifier.wrapContentSize()) {
                FreshNewsImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    imageUrl = article.urlToImage,
                )
            }
            ColumnSpacer(16.dp)
            Column(Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = article.publishedAt.toDate(),
                    style = ThemeProvider.typography.cardSupportingText,
                    color = ThemeProvider.colors.outline,
                )
                ColumnSpacer(8.dp)
                Text(
                    text = article.title,
                    style = ThemeProvider.typography.cardTitle,
                    color = ThemeProvider.colors.mainText,
                )
                ColumnSpacer(8.dp)
                Text(
                    text = article.source,
                    style = ThemeProvider.typography.cardSupportingText,
                    color = ThemeProvider.colors.outline,
                )
                ColumnSpacer(20.dp)
                Text(
                    text = article.description,
                    style = ThemeProvider.typography.cardSupportingText,
                    color = ThemeProvider.colors.outline,
                )
                ColumnSpacer(16.dp)
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
        containerColor = ThemeProvider.colors.background,
        contentColor = ThemeProvider.colors.mainText,
        onClick = onClick,
    ) {
        Icon(
            imageVector = FreshNewsIcons.ARROW_BACK,
            contentDescription = null,
        )
    }
}
