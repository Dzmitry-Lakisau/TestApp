package by.dzmitry_lakisau.testapp.ui.screens.usersListScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import by.dzmitry_lakisau.testapp.ui.component.UserItem
import by.dzmitry_lakisau.testapp.ui.model.NavRoutes
import kotlinx.coroutines.delay

@Composable
fun UsersListScreen(
    navController: NavHostController,
    viewModel: UsersListViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    val listState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(uiState.errorMessage) {
        if (uiState.errorMessage.isNotEmpty()) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = uiState.errorMessage
            )
            delay(4000)
            viewModel.resetSnack()
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = uiState.list,
                    key = { it.id }
                ) { user ->
                    UserItem(
                        user = user,
                        onClick = {
                            navController.navigate(NavRoutes.User.withArgs(user.id.toString())) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    }
}