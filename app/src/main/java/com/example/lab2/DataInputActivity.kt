package com.example.lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_data_input.*

class DataInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_input)

        goToUserPageButton.setOnClickListener {

            //get data from fields
            val username = userNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val birthday = birthdayEditText.text.toString()
            val phone = phoneEditText.text.toString()

            //if fields are empty shows a toast
            if (username.isEmpty() || email.isEmpty() || birthday.isEmpty() || phone.isEmpty())
                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show()
            else{
                //else go to user page and transfer a information about user
                val user = User(username, email, birthday, phone)
                val intent = Intent(this, UserPageActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }
        }

    }
}
