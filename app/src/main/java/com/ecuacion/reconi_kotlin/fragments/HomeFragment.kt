package com.ecuacion.reconi_kotlin.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ecuacion.reconi_kotlin.CameraDisplay
import com.ecuacion.reconi_kotlin.GalleryDisplay
import com.ecuacion.reconi_kotlin.databinding.FragmentHomeBinding
import com.ecuacion.reconi_kotlin.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.galleryCardview.setOnClickListener {
            startActivityForResult(viewModel.galleryIntent(), HomeViewModel.GALLERY_CODE)
        }

        binding.cameraCardview.setOnClickListener {
            startActivityForResult(
                viewModel.cameraIntent(requireContext().contentResolver),
                HomeViewModel.CAMERA_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                HomeViewModel.CAMERA_CODE -> {
                    val imageURI = viewModel.imageURI
                    if (imageURI != null) {
                        val intent = Intent(requireActivity(), CameraDisplay::class.java)
                        intent.putExtra("imageURI", imageURI.toString())
                        startActivity(intent)
                    }
                }

                HomeViewModel.GALLERY_CODE -> {
                    val selectedImageUri = data?.data
                    if (selectedImageUri != null) {
                        val intent = Intent(requireActivity(), GalleryDisplay::class.java)
                        intent.putExtra("imageUri", selectedImageUri.toString())
                        startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
