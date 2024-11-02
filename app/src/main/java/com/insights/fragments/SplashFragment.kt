package com.insights.fragments

import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.insights.R
import com.insights.databinding.FragmentSplashBinding
import android.os.Handler
import androidx.navigation.fragment.findNavController
import com.insights.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding = FragmentSplashBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            if(auth.currentUser != null) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
            else {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }, 2000)

    }
}