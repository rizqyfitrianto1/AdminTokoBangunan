package com.example.tugasbesarkotlin2.Database

import androidx.room.*
import com.example.recycler.Model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User where username= :username and password= :password")
    fun getUser(username: String, password: String): User

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}