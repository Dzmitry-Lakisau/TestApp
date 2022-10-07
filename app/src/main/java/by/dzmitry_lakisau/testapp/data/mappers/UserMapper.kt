package by.dzmitry_lakisau.testapp.data.mappers

import by.dzmitry_lakisau.testapp.data.local.dbModel.UserDb
import by.dzmitry_lakisau.testapp.data.remote.dto.UserDto
import by.dzmitry_lakisau.testapp.domain.model.User

fun UserDto.toUserDb(): UserDb = UserDb(
    id = userId,
    name = name,
    imageUrl = url,
    thumbnailUrl = thumbnailUrl
)

fun UserDb.toUser(): User = User(
    id = id,
    name = name,
    imageUrl = imageUrl,
    thumbnailUrl = thumbnailUrl,
    postsCount = 0
)