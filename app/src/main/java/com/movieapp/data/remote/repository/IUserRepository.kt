package com.movieapp.data.remote.repository

import com.movieapp.data.Result
import com.movieapp.data.local.database.entity.UserLocalEntity
import com.movieapp.data.remote.entity.UserResponseEntity

interface IUserRepository {
    suspend fun getRandomUserName(): Result<String>
    suspend fun addUserToDB(user: UserResponseEntity): Long
    suspend fun updateUserInDB(user: UserLocalEntity)
}