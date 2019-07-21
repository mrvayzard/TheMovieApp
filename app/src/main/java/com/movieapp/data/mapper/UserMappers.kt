package com.movieapp.data.mapper

import com.movieapp.data.local.database.entity.UserLocalEntity
import com.movieapp.data.remote.entity.UserResponseEntity

fun UserResponseEntity.toLocalEntity() = UserLocalEntity(
    gender = gender,
    name = name,
    region = region,
    surname = surname
)

fun UserLocalEntity.toRemoteEntity() = UserResponseEntity(
    gender,
    name,
    region,
    surname
)