package kfu.itis.freshnews.feature.auth.di

import com.russhwolf.settings.Settings
import kfu.itis.freshnews.FreshNews
import kfu.itis.freshnews.feature.auth.data.AuthRepositoryImpl
import kfu.itis.freshnews.feature.auth.data.datasource.local.LocalAuthDataSource
import kfu.itis.freshnews.feature.auth.domain.AuthRepository
import kfu.itis.freshnews.feature.auth.domain.usecase.GetUserIdUseCase
import kfu.itis.freshnews.feature.auth.domain.usecase.IsWelcomeScreenShownUseCase
import kfu.itis.freshnews.feature.auth.domain.usecase.SaveUserIdUseCase
import kfu.itis.freshnews.feature.auth.domain.usecase.SetWelcomeScreenShownUseCase
import kfu.itis.freshnews.feature.auth.domain.usecase.SignInUseCase
import kfu.itis.freshnews.feature.auth.domain.usecase.SignUpUseCase
import kfu.itis.freshnews.feature.auth.domain.usecase.impl.GetUserIdUseCaseImpl
import kfu.itis.freshnews.feature.auth.domain.usecase.impl.IsWelcomeScreenShownUseCaseImpl
import kfu.itis.freshnews.feature.auth.domain.usecase.impl.SaveUserIdUseCaseImpl
import kfu.itis.freshnews.feature.auth.domain.usecase.impl.SetWelcomeScreenShowUseCaseImpl
import kfu.itis.freshnews.feature.auth.domain.usecase.impl.SignInUseCaseImpl
import kfu.itis.freshnews.feature.auth.domain.usecase.impl.SignUpUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

private const val MODULE_NAME = "authModule"

val authModule = DI.Module(MODULE_NAME) {

    bindProvider<LocalAuthDataSource> {
        LocalAuthDataSource(
            database = instance<FreshNews>(),
        )
    }

    bindProvider<AuthRepository> {
        AuthRepositoryImpl(
            localAuthDataSource = instance<LocalAuthDataSource>(),
            settings = instance<Settings>(),
        )
    }

    bindProvider<SignInUseCase> {
        SignInUseCaseImpl(
            authRepository = instance<AuthRepository>()
        )
    }

    bindProvider<SignUpUseCase> {
        SignUpUseCaseImpl(
            authRepository = instance<AuthRepository>(),
        )
    }

    bindSingleton<SaveUserIdUseCase> {
        SaveUserIdUseCaseImpl(
            authRepository = instance<AuthRepository>(),
        )
    }

    bindSingleton<GetUserIdUseCase> {
        GetUserIdUseCaseImpl(
            authRepository = instance<AuthRepository>(),
        )
    }

    bindSingleton<IsWelcomeScreenShownUseCase> {
        IsWelcomeScreenShownUseCaseImpl(
            authRepository = instance<AuthRepository>(),
        )
    }

    bindSingleton<SetWelcomeScreenShownUseCase> {
        SetWelcomeScreenShowUseCaseImpl(
            authRepository = instance<AuthRepository>(),
        )
    }
}
