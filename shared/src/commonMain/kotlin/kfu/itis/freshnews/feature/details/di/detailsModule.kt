package kfu.itis.freshnews.feature.details.di

import io.ktor.client.HttpClient
import kfu.itis.freshnews.feature.details.data.DetailsRepositoryImpl
import kfu.itis.freshnews.feature.details.data.datasource.remote.RemoteDetailsDataSource
import kfu.itis.freshnews.feature.details.domain.DetailsRepository
import kfu.itis.freshnews.feature.details.domain.usecase.GetArticleDetailsUseCase
import kfu.itis.freshnews.feature.details.domain.usecase.impl.GetArticleDetailsUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

private const val MODULE_NAME = "detailsModule"

val detailsModule = DI.Module(name = MODULE_NAME) {

    bindProvider<RemoteDetailsDataSource> {
        RemoteDetailsDataSource(
            httpClient = instance<HttpClient>(),
        )
    }

    bindProvider<DetailsRepository> {
        DetailsRepositoryImpl(
            remoteDetailsDataSource = instance<RemoteDetailsDataSource>(),
        )
    }

    bindProvider<GetArticleDetailsUseCase> {
        GetArticleDetailsUseCaseImpl(
            detailsRepository = instance<DetailsRepository>(),
        )
    }
}
