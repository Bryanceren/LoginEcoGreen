package com.example.kriz.login.Activities

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.kriz.login.Helpers.DatabaseHelper
import com.example.kriz.login.Helpers.InputValidation
import com.example.kriz.login.Models.User


class RegisterActivity : AppCompatActivity(),View.OnClickListener {
    private val activity=this@RegisterActivity
    private lateinit var username:EditText
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var confirmPass:EditText
    private lateinit var register:Button
    private lateinit var login:TextView
    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        initListeners()
        initObjects()
    }
    private fun initViews() {
        val videoview = findViewById<VideoView>(R.id.videoview)
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.test)
        videoview.setVideoURI(uri)
        videoview.start()
        videoview.setOnPreparedListener { mediaPlayer -> mediaPlayer.isLooping = true }
        username = findViewById<View>(R.id.username_activity_main) as EditText
        email = findViewById<View>(R.id.email_activity_main) as EditText
        password = findViewById<View>(R.id.password_activity_main) as EditText
        confirmPass = findViewById<View>(R.id.confirm_password_activity_main) as EditText

        register = findViewById<View>(R.id.register_activity_main) as Button
        login= findViewById<View>(R.id.login_activity_main) as TextView

    }

    private fun initListeners() {
        register.setOnClickListener(this)
        login.setOnClickListener(this)

    }

    private fun initObjects() {
        inputValidation = InputValidation(activity)
        databaseHelper = DatabaseHelper(activity)


    }


    override fun onClick(v: View) {
        when (v.id) {

            R.id.register_activity_main -> postDataToSQLite()

            R.id.login_activity_main -> {
                val intentRegister = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }

    private fun postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(username, getString(R.string.error_message_name))) {
            return
        }
        if (!inputValidation.isInputEditTextFilled(email, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation.isInputEditTextEmail(email, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation.isInputEditTextFilled(password, getString(R.string.error_message_password))) {
            return
        }
        if (!inputValidation.isInputEditTextMatches(password, confirmPass, getString(R.string.error_password_match))) {
            return
        }

        if (!databaseHelper.checkUser(email.text.toString().trim())) {

            var user = User(name = username.text.toString().trim(),
                email = email.text.toString().trim(),
                password = password.text.toString().trim())

            databaseHelper.addUser(user)
            emptyInputEditText()
            Toast.makeText(applicationContext,"Usuario registrado con exito", Toast.LENGTH_SHORT).show()


        } else {
            Toast.makeText(applicationContext,"Usuario ya existe", Toast.LENGTH_SHORT).show()
        }


    }

    private fun emptyInputEditText() {
        username.text = null
        email.text = null
        password.text = null
        confirmPass.text = null
    }
}