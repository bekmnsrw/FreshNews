package kfu.itis.freshnews.feature.home.domain.usecase

interface RemoveFavoritesArticleUseCase {

    suspend operator fun invoke(id: Int)
}
