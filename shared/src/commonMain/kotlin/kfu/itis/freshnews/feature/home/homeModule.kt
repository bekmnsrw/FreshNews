package kfu.itis.freshnews.feature.home

import io.ktor.client.HttpClient
import kfu.itis.freshnews.feature.home.data.NewsRepositoryImpl
import kfu.itis.freshnews.feature.home.data.RemoteNewsDataSource
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

val homeModule = DI.Module(name = MODULE_NAME) {

    bindProvider<RemoteNewsDataSource> {
        provideRemoteNewsDataSource(
            httpClient = instance<HttpClient>()
        )
    }

    bindProvider<NewsRepository> {
        provideNewsRepository(
            remoteNewsDataSource = instance<RemoteNewsDataSource>()
        )
    }

    bindProvider<GetTopHeadlinesUseCase> {
        provideGetTopHeadlinesUseCase(
            newsRepository = instance<NewsRepository>()
        )
    }

    bindProvider<GetTopHeadlinesByCategoryUseCase> {
        provideGetTopHeadlinesByCategoryUseCase(
            newsRepository = instance<NewsRepository>()
        )
    }

    bindProvider<SearchTopHeadlinesByPhraseUseCase> {
        provideSearchTopHeadlinesByPhraseUseCase(
            newsRepository = instance<NewsRepository>()
        )
    }
}

private fun provideRemoteNewsDataSource(
    httpClient: HttpClient
): RemoteNewsDataSource = RemoteNewsDataSource(
    httpClient = httpClient
)

private fun provideNewsRepository(
    remoteNewsDataSource: RemoteNewsDataSource
): NewsRepository = NewsRepositoryImpl(
    remoteNewsDataSource = remoteNewsDataSource
)

private fun provideGetTopHeadlinesUseCase(
    newsRepository: NewsRepository
): GetTopHeadlinesUseCase = GetTopHeadlinesUseCaseImpl(
    newsRepository = newsRepository
)

private fun provideGetTopHeadlinesByCategoryUseCase(
    newsRepository: NewsRepository
): GetTopHeadlinesByCategoryUseCase = GetTopHeadlinesByCategoryUseCaseImpl(
    newsRepository = newsRepository
)

private fun provideSearchTopHeadlinesByPhraseUseCase(
    newsRepository: NewsRepository
): SearchTopHeadlinesByPhraseUseCase = SearchTopHeadlinesByPhraseUseCaseImpl(
    newsRepository = newsRepository
)
