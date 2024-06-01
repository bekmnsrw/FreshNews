package kfu.itis.freshnews.feature.profile.data.mapper

import kfu.itis.freshnews.Profile
import kfu.itis.freshnews.feature.profile.domain.model.UserProfile

fun Profile.toUserProfile(): UserProfile = UserProfile(
    id = id,
    login = login,
)
