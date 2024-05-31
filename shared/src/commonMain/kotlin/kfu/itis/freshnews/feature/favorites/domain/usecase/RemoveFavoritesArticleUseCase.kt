package kfu.itis.freshnews.feature.favorites.domain.usecase

interface RemoveFavoritesArticleUseCase {

    suspend operator fun invoke(
        title: String,
        userId: Long,
    )
}
