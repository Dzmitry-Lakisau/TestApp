package by.dzmitry_lakisau.testapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import by.dzmitry_lakisau.testapp.ui.model.NavRoutes
import by.dzmitry_lakisau.testapp.ui.screens.userScreen.UserScreen
import by.dzmitry_lakisau.testapp.ui.screens.usersListScreen.UsersListScreen
import by.dzmitry_lakisau.testapp.util.ARG_USER_ID

@Composable
fun AppNavigationHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NavRoutes.UsersList.route) {

        composable(
            route = NavRoutes.UsersList.route,
        ) {
            UsersListScreen(
                navController = navController
            )
        }

        composable(
            route = NavRoutes.User.route + "/{$ARG_USER_ID}",
            arguments = listOf(
                navArgument(ARG_USER_ID) {
                    type = NavType.LongType
                }
            )
        ) {
            UserScreen()
        }
    }
}