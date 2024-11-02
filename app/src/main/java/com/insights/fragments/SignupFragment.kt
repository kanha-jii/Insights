package com.insights.fragments

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.insights.R
import com.insights.base.BaseFragment
import com.insights.databinding.FragmentSignupBinding

class SignupFragment : BaseFragment<FragmentSignupBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignupBinding = FragmentSignupBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var showPassFlag = false;
        var showConfirmPassFlag = false;

        binding.tvVerify.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val pass = binding.etPass.text.toString()
            val confirmPass = binding.etConfirmPass.text.toString()

            if(email.isEmpty()) {
                showErrorSnackBar("Enter Email",false)
            }
            else if(pass.isEmpty()) {
                showErrorSnackBar("Enter Password", false)
            }
            else if(confirmPass.isEmpty()) {
                showErrorSnackBar("Enter Confirm Password", false)
            }
            else if(pass != confirmPass) {
                showErrorSnackBar("Password Mismatch", false)
            }
            else {
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showErrorSnackBar("Authentication Successful", false)
                        findNavController().navigate(R.id.action_signupFragment_to_selectTypeFragment)
                    } else {
                        showErrorSnackBar(it.exception?.message.toString(), false)
                    }
                }
            }


        }

        binding.ibShowPass.setOnClickListener {
            if(showPassFlag) {
                binding.etPass.transformationMethod = PasswordTransformationMethod.getInstance()
                showPassFlag = false
            }
            else {
                binding.etPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                showPassFlag = true
            }

        }

        binding.ibShowPassConfirm.setOnClickListener {
            if(showConfirmPassFlag) {
                binding.etConfirmPass.transformationMethod = PasswordTransformationMethod.getInstance()
                showConfirmPassFlag = false
            }
            else {
                binding.etConfirmPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                showConfirmPassFlag = true
            }

        }

    }
}