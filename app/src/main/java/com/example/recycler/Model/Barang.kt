package com.example.recycler.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Barang (
    @PrimaryKey(autoGenerate = true) var Id: Int,
    var nama: String,
    var merk: String,
    var stock: String,
    var price: String
)