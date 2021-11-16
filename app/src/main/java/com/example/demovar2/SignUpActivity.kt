package com.example.demovar2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.content.Intent
import android.content.IntentFilter
import android.os.CountDownTimer
import android.widget.EditText
import java.util.regex.Pattern.compile
import kotlin.concurrent.timer

class SignUpActivity : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var repeatpassword: EditText
    lateinit var name: EditText
    lateinit var surname: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        repeatpassword = findViewById(R.id.repeatpassword)
        name = findViewById(R.id.name)
        surname =findViewById(R.id.surname)
    }
    val paternt =("[a-z0-9]{1,256}" +
            "\\@" +
            "[a-z]{1,10}" +
            "\\." +
            "[a-z]{1,3}")
    fun EmailValid (email:String):Boolean{
        return compile (paternt).matcher(email).matches()
    }
    fun suasignup(view: android.view.View){
        if (email.text.toString().isNotEmpty()
            && password.text.toString().isNotEmpty()
            && repeatpassword.text.toString().isNotEmpty()
            && password.text.toString() == repeatpassword.text.toString()
            && name.text.toString().isNotEmpty()
            && surname.text.toString().isNotEmpty()){
            if (EmailValid(email.text.toString())) {
                val alert = AlertDialog.Builder(this)
                    .setTitle("Уведомление")
                    .setMessage("Вы зарегистрировались")
                    .setPositiveButton("OK", null)
                    .create()
                    .show()
                val timer = object : CountDownTimer(1500, 250) {
                    override fun onTick(p0: Long) {

                    }

                    override fun onFinish() {
                        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                timer.start()
            } else {
                val alert = AlertDialog.Builder(this)
                    .setTitle("Уведомление")
                    .setMessage("Email введен неверно")
                    .setPositiveButton("OK", null)
                    .create()
                    .show()
            }
        } else
        {
            if(password.text.toString() != repeatpassword.text.toString()){
            val alert = AlertDialog.Builder(this)
            .setTitle("Ошибка")
            .setMessage("Пароли не совпадают")
            .setPositiveButton("OK", null)
            .create()
            .show()
            } else
            {
                val alert = AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("Заполните все поля")
                    .setPositiveButton("OK", null)
                    .create()
                    .show()
            }
        }
    }
    fun suasignin(view: android.view.View){
            val intent = Intent(this@SignUpActivity,SignInActivity::class.java)
            startActivity(intent)
        }
}