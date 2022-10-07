package by.dzmitry_lakisau.testapp.ui.model

sealed class NavRoutes(val route: String){
    object UsersList: NavRoutes("users_list")
    object User: NavRoutes("user")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
