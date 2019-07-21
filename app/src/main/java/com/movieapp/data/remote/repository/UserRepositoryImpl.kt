package com.movieapp.data.remote.repository

import com.movieapp.data.Result
import com.movieapp.data.local.database.dao.UserDao
import com.movieapp.data.local.database.entity.UserLocalEntity
import com.movieapp.data.mapTo
import com.movieapp.data.mapper.toLocalEntity
import com.movieapp.data.remote.ApiService
import com.movieapp.data.remote.entity.UserResponseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val api: ApiService,
    private val userDao: UserDao
) : IUserRepository, BaseRepository() {
    override suspend fun getRandomUserName(): Result<String> = withContext(Dispatchers.IO) {
        processRequest {
            api.getRandomUser()
        }.mapTo { it.name }
    }

    override suspend fun addUserToDB(user: UserResponseEntity) = withContext(Dispatchers.IO) {
        userDao.add(user.toLocalEntity())
    }

    override suspend fun updateUserInDB(user: UserLocalEntity) = withContext(Dispatchers.IO) {
        userDao.update(user)
    }
}