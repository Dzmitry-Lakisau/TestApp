package by.dzmitry_lakisau.testapp.ui.screens.usersListScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.dzmitry_lakisau.testapp.domain.model.ResultOf
import by.dzmitry_lakisau.testapp.domain.usecase.FetchDataUseCase
import by.dzmitry_lakisau.testapp.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val fetchDataUseCase: FetchDataUseCase
) : ViewModel() {

    var uiState by mutableStateOf(UsersListUiState())
        private set

    init {
        fetchData()
        getUsers()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val result = fetchDataUseCase()
            if (result is ResultOf.Error) {
                uiState = uiState.copy(
                    errorMessage = result.message
                )
            }
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase().collectLatest {
                uiState = uiState.copy(
                    list = it
                )
            }
        }
    }

    fun resetSnack() {
        uiState = uiState.copy(
            errorMessage = ""
        )
    }
}