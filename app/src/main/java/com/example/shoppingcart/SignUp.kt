package com.example.shoppingcart

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUp : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var signup=findViewById<Button>(R.id.btn_signup)

        signup.setOnClickListener {
            var intent= Intent(this,BottomNavigationBar::class.java)
            startActivity(intent)
            finish()

        }
    }
}