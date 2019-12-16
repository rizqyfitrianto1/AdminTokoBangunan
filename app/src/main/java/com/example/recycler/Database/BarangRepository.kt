package com.example.recycler.Database

import com.example.recycler.Model.Barang

class BarangRepository(private val barangDao: BarangDao) {
    val allBarang = barangDao.loadBarang()

    suspend fun insert(barang: Barang){
        barangDao.insertBarang(barang)
    }

    suspend fun delete(barang: Barang){
        barangDao.deleteBarang(barang)
    }

    suspend fun update(barang: Barang){
        barangDao.updateBarang(barang)
    }

}