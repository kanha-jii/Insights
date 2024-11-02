package com.insights.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.insights.R
import com.insights.base.BaseFragment
import com.insights.databinding.FragmentPassResetBinding

class PassResetFragment : BaseFragment<FragmentPassResetBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPassResetBinding = FragmentPassResetBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.tvSendLink.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if(email.isEmpty()) {
                showErrorSnackBar("Enter Email",false)
            }
            else {
                auth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showErrorSnackBar("Password Reset Link Sent", false)
                        findNavController().navigateUp()
                    } else {
                        showErrorSnackBar(it.exception?.message.toString(), false)
                    }
                }
            }
        }

    }

}