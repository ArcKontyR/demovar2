package com.example.demovar2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.content.Intent
import android.content.IntentFilter
import android.widget.EditText
import java.util.regex.Pattern.compile

class SignInActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
    }
    val pattern =("[a-z0-9]{1,256}" +
            "\\@" +
            "[a-z]{1,10}" +
            "\\." +
            "[a-z]{1,3}")
    fun EmailValid (email:String):Boolean{
        return compile (pattern).matcher(email).matches()}

    fun siasignin(view: android.view.View) {
        if(email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()){
            if (EmailValid(email.text.toString())) {
                val intent = Intent(this@SignInActivity, MainActivity::class.java)
                startActivity(intent)
            } else{
                val alert = AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("Email введен неверно")
                .setPositiveButton("OK", null)
                .create()
                .show()
            }
        }
        else{
            val alert = AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("Заполните все поля")
                .setPositiveButton("OK", null)
                .create()
                .show()
        }
    }
    fun siasignup(view: android.view.View) {
        val intent = Intent(this@SignInActivity,SignUpActivity::class.java)
        startActivity(intent)
        }
    }