package by.dzmitry_lakisau.testapp.ui.screens.usersListScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import by.dzmitry_lakisau.testapp.ui.component.UserItem
import by.dzmitry_lakisau.testapp.ui.model.NavRoutes

@Composable
fun UsersListScreen(
    navController: NavHostController,
    viewModel: UsersListViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    val listState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (state) {
                is UsersListUiState.Error -> {
                    ErrorMessage(state.message)
                }
                is UsersListUiState.Loading -> {
                    ProgressBar()
                }
                is UsersListUiState.Data -> {
                    LazyColumn(
                        state = listState,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            items = state.list,
                            key = { it.id }
                        ) { user ->
                            UserItem(
                                user = user,
                                onClick = {
                                    navController.navigate(NavRoutes.User.withArgs(user.id.toString())) { launchSingleTop = true }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ErrorMessage(message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}