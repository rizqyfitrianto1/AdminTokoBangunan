package com.example.tugasbesarkotlin2.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recycler.Model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract val userDao: UserDao

}
