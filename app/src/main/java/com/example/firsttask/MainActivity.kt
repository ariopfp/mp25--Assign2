package com.example.firsttask

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class LoginActivity : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.editTextName)
        val nimInput = findViewById<EditText>(R.id.editTextNIM)
        val saveButton = findViewById<Button>(R.id.buttonSave)
        profileImageView = findViewById(R.id.imageViewProfile)

        profileImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 100)
        }

        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            val nim = nimInput.text.toString()

            if (name.isNotEmpty() && nim.isNotEmpty()) {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("EXTRA_NAME", name)
                intent.putExtra("EXTRA_NIM", nim)
                intent.putExtra("EXTRA_IMAGE_URI", imageUri?.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data

            Glide.with(this)
                .load(imageUri)
                .apply(RequestOptions.circleCropTransform())
                .into(profileImageView)
        }
    }
}
