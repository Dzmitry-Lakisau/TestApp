package by.dzmitry_lakisau.testapp.domain.usecase

import by.dzmitry_lakisau.testapp.domain.model.ResultOf
import by.dzmitry_lakisau.testapp.domain.repository.PostRepository
import by.dzmitry_lakisau.testapp.domain.repository.UserRepository
import javax.inject.Inject

class FetchDataUseCase @Inject constructor(
    private val postsRepository: PostRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): ResultOf<Unit> {
        val postsResponse = postsRepository.fetchPosts()
        val userResponse = userRepository.fetchUsers()

        return when {
            postsResponse is ResultOf.Success && userResponse is ResultOf.Success -> {
                ResultOf.Success(Unit)
            }
            postsResponse is ResultOf.Error -> postsResponse
            userResponse is ResultOf.Error -> userResponse
            else -> ResultOf.Error("FetchDataUseCase error")
        }
    }
}