package com.example.shoppingcart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        var signup=findViewById<Button>(R.id.signup)

        signup.setOnClickListener {
            var intent= Intent(this,SignUp::class.java)
            startActivity(intent)
        }
    }
}