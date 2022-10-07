package by.dzmitry_lakisau.testapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.dzmitry_lakisau.testapp.data.local.dbModel.PostDb
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<PostDb>)

    @Query("SELECT * FROM posts WHERE userId = :userId")
    fun getAllByUser(userId: Long): Flow<List<PostDb>>

    @Query("SELECT COUNT(id) FROM posts WHERE userId=:userId")
    fun getPostsCountByUser(userId: Long): Flow<Int>
}