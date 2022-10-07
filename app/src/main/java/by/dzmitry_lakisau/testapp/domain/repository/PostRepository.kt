package by.dzmitry_lakisau.testapp.domain.repository

import by.dzmitry_lakisau.testapp.domain.model.Post
import by.dzmitry_lakisau.testapp.domain.model.ResultOf
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPostsByUser(userId: Long): Flow<List<Post>>

    fun getPostsCountByUser(userId: Long): Flow<Int>

    suspend fun fetchPosts(): ResultOf<Unit>
}