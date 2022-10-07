package by.dzmitry_lakisau.testapp.domain.model

data class User(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val thumbnailUrl: String,
    val postsCount: Int
)
