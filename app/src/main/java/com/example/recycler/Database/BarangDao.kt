package com.example.recycler.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recycler.Model.Barang

@Dao
interface BarangDao{
    @Query ("SELECT * FROM barang")
    fun loadBarang() : LiveData<List<Barang>>

    @Insert
    fun insertBarang(barang: Barang)

    @Update
    fun updateBarang(barang: Barang)

    @Delete
    fun deleteBarang(barang: Barang)
}
