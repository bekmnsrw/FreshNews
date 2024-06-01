package kfu.itis.freshnews.core.di

import kfu.itis.freshnews.feature.auth.presentation.AuthViewModel
import kfu.itis.freshnews.feature.favorites.presentation.FavoritesViewModel
import kfu.itis.freshnews.feature.home.presentation.HomeViewModel
import kfu.itis.freshnews.feature.profile.presentation.ProfileViewModel
import kfu.itis.freshnews.feature.splash.presentation.SplashViewModel
import kotlinx.coroutines.NonCancellable.get
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {

    single { HomeViewModel() }
    single { FavoritesViewModel() }
    single { ProfileViewModel() }
    single { AuthViewModel() }
    single { SplashViewModel() }
}

object ViewModels : KoinComponent {
    fun getHomeViewModel() = get<HomeViewModel>()
    fun getFavoritesViewModel() = get<FavoritesViewModel>()
    fun getProfileViewModel() = get<ProfileViewModel>()
    fun getAuthViewModel() = get<AuthViewModel>()
    fun getSplashViewModel() = get<SplashViewModel>()
}
