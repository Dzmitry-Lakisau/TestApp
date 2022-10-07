package by.dzmitry_lakisau.testapp.data.repository

import by.dzmitry_lakisau.testapp.data.local.dao.UserDao
import by.dzmitry_lakisau.testapp.data.mappers.toUser
import by.dzmitry_lakisau.testapp.data.mappers.toUserDb
import by.dzmitry_lakisau.testapp.data.remote.service.ApiService
import by.dzmitry_lakisau.testapp.domain.model.ResultOf
import by.dzmitry_lakisau.testapp.domain.model.User
import by.dzmitry_lakisau.testapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) : UserRepository {

    override fun getUsers(): Flow<List<User>> =
        userDao.getAll().map { it.map { userDb -> userDb.toUser() } }

    override fun getUser(userId: Long): Flow<User> =
        userDao.getUser(userId).map { it.toUser() }

    override suspend fun fetchUsers(): ResultOf<Unit> {
        return try {
            val response = apiService.getUsers()
            if (response.isSuccessful) {
                response.body()?.let {
                    userDao.insert(it.map { userDto -> userDto.toUserDb() })
                }
                ResultOf.Success(Unit)
            } else {
                ResultOf.Error(
                    response.errorBody()?.toString() ?: "Something wrong with fetch Users"
                )
            }
        } catch (e: Exception) {
            ResultOf.Error("Check INTERNET connection")
        }
    }
}

