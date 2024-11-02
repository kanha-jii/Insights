package com.insights.base

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

import com.insights.R


abstract class BaseFragment<B : ViewBinding> : Fragment() {

    lateinit var binding: B
    lateinit var loader: Dialog
    public lateinit var auth:FirebaseAuth
//    lateinit var storage: FirebaseStorage
    lateinit var firestore: FirebaseFirestore
//    lateinit var database: FirebaseDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        dialogShow()

        // Initialize Firebase Auth
        auth = Firebase.auth
//        storage = Firebase.storage
        firestore = Firebase.firestore
//        database = Firebase.database
        return binding.root
    }

    fun dialogShow() {
        loader = Dialog(requireContext())
        loader.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loader.setContentView(R.layout.layout_loader)
        loader.setCancelable(true)
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val phonePattern = Patterns.PHONE // Android's built-in phone number pattern
        return phonePattern.matcher(phoneNumber).matches()
    }

//    fun dialogShow() {
//        loader = Dialog(requireContext())
//        loader.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        loader.setContentView(R.layout.layout_loader)
//        loader.setCancelable(true)
//    }

    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make((requireActivity().findViewById(android.R.id.content)), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorSnackBarError))
        }else{
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorSnackBarSuccess
                )
            )
        }
        snackBar.show()
    }

    fun getCapturedImage(selectedPhotoUri: Uri): Bitmap {
        val bitmap = when {
            Build.VERSION.SDK_INT < 28 -> MediaStore.Images.Media.getBitmap(
                requireActivity().contentResolver,
                selectedPhotoUri
            )

            else -> {
                val source =
                    ImageDecoder.createSource(requireActivity().contentResolver, selectedPhotoUri)
                ImageDecoder.decodeBitmap(source)
            }
        }
        return bitmap
    }

}