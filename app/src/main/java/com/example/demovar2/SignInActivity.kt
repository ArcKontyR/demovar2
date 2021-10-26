package com.example.demovar2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class SignInActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
    }

    fun signin(view: android.view.View) {
        if(email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()){
            val log = MyRetrofit().getRetrofit()
            val getApi = log.create(Interface::class.java)
            val hashMap: HashMap<String,String> = HashMap<String,String>()
            hashMap.put("email",email.text.toString())
            hashMap.put("password",password.text.toString())
            val log_call:Call<Void> = getApi.getAuth(hashMap)
            log_call.enqueue(object:retrofit2.Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful)
                    {
                        val intent = Intent(this@SignInActivity,MenuActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@SignInActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
            Intent(this, MenuActivity::class.java)
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
    fun signup(view: android.view.View) {
        val intent = Intent(this@SignInActivity,SignUpActivity::class.java)
        startActivity(intent)
        }
    }
}