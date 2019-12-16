package com.example.recycler.Adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.recycler.Controller.ItemViewModel
import com.example.recycler.R
import com.example.recycler.Model.Barang
import com.example.recycler.databinding.ItemProductBinding

class ListItemAdapter(private val viewModel: ItemViewModel) : ListAdapter<Barang, ListItemAdapter.MyViewHolder>(
    ItemDiffCallback()
) {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nomor.text = (holder.adapterPosition+1).toString()
        holder.txtNama.text = getItem(holder.adapterPosition).nama
        holder.txtMerk.text = getItem(holder.adapterPosition).merk
        holder.txtStock.text = getItem(holder.adapterPosition).stock
        holder.txtPrice.text = getItem(holder.adapterPosition).price

            //delete
        holder.btnDelte.setOnClickListener {
            viewModel.removeItem(getItem(holder.adapterPosition))
        }

        //edit
        holder.btnEdit.setOnClickListener {
            val context = holder.itemView.context

            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.edit_item, null)

            //mengambil data sebelumnya
            val prevNama = getItem(holder.adapterPosition).nama
            val prevMerk = getItem(holder.adapterPosition).merk
            val prevStock = getItem(holder.adapterPosition).stock
            val prevPrice = getItem(holder.adapterPosition).price
            val editNama = view.findViewById<TextView>(R.id.edtNameChange)
            val editMerk = view.findViewById<TextView>(R.id.et_merkChange)
            val editStock = view.findViewById<TextView>(R.id.et_stokChange)
            val editPrice = view.findViewById<TextView>(R.id.et_priceChange)
            editNama.text = prevNama
            editMerk.text = prevMerk
            editStock.text = prevStock
            editPrice.text = prevPrice

            //dialog
            var alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Edit Item")
                .setView(view)
                .setPositiveButton("Update", DialogInterface.OnClickListener {
                    dialog, id ->

                    //edit
                    val editedNama = editNama.text.toString()
                    val editedMerk = editMerk.text.toString()
                    val editedStock = editStock.text.toString()
                    val editedPrice = editPrice.text.toString()

                    if (editedNama.isEmpty() || editedMerk.isEmpty() || editedPrice.isEmpty() || editedStock.isEmpty()){
                        Toast.makeText(context, "All field is required", Toast.LENGTH_SHORT).show()
                    }else{
                        getItem(holder.adapterPosition).nama = editedNama
                        getItem(holder.adapterPosition).merk = editedMerk
                        getItem(holder.adapterPosition).stock = editedStock
                        getItem(holder.adapterPosition).price = editedPrice
                        viewModel.updateItem(getItem(holder.adapterPosition))
                        holder.txtNama.text = editedNama
                        holder.txtMerk.text = editedMerk
                        holder.txtStock.text = editedStock
                        holder.txtPrice.text = editedPrice
                    }
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener{ dialog, id -> })

            //tampil dialog
            alertDialog.create().show()
        }
    }

    class MyViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val nomor = binding.tvNo
        val txtNama = binding.tvProductName
        val txtMerk = binding.tvMerk
        val txtStock = binding.tvStok
        val txtPrice = binding.tvPrice
        val btnDelte = binding.btnDelete
        val btnEdit = binding.btnEdit
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<Barang>(){
    override fun areItemsTheSame(p0: Barang, p1: Barang): Boolean {
        return p0.Id == p1.Id
    }

    override fun areContentsTheSame(p0: Barang, p1: Barang): Boolean {
        return p0.equals(p1)
    }

}