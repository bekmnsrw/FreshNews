package kfu.itis.freshnews.feature.details.presentation

import kfu.itis.freshnews.core.di.PlatformSDK
import kfu.itis.freshnews.core.firebase.FirebaseCrashlyticsBinding
import kfu.itis.freshnews.core.viewmodel.BaseViewModel
import kfu.itis.freshnews.feature.details.data.mapper.toFavoritesArticle
import kfu.itis.freshnews.feature.details.domain.usecase.GetArticleDetailsUseCase
import kfu.itis.freshnews.feature.favorites.domain.usecase.AddFavoritesArticleUseCase
import kotlinx.coroutines.launch

class DetailsViewModel : BaseViewModel<DetailsState, DetailsAction, DetailsEvent>(
    initialState = DetailsState(),
) {

    private val getArticleDetailsUseCase: GetArticleDetailsUseCase by PlatformSDK.lazyInstance()
    private val firebaseCrashlyticsBinding: FirebaseCrashlyticsBinding by PlatformSDK.lazyInstance()
    private val addFavoritesArticleUseCase: AddFavoritesArticleUseCase by PlatformSDK.lazyInstance()

    override fun handleEvent(event: DetailsEvent) = when (event) {
        is DetailsEvent.OnInit -> onInit(event.title)
        DetailsEvent.OnAddToFavoritesClick -> onAddToFavoritesClick()
        DetailsEvent.OnArrowBackClick -> onArrowBackClick()
    }

    private fun onInit(title: String) {
        scope.launch {
            try {
                state = state.copy(isLoading = true)
                val articleDetails = getArticleDetailsUseCase(title)
                state = state.copy(articleDetails = articleDetails)
            } catch (e: Throwable) {
                handleError(e)
            } finally {
                state = state.copy(isLoading = false)
            }
        }
    }

    private fun onAddToFavoritesClick() {
        scope.launch {
            try {
                state.articleDetails?.let { details ->
                    addFavoritesArticleUseCase(details.toFavoritesArticle())
                }
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun onArrowBackClick() {
        scope.launch {
            try {
                action = DetailsAction.NavigateBack
            } catch (e: Throwable) {
                handleError(e)
            }
        }
    }

    private fun handleError(e: Throwable) {
        state = state.copy(error = e)
        action = DetailsAction.ShowError("Oops, something went wrong")
        firebaseCrashlyticsBinding.sendNonFatalErrorReport(e)
    }
}
