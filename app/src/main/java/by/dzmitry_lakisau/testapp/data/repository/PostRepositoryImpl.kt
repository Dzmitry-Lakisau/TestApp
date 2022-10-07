package by.dzmitry_lakisau.testapp.data.repository

import by.dzmitry_lakisau.testapp.data.local.dao.PostDao
import by.dzmitry_lakisau.testapp.data.mappers.toPost
import by.dzmitry_lakisau.testapp.data.mappers.toPostDb
import by.dzmitry_lakisau.testapp.data.remote.service.ApiService
import by.dzmitry_lakisau.testapp.domain.model.Post
import by.dzmitry_lakisau.testapp.domain.model.ResultOf
import by.dzmitry_lakisau.testapp.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val postDao: PostDao
) : PostRepository {

    override fun getPostsByUser(userId: Long): Flow<List<Post>> =
        postDao.getAllByUser(userId).map { it.map { postDb -> postDb.toPost() } }

    override fun getPostsCountByUser(userId: Long): Flow<Int> =
        postDao.getPostsCountByUser(userId)

    override suspend fun fetchPosts(): ResultOf<Unit> {
        return try {
            val response = apiService.getPosts()
            if (response.isSuccessful) {
                response.body()?.let {
                    postDao.insert(it.map { postDto -> postDto.toPostDb() })
                }
                ResultOf.Success(Unit)
            } else {
                ResultOf.Error(
                    response.errorBody()?.toString() ?: "Something wrong with fetch Posts"
                )
            }
        } catch (e: Exception) {
            ResultOf.Error("Check INTERNET connection")
        }
    }
}