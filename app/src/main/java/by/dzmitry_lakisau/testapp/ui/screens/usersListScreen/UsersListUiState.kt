package by.dzmitry_lakisau.testapp.ui.screens.usersListScreen

import by.dzmitry_lakisau.testapp.domain.model.User

sealed class UsersListUiState {

    object Loading : UsersListUiState()

    data class Error(val message: String) : UsersListUiState()

    data class Data(val list: List<User>) : UsersListUiState()
}