package kfu.itis.freshnews.feature.home.di

import io.ktor.client.HttpClient
import kfu.itis.freshnews.feature.home.data.NewsRepositoryImpl
import kfu.itis.freshnews.feature.home.data.datasource.remote.RemoteNewsDataSource
import kfu.itis.freshnews.feature.home.domain.NewsRepository
import kfu.itis.freshnews.feature.home.domain.usecase.GetTopHeadlinesByCategoryUseCase
import kfu.itis.freshnews.feature.home.domain.usecase.GetTopHeadlinesUseCase
import kfu.itis.freshnews.feature.home.domain.usecase.SearchTopHeadlinesByPhraseUseCase
import kfu.itis.freshnews.feature.home.domain.usecase.impl.GetTopHeadlinesByCategoryUseCaseImpl
import kfu.itis.freshnews.feature.home.domain.usecase.impl.GetTopHeadlinesUseCaseImpl
import kfu.itis.freshnews.feature.home.domain.usecase.impl.SearchTopHeadlinesByPhraseUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

private const val MODULE_NAME = "homeModule"

val homeModule = DI.Module(MODULE_NAME) {

    bindProvider<RemoteNewsDataSource> {
        RemoteNewsDataSource(
            httpClient = instance<HttpClient>(),
        )
    }

    bindProvider<NewsRepository> {
        NewsRepositoryImpl(
            remoteNewsDataSource = instance<RemoteNewsDataSource>(),
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
}
