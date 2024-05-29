package kfu.itis.freshnews.feature.favorites.domain.usecase

interface RemoveFavoritesArticleUseCase {

    suspend operator fun invoke(id: Int)
}
