package kfu.itis.freshnews.feature.auth.data.mapper

import kfu.itis.freshnews.Profile
import kfu.itis.freshnews.feature.auth.domain.model.UserProfile

fun Profile.toUserProfile(): UserProfile = UserProfile(
    id = id.toInt(),
    login = login,
    password = password,
)
