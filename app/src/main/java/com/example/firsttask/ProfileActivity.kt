package com.example.firsttask

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val name = intent.getStringExtra("EXTRA_NAME")
        val nim = intent.getStringExtra("EXTRA_NIM")
        val imageUri = intent.getStringExtra("EXTRA_IMAGE_URI")

        val profileName = findViewById<TextView>(R.id.textViewProfileName)
        val profileNIM = findViewById<TextView>(R.id.textViewProfileNIM)
        val profileImage = findViewById<ImageView>(R.id.imageViewProfile)

        profileName.text = "Nama: $name"
        profileNIM.text = "NIM: $nim"

        if (!imageUri.isNullOrEmpty()) {
            Glide.with(this)
                .load(Uri.parse(imageUri))
                .apply(RequestOptions.circleCropTransform())
                .into(profileImage)
        }
    }
}
