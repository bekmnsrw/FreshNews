package kfu.itis.freshnews.feature.home.di

import io.ktor.client.HttpClient
import kfu.itis.freshnews.FreshNews
import kfu.itis.freshnews.feature.home.data.NewsRepositoryImpl
import kfu.itis.freshnews.feature.home.data.datasource.local.LocalNewsDataSource
import kfu.itis.freshnews.feature.home.data.datasource.remote.RemoteNewsDataSource
import kfu.itis.freshnews.feature.home.domain.NewsRepository
import kfu.itis.freshnews.feature.home.domain.usecase.AddFavoritesArticleUseCase
import kfu.itis.freshnews.feature.home.domain.usecase.GetTopHeadlinesByCategoryUseCase
import kfu.itis.freshnews.feature.home.domain.usecase.GetTopHeadlinesUseCase
import kfu.itis.freshnews.feature.home.domain.usecase.RemoveFavoritesArticleUseCase
import kfu.itis.freshnews.feature.home.domain.usecase.SearchTopHeadlinesByPhraseUseCase
import kfu.itis.freshnews.feature.home.domain.usecase.impl.AddFavoritesArticleUseCaseImpl
import kfu.itis.freshnews.feature.home.domain.usecase.impl.GetTopHeadlinesByCategoryUseCaseImpl
import kfu.itis.freshnews.feature.home.domain.usecase.impl.GetTopHeadlinesUseCaseImpl
import kfu.itis.freshnews.feature.home.domain.usecase.impl.RemoveFavoritesArticleUseCaseImpl
import kfu.itis.freshnews.feature.home.domain.usecase.impl.SearchTopHeadlinesByPhraseUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

private const val MODULE_NAME = "homeModule"

val homeModule = DI.Module(name = MODULE_NAME) {

    bindProvider<RemoteNewsDataSource> {
        RemoteNewsDataSource(
            httpClient = instance<HttpClient>(),
        )
    }

    bindProvider<LocalNewsDataSource> {
        LocalNewsDataSource(
            database = instance<FreshNews>(),
        )
    }

    bindProvider<NewsRepository> {
        NewsRepositoryImpl(
            remoteNewsDataSource = instance<RemoteNewsDataSource>(),
            localNewsDataSource = instance<LocalNewsDataSource>(),
        )
    }

    bindProvider<GetTopHeadlinesUseCase> {
        GetTopHeadlinesUseCaseImpl(
            newsRepository = instance<NewsRepository>(),
        )
    }

    bindProvider<GetTopHeadlinesByCategoryUseCase> {
        GetTopHeadlinesByCategoryUseCaseImpl(
            newsRepository = instance<NewsRepository>(),
        )
    }

    bindProvider<SearchTopHeadlinesByPhraseUseCase> {
        SearchTopHeadlinesByPhraseUseCaseImpl(
            newsRepository = instance<NewsRepository>(),
        )
    }

    bindProvider<AddFavoritesArticleUseCase> {
        AddFavoritesArticleUseCaseImpl(
            newsRepository = instance<NewsRepository>(),
        )
    }

    bindProvider<RemoveFavoritesArticleUseCase> {
        RemoveFavoritesArticleUseCaseImpl(
            newsRepository = instance<NewsRepository>(),
        )
    }
}
