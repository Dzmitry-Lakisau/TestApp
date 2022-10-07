package by.dzmitry_lakisau.testapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import by.dzmitry_lakisau.testapp.data.local.dbModel.UserDb
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(users: List<UserDb>)

    @Query("SELECT * FROM users")
    fun getAll(): Flow<List<UserDb>>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUser(userId: Long): Flow<UserDb>
}