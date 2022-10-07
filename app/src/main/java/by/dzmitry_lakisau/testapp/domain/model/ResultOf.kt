package by.dzmitry_lakisau.testapp.domain.model

sealed class ResultOf<out R : Any> {
    data class Success<out R : Any>(val value: R) : ResultOf<R>()
    data class Error(val message: String) : ResultOf<Nothing>()
}
