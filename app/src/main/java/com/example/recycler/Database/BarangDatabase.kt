package com.example.recycler.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recycler.Model.Barang

@Database(version = 2, entities = [Barang::class])
abstract class BarangDatabase : RoomDatabase() {

    abstract fun barangDao() : BarangDao

    companion object{

        @Volatile
        private var INSTANCE: BarangDatabase? = null

        fun getInstance(context: Context) : BarangDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BarangDatabase::class.java,
                        "barang_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}