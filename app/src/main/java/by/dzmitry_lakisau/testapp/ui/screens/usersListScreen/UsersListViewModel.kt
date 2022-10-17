package by.dzmitry_lakisau.testapp.ui.screens.usersListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.dzmitry_lakisau.testapp.domain.model.ResultOf
import by.dzmitry_lakisau.testapp.domain.usecase.FetchDataUseCase
import by.dzmitry_lakisau.testapp.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val fetchDataUseCase: FetchDataUseCase
) : ViewModel() {

    private var uiState = MutableStateFlow<UsersListUiState>(UsersListUiState.Loading)
    val state = uiState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val result = fetchDataUseCase()
            when (result) {
                is ResultOf.Error -> uiState.value = UsersListUiState.Error(result.message)
                is ResultOf.Success -> getUsers()
            }
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            getUsersUseCase().collectLatest {
                uiState.value = UsersListUiState.Data(it)
            }
        }
    }
}