package com.ecuacion.reconi_kotlin.viewModel

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.lifecycle.ViewModel

class HomeViewModel(): ViewModel() {

    var imageURI: Uri? = null

    companion object {
        const val CAMERA_CODE = 1001
        const val GALLERY_CODE = 1002
    }

    fun galleryIntent(): Intent {
        val pickImage = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImage.type = "image/*"
        return pickImage
    }

    fun cameraIntent(contentResolver: ContentResolver): Intent {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        imageURI = createImage(contentResolver)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageURI)
        return cameraIntent
    }

    private fun createImage(resolver: ContentResolver): Uri {
        val uri: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
        val imgName = "${System.currentTimeMillis()}.jpeg"
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, imgName)
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/ReconiKotlin/")
        }
        val finalUri: Uri = resolver.insert(uri, contentValues)
            ?: throw IllegalStateException("Failed to create image URI")
        imageURI = finalUri
        return finalUri
    }
}