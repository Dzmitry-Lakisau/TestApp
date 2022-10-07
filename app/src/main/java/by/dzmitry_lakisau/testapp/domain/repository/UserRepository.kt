package by.dzmitry_lakisau.testapp.domain.repository

import by.dzmitry_lakisau.testapp.domain.model.ResultOf
import by.dzmitry_lakisau.testapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<List<User>>

    fun getUser(userId: Long): Flow<User>

    suspend fun fetchUsers(): ResultOf<Unit>
}