package by.dzmitry_lakisau.testapp.domain.usecase

import by.dzmitry_lakisau.testapp.domain.model.User
import by.dzmitry_lakisau.testapp.domain.repository.PostRepository
import by.dzmitry_lakisau.testapp.domain.repository.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(): Flow<List<User>> {
        val list = userRepository.getUsers()
        val postsCount = list.map {
            it.pmap { user ->
                postRepository.getPostsCountByUser(user.id).first()
            }
        }
        val users = list.zip(postsCount) { listUsers, listCounts ->
            listUsers.zip(listCounts) { user, count ->
                user.copy(postsCount = count)
            }
        }
        return users
    }
}

suspend fun <A, B> Iterable<A>.pmap(f: suspend (A) -> B): List<B> = coroutineScope {
    map { async { f(it) } }.awaitAll()
}