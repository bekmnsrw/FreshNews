package kfu.itis.freshnews.feature.profile.di

import com.russhwolf.settings.Settings
import kfu.itis.freshnews.FreshNews
import kfu.itis.freshnews.feature.profile.data.ProfileRepositoryImpl
import kfu.itis.freshnews.feature.profile.data.datasource.local.LocalProfileDataSource
import kfu.itis.freshnews.feature.profile.domain.ProfileRepository
import kfu.itis.freshnews.feature.profile.domain.usecase.ChangeDarkModeUseCase
import kfu.itis.freshnews.feature.profile.domain.usecase.DeleteProfileUseCase
import kfu.itis.freshnews.feature.profile.domain.usecase.GetProfileUseCase
import kfu.itis.freshnews.feature.profile.domain.usecase.IsDarkModeEnabledUseCase
import kfu.itis.freshnews.feature.profile.domain.usecase.impl.ChangeDarkModeUseCaseImpl
import kfu.itis.freshnews.feature.profile.domain.usecase.impl.DeleteProfileUseCaseImpl
import kfu.itis.freshnews.feature.profile.domain.usecase.impl.GetProfileUseCaseImpl
import kfu.itis.freshnews.feature.profile.domain.usecase.impl.IsDarkModeEnabledUseCaseImpl
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

private const val MODULE_NAME = "profileModule"

val profileModule = DI.Module(MODULE_NAME) {

    bindProvider<LocalProfileDataSource> {
        LocalProfileDataSource(
            database = instance<FreshNews>(),
        )
    }

    bindProvider<ProfileRepository> {
        ProfileRepositoryImpl(
            localProfileDataSource = instance<LocalProfileDataSource>(),
            settings = instance<Settings>(),
        )
    }

    bindProvider<DeleteProfileUseCase> {
        DeleteProfileUseCaseImpl(
            profileRepository = instance<ProfileRepository>(),
        )
    }

    bindProvider<GetProfileUseCase> {
        GetProfileUseCaseImpl(
            profileRepository = instance<ProfileRepository>(),
        )
    }

    bindProvider<IsDarkModeEnabledUseCase> {
        IsDarkModeEnabledUseCaseImpl(
            profileRepository = instance<ProfileRepository>(),
        )
    }

    bindProvider<ChangeDarkModeUseCase> {
        ChangeDarkModeUseCaseImpl(
            profileRepository = instance<ProfileRepository>(),
        )
    }
}
