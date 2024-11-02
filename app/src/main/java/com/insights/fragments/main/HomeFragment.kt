package com.insights.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.insights.R
import com.insights.base.BaseFragment
import com.insights.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loader.show()

        firestore.collection("users").document(auth.currentUser!!.uid).get().addOnSuccessListener {
            if (it != null) {
                loader.dismiss()

                val userRole = it.getString("type")
                if (userRole == "doctor") {
                    findNavController().navigate(R.id.action_homeFragment_to_doctorFragment)
                }
                else if (userRole == "parent") {
                    findNavController().navigate(R.id.action_homeFragment_to_parentFragment)
                }
                else if (userRole == "teacher") {
                    findNavController().navigate(R.id.action_homeFragment_to_teacherFragment)
                }
            }
        }


    }
}