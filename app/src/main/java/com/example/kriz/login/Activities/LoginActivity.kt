package com.example.kriz.login.Activities

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.kriz.login.Helpers.DatabaseHelper
import com.example.kriz.login.Helpers.InputValidation
import com.example.kriz.login.Activities.MenuActivity as MenuActivity

class LoginActivity : AppCompatActivity(),View.OnClickListener {
    private val activity=this@LoginActivity
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var login:Button
    private lateinit var register:TextView
    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
        email = findViewById<View>(R.id.email_activity_login) as EditText
        password = findViewById<View>(R.id.password_activity_login) as EditText

        register = findViewById<View>(R.id.register_activity_login) as TextView
        login= findViewById<View>(R.id.login_activity_login) as Button

    }

    private fun initListeners() {
        register.setOnClickListener(this)
        login.setOnClickListener(this)

    }

    fun initObjects() {
        inputValidation = InputValidation(activity)
        databaseHelper = DatabaseHelper(activity)


    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.login_activity_login -> verifyFromSQLite()
            R.id.register_activity_login -> {
                val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }

    private fun verifyFromSQLite() {

        if (!inputValidation.isInputEditTextFilled(email,  getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation.isInputEditTextEmail(email, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation.isInputEditTextFilled(password, getString(R.string.error_message_email))) {
            return
        }

        if (databaseHelper.checkUser(email.text.toString().trim { it <= ' ' }, password.text.toString().trim { it <= ' ' })) {


            /*val accountsIntent = Intent(activity, UsersListActivity::class.java)
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.text.toString().trim { it <= ' ' })
            */
            val MenuActivity = Intent(activity, MenuActivity::class.java)
            MenuActivity.putExtra("EMAIL", email.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(MenuActivity)

        } else {
            Toast.makeText(applicationContext,"Usuario no existe", Toast.LENGTH_SHORT).show()

        }
    }

    private fun emptyInputEditText() {
        email.text = null
        password.text = null
    }
}