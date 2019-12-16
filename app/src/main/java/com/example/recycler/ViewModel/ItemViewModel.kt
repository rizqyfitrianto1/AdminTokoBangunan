package com.example.recycler.Controller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.recycler.Model.Barang
import com.example.recycler.Database.BarangDao
import com.example.recycler.Database.BarangDatabase
import com.example.recycler.Database.BarangRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : BarangRepository
    private val barangDao : BarangDao

    private val _items : LiveData<List<Barang>>

    val items : LiveData<List<Barang>>
    get() = _items

    private var vmJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + vmJob)

    init {
        barangDao = BarangDatabase.getInstance(application).barangDao()
        repository = BarangRepository(barangDao)
        _items = repository.allBarang
    }

    fun addItem(nama: String, merk: String, stock: String, price: String ) {
        uiScope.launch {
            repository.insert(
                Barang(
                    0,
                    nama,
                    merk,
                    stock,
                    price
                )
            )
        }
    }

    fun removeItem(barang: Barang){
        uiScope.launch {
            repository.delete(barang)
        }
    }

    fun updateItem(barang: Barang){
        uiScope.launch {
            repository.update(barang)
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }

}