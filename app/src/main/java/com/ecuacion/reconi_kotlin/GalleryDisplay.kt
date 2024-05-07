package com.ecuacion.reconi_kotlin


import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ecuacion.reconi_kotlin.databinding.ActivityGalleryDisplayBinding


class GalleryDisplay : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUriString = intent.getStringExtra("imageUri")
        val imageUri = Uri.parse(imageUriString)
        binding.galleryResult.setImageURI(imageUri)
    }

}