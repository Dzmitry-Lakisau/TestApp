package by.dzmitry_lakisau.testapp.data.remote.dto

import com.squareup.moshi.Json

data class PostDto(
    @Json(name = "userId")
    var userId: Long,
    @Json(name = "id")
    var id: Long,
    @Json(name = "title")
    var title: String,
    @Json(name = "body")
    var body: String
)
