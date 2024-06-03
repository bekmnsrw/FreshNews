package kfu.itis.freshnews.core.di

import kfu.itis.freshnews.feature.auth.presentation.AuthViewModel
import kfu.itis.freshnews.feature.details.presentation.DetailsViewModel
import kfu.itis.freshnews.feature.favorites.presentation.FavoritesViewModel
import kfu.itis.freshnews.feature.home.presentation.HomeViewModel
import kfu.itis.freshnews.feature.profile.presentation.ProfileViewModel
import kfu.itis.freshnews.feature.splash.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {

    viewModel<HomeViewModel> { HomeViewModel() }
    viewModel<FavoritesViewModel> { FavoritesViewModel() }
    viewModel<ProfileViewModel> { ProfileViewModel() }
    viewModel<AuthViewModel> { AuthViewModel() }
    viewModel<SplashViewModel> { SplashViewModel() }
    viewModel<DetailsViewModel> { DetailsViewModel() }
}
