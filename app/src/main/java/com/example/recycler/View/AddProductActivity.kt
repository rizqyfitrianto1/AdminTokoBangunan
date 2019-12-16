package com.example.recycler.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.recycler.Controller.ItemViewModel
import com.example.recycler.R
import kotlinx.android.synthetic.main.activity_add_product.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class AddProductActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        btnSave.setOnClickListener {

            val editedNama = edtName.text.toString()
            val editedMerk = et_merk.text.toString()
            val editedStock = et_stok.text.toString()
            val editedPrice = et_price.text.toString()

            if (editedNama.isEmpty() || editedMerk.isEmpty() || editedPrice.isEmpty() || editedStock.isEmpty()){
                toast("All field is required")
            }else{
            viewModel.addItem(editedNama, editedMerk, editedStock, editedPrice)
            edtName.text = null
            et_merk.text = null
            et_stok.text = null
            et_price.text = null

            startActivity(intentFor<MainActivity>().clearTask().clearTop())
            finish()
            }

        }
    }
}
