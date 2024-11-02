package com.insights.fragments

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.insights.R
import com.insights.base.BaseFragment
import com.insights.databinding.FragmentLoginBinding





class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        var showPassFlag = false
        binding.tvLogIn.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val pass = binding.etLoginPass.text.toString()
            if(email.isEmpty()) {
                showErrorSnackBar("Enter Email",false)
            }
            if(pass.isEmpty()) {
                showErrorSnackBar("Enter Password",false)
            }
            else {
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showErrorSnackBar("Authentication Successful", false)
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        showErrorSnackBar(it.exception?.message.toString(), false)
                    }
                }
            }
        }



        binding.tvCreateAc.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }


        binding.tvContinueWithFbBt.setOnClickListener {
            showErrorSnackBar("Coming Soon",true)
        }

        binding.tvContinueWithGoogleBt.setOnClickListener {
            showErrorSnackBar("Coming Soon",true)
        }

        binding.ibShowPass.setOnClickListener {
            if(showPassFlag) {
                binding.etLoginPass.transformationMethod = PasswordTransformationMethod.getInstance()
                showPassFlag = false
            }
            else {
                binding.etLoginPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                showPassFlag = true
            }

        }

        binding.tvForgetPass.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_passResetFragment)
        }



    }

}