package by.dzmitry_lakisau.testapp.data.remote.dto

import com.squareup.moshi.Json

data class UserDto(
    @Json(name = "albumId") 
    var albumId: Long,
    @Json(name = "userId")
    var userId: Long,
    @Json(name = "name") 
    var name: String,
    @Json(name = "url")
    var url: String,
    @Json(name = "thumbnailUrl")
    var thumbnailUrl: String
)
