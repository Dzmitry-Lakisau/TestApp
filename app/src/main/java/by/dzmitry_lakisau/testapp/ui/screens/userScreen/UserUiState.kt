package by.dzmitry_lakisau.testapp.ui.screens.userScreen

import by.dzmitry_lakisau.testapp.domain.model.Post

data class UserUiState(
    val userId: Long = 0,
    val imageUrl: String ="",
    val posts: List<Post> = emptyList()
)
