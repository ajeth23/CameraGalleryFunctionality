package com.ecuacion.reconi_kotlin

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ecuacion.reconi_kotlin.databinding.ActivityCameraDisplayBinding

class CameraDisplay : AppCompatActivity() {

    private lateinit var binding: ActivityCameraDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageURIString = intent.getStringExtra("imageURI")
        val imageURI = Uri.parse(imageURIString)
        val imageBitmap = getImageBitmapFromUri(imageURI)
        binding.cameraResult.setImageBitmap(imageBitmap)

    }

    private fun getImageBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            val resolver = contentResolver
            resolver.openInputStream(uri)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}