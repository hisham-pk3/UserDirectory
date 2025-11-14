package com.example.userdirectory.data

import com.example.userdirectory.data.local.User
import com.example.userdirectory.data.local.UserDao
import com.example.userdirectory.data.remote.UserApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserRepository(
    private val apiService: UserApiService,
    private val userDao: UserDao
) {

    fun getUsersFromDb(): Flow<List<User>> = userDao.getAllUsers()

    fun searchUsers(query: String): Flow<List<User>> =
        if (query.isBlank()) userDao.getAllUsers()
        else userDao.searchUsers(query)

    suspend fun refreshUsers() = withContext(Dispatchers.IO) {
        val remoteUsers = apiService.getUsers()
        userDao.clearUsers()
        userDao.insertUsers(remoteUsers)
    }
}
