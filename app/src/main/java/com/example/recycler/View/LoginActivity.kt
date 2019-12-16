package com.example.recycler.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.recycler.R
import com.example.recycler.Model.User
import com.example.tugasbesarkotlin2.Database.UserDao
import com.example.tugasbesarkotlin2.Database.UserDatabase
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.*

class LoginActivity : AppCompatActivity(), AnkoLogger {

    private var userDao: UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userDao = Room.databaseBuilder(this, UserDatabase::class.java, "tokobangunan.db")
            .allowMainThreadQueries()
            .build()
            .userDao
        val user =
            User("Super Admin", "admin", "admin")
        userDao!!.insert(user)

        btn_login.setOnClickListener {
            val username = et_user.text.toString().trim()
            val password = et_pass.text.toString().trim()

            handleOnLogin(username = username, password = password)
        }
    }

    private fun handleOnLogin(username: String, password: String) {
        debug { "username: $username and password: $password" }

        when {
            username.isEmpty() -> toast("Username is required.")
            password.isEmpty() -> toast("Password is required.")
            else ->

                if (userDao!!.getUser(username,password) != null) {
                    toast("Hi $username, You're logged in")
                    startActivity(intentFor<MainActivity>("username" to username).clearTask().clearTop())
                    finish()
                } else {
                    alert(
                        title = getString(R.string.title_login_failed),
                        message = getString(R.string.message_login_failed)
                    ) {
                        positiveButton(buttonText = "OK") {

                        }
                    }.show()
                }
        }
    }
}
