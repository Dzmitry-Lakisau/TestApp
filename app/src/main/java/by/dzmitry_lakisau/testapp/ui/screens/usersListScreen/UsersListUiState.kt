package by.dzmitry_lakisau.testapp.ui.screens.usersListScreen

import by.dzmitry_lakisau.testapp.domain.model.User

data class UsersListUiState(
    val list: List<User> = emptyList(),
    val errorMessage: String = ""
)