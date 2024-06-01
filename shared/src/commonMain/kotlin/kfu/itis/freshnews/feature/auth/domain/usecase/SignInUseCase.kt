package kfu.itis.freshnews.feature.auth.domain.usecase

import kfu.itis.freshnews.feature.auth.domain.model.AuthModel
import kotlinx.coroutines.flow.Flow

interface SignInUseCase {

    operator fun invoke(login: String, password: String): Flow<AuthModel?>
}
