package com.example.day16database

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

// used for fire base
//    Initialize dataBase (variable)
    private lateinit var dataBase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signButton = findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etMail = findViewById<TextInputEditText>(R.id.etMail)
        val userId = findViewById<TextInputEditText>(R.id.etUserName)
        val userPassword = findViewById<TextInputEditText>(R.id.etPassword)

        signButton.setOnClickListener {
            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val uniqueId = userId.text.toString()
            val password = userPassword.text.toString()

//             Here we initialize the dataBase variable ( above we declared to initialize later)
//            here we locate the reference of firebase
//            then we have to make kotlin/class in java/com.example.day16database package select (data class on creating kotlin/class)
            val user = User(name, mail,password, uniqueId)

            dataBase = FirebaseDatabase.getInstance().getReference("Users")

            dataBase.child(uniqueId).setValue(user).addOnSuccessListener {
                etName.text?.clear()
                etMail.text?.clear()
                userId.text?.clear()
                userPassword.text?.clear()

                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
    }
            val signInText= findViewById<TextView>(R.id.tvSignIn)

            signInText.setOnClickListener{
                val openSignInActivity = Intent (this, SignInActivity::class.java)
                startActivity(openSignInActivity)
            }
        }
}