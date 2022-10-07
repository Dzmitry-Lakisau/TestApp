package by.dzmitry_lakisau.testapp.domain.usecase

import by.dzmitry_lakisau.testapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsByUserUseCase @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke(userId: Long) = repository.getPostsByUser(userId)
}
