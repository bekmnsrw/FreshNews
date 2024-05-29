package kfu.itis.freshnews.feature.favorites.di

import kfu.itis.freshnews.FreshNews
import kfu.itis.freshnews.feature.favorites.data.FavoritesRepositoryImpl
import kfu.itis.freshnews.feature.favorites.data.datasource.local.LocalFavoritesDataSource
import kfu.itis.freshnews.feature.favorites.domain.FavoritesRepository
import kfu.itis.freshnews.feature.favorites.domain.usecase.AddFavoritesArticleUseCase
import kfu.itis.freshnews.feature.favorites.domain.usecase.GetAllFavoritesArticlesUseCase
import kfu.itis.freshnews.feature.favorites.domain.usecase.GetFavoritesArticleUseCase
import kfu.itis.freshnews.feature.favorites.domain.usecase.RemoveFavoritesArticleUseCase
import kfu.itis.freshnews.feature.favorites.domain.usecase.impl.AddFavoritesArticleUseCaseImpl
import kfu.itis.freshnews.feature.favorites.domain.usecase.impl.GetAllFavoritesArticlesUseCaseImpl
import kfu.itis.freshnews.feature.favorites.domain.usecase.impl.GetFavoritesArticleUseCaseImpl
import kfu.itis.freshnews.feature.favorites.domain.usecase.impl.RemoveFavoritesArticleUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

private const val MODULE_NAME = "favoritesModule"

val favoritesModule = DI.Module(name = MODULE_NAME) {

    bindProvider<LocalFavoritesDataSource> {
        LocalFavoritesDataSource(
            database = instance<FreshNews>(),
        )
    }

    bindProvider<FavoritesRepository> {
        FavoritesRepositoryImpl(
            localFavoritesDataSource = instance<LocalFavoritesDataSource>(),
        )
    }

    bindProvider<GetFavoritesArticleUseCase> {
        GetFavoritesArticleUseCaseImpl(
            favoritesRepository = instance<FavoritesRepository>(),
        )
    }

    bindProvider<AddFavoritesArticleUseCase> {
        AddFavoritesArticleUseCaseImpl(
            favoritesRepository = instance<FavoritesRepository>(),
        )
    }

    bindProvider<RemoveFavoritesArticleUseCase> {
        RemoveFavoritesArticleUseCaseImpl(
            favoritesRepository = instance<FavoritesRepository>(),
        )
    }

    bindProvider<GetAllFavoritesArticlesUseCase> {
        GetAllFavoritesArticlesUseCaseImpl(
            favoritesRepository = instance<FavoritesRepository>(),
        )
    }
}
