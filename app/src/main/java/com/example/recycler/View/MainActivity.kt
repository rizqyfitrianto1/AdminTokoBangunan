package com.example.recycler.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler.Controller.ItemViewModel
import com.example.recycler.Adapter.ListItemAdapter
import com.example.recycler.R
import com.example.recycler.databinding.ActivityMainBinding
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ListItemAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = intent.getStringExtra("username")
        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.app_name)
        actionBar?.subtitle = username
        actionBar?.elevation = 4.0F
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setLogo(R.mipmap.ic_launcher_round)
        actionBar?.setDisplayUseLogoEnabled(true)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ListItemAdapter(viewModel)

        recyclerView = binding.recyclerView

        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter

        }

        viewModel.items.observe(this, Observer {
            list -> viewAdapter.submitList(list.toMutableList())
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                startActivity(intentFor<LoginActivity>().clearTask().clearTop())
                finish()
                return true
            }
            R.id.action_addProduct -> {
                startActivity(intentFor<AddProductActivity>().clearTask().clearTop())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
