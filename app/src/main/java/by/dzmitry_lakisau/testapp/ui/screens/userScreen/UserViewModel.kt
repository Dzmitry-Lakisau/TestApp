package by.dzmitry_lakisau.testapp.ui.screens.userScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.dzmitry_lakisau.testapp.domain.usecase.GetPostsByUserUseCase
import by.dzmitry_lakisau.testapp.domain.usecase.GetUserUseCase
import by.dzmitry_lakisau.testapp.util.ARG_USER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase,
    private val getPostsByUserUseCase: GetPostsByUserUseCase
) : ViewModel() {

    var uiState by mutableStateOf(UserUiState())
        private set

    init {
        val userId = savedStateHandle.get<Long>(ARG_USER_ID)
        userId?.let {
            getUser(it)
            getPosts(it)
        }
    }

    private fun getUser(userId: Long) {
        viewModelScope.launch {
            getUserUseCase(userId).collectLatest {
                uiState = uiState.copy(
                    imageUrl = it.imageUrl
                )
            }
        }
    }

    private fun getPosts(userId: Long) {
        viewModelScope.launch {
            getPostsByUserUseCase(userId).collectLatest {
                uiState = uiState.copy(
                    posts = it
                )
            }
        }
    }

}