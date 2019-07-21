package com.movieapp.data.remote.entity

data class UserResponseEntity(
    val gender: String,
    val name: String,
    val region: String,
    val surname: String
) {
    override fun toString(): String {
        return "$name $surname"
    }
}