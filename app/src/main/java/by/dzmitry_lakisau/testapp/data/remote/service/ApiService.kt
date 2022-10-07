package by.dzmitry_lakisau.testapp.data.remote.service

import by.dzmitry_lakisau.testapp.data.remote.dto.PostDto
import by.dzmitry_lakisau.testapp.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostDto>>

    @GET("users")
    suspend fun getUsers(): Response<List<UserDto>>
}