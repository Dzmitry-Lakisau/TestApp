package by.dzmitry_lakisau.testapp.data.mappers

import by.dzmitry_lakisau.testapp.data.local.dbModel.PostDb
import by.dzmitry_lakisau.testapp.data.remote.dto.PostDto
import by.dzmitry_lakisau.testapp.domain.model.Post

fun PostDto.toPostDb(): PostDb = PostDb(
    id = id,
    title = title,
    body = body,
    userId = userId
)

fun PostDb.toPost(): Post = Post(
    id = id,
    title = title,
    body = body
)