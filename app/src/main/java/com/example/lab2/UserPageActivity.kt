package com.example.lab2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user_page.*
import java.io.FileNotFoundException


class UserPageActivity : AppCompatActivity() {

    val RESULT_LOAD_IMG = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page)

        //display an arrow in toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get a user data received from dataInputActivity
        val bundle: Bundle? = intent.extras
        val user = bundle?.getSerializable("user") as User

        //using Picasso library to set image
        pictureImageView.setImageResource(R.drawable.user_profile)

        //display data
        nameTextView.text = user.userName
        emailTextView.text = user.email
        birthdayTextView.text = user.birthday
        phoneTextView.text = user.phone


        //load image from gallery
        pictureImageView.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"

            // Получаем Package Manager
            val manager = this.packageManager
            // Получаем список обработчиков намерения
            val list = manager.queryIntentActivities(photoPickerIntent, 0)

            if (list.size > 0) {
                //starting activity for result
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG)
            } else {
                Toast.makeText(this, "Невозможно обработать намерение!", Toast.LENGTH_LONG).show()
            }

        }
    }

    //loading selected image to page
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            try {
                // if result is successful display loaded image
                val imageUri: Uri? = data?.data
                pictureImageView.setImageURI(imageUri)

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show()
        }
    }
}
