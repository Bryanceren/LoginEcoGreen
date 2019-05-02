package com.example.kriz.login.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView


class MenuActivity : AppCompatActivity() {
    private val activity=this@MenuActivity
    private lateinit var email:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        initViews()
    }
    private fun initViews() {
        email = findViewById<View>(R.id.email_activity_menu) as TextView
        val emailFromIntent = intent.getStringExtra("EMAIL")
        email.text = emailFromIntent.toString()
    }
}
