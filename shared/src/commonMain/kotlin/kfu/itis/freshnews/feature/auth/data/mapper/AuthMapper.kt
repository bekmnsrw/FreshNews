package kfu.itis.freshnews.feature.auth.data.mapper

import kfu.itis.freshnews.Profile
import kfu.itis.freshnews.feature.auth.domain.model.AuthModel

fun Profile.toAuthModel(): AuthModel = AuthModel(
    id = id,
    login = login,
    password = password,
)
