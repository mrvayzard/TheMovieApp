package com.movieapp.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.movieapp.data.local.database.entity.UserLocalEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAll(): LiveData<List<UserLocalEntity>>

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getById(id: Long): UserLocalEntity?

    @Insert
    fun add(user: UserLocalEntity): Long

    @Update
    fun update(user: UserLocalEntity)

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Query("DELETE FROM user_table WHERE id = :id")
    fun delete(id: Long)
}
