package com.example.userdirectory.data.remote

import com.example.userdirectory.data.local.User
import retrofit2.http.GET

interface UserApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}
