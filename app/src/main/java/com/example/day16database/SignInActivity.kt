package com.example.day16database

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {


    private lateinit var databaseReference: DatabaseReference

    companion object{
        const val KEY1 = "com.example.day16database.SignInActivity.mail"
        const val KEY2 = "com.example.day16database.SignInActivity.name"
        const val KEY3 = "com.example.day16database.SignInActivity.id"
        const val KEY4 = "com.example.day16database.SignInActivity.password"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<TextInputEditText>(R.id.etUserNameEditText)

        signInButton.setOnClickListener{

//            Take reference till node "Users"
            val uniqueId = userName.text.toString()
            if (uniqueId.isNotEmpty()){
                readData(uniqueId)
            }
            else{
                Toast.makeText(this, "Please Enter UserId", Toast.LENGTH_SHORT).show()
            }
        }

    } //onCreate Method over
     private fun readData(uniqueId:String) {
         databaseReference = FirebaseDatabase.getInstance().getReference("Users")
         databaseReference.child(uniqueId).get().addOnSuccessListener {

             //  if user exist or not
             if (it.exists()) {
                 // here we are getting the values
                 // welcome user in your app, with intent and also pass
                 val email = it.child("email").value
                 val name = it.child("name").value
                 val userId = it.child("uniqueId").value
                 val password = it.child("password").value


                 //here we passing the values
                 val intentWelcome = Intent(this, WelcomeActivity::class.java)
                 intentWelcome.putExtra(KEY1, email.toString())
                 intentWelcome.putExtra(KEY2, name.toString())
                 intentWelcome.putExtra(KEY3, userId.toString())
                 intentWelcome.putExtra(KEY4, password.toString())
                 startActivity(intentWelcome)

             } else {
                 Toast.makeText(this, "User does not exists", Toast.LENGTH_SHORT).show()
             }

         }.addOnFailureListener {
             Toast.makeText(this, "Failed , Error in Database", Toast.LENGTH_SHORT).show()
         }


    }

}