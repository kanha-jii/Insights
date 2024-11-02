package com.insights.fragments.interfacetype

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.insights.R
import com.insights.base.BaseFragment
import com.insights.databinding.FragmentDoctorBinding

class DoctorFragment : BaseFragment<FragmentDoctorBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDoctorBinding = FragmentDoctorBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSurvey.setOnClickListener {
            findNavController().navigate(R.id.action_doctorFragment_to_surveyFragment)
        }

        binding.tvLogout.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.action_doctorFragment_to_loginFragment)
        }

    }
}