package com.example.day16database

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val email = intent.getStringExtra(SignInActivity.KEY1)
        val name = intent.getStringExtra(SignInActivity.KEY2)
        val userId = intent.getStringExtra(SignInActivity.KEY3)
        val password = intent.getStringExtra(SignInActivity.KEY4)

        val welcomeText = findViewById<TextView>(R.id.tVWelcome)
        val mailText = findViewById<TextView>(R.id.tVMail)
        val idText = findViewById<TextView>(R.id.tVUnique)
        val idPassword = findViewById<TextView>(R.id.tVPassword)

        welcomeText.text = "Welcome $name"
        mailText.text = "Mail : $email"
        idText.text = "UserId :$userId"
        idPassword.text = "Password :$password"



    }
}