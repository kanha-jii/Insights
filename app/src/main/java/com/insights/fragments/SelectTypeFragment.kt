package com.insights.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.insights.R
import com.insights.base.BaseFragment
import com.insights.databinding.FragmentSelectTypeBinding
import com.insights.extensions.hide
import com.insights.extensions.show

class SelectTypeFragment : BaseFragment<FragmentSelectTypeBinding>() {
    var selectType = "";
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSelectTypeBinding = FragmentSelectTypeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDoctor.setOnClickListener {
            resetAll();
            binding.tvDoctor.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.tvDoctor.setBackgroundResource(R.drawable.tv_bg)
            binding.ivDoctor.show()
            selectType = "doctor"
        }

        binding.tvParent.setOnClickListener {

            resetAll();
            binding.tvParent.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.tvParent.setBackgroundResource(R.drawable.tv_bg)
            binding.ivParent.show()
            selectType = "parent"

        }

        binding.tvTeacher.setOnClickListener {

            resetAll();
            binding.tvTeacher.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.tvTeacher.setBackgroundResource(R.drawable.tv_bg)
            binding.ivTeacher.show()
            selectType = "teacher"

        }


        binding.tvNext.setOnClickListener {
            if(selectType == "") {
                showErrorSnackBar("Please select your path",false)
                return@setOnClickListener
            }
            else{
                loader.show()
                val user = hashMapOf(
                    "type" to selectType
                )
                firestore.collection("users").document(auth.currentUser!!.uid).set(user).addOnCompleteListener {
                    loader.dismiss()
                    findNavController().navigate(R.id.action_selectTypeFragment_to_surveyFragment)
                }
            }
        }
    }

    private fun resetAll() {
        binding.apply {
            tvDoctor.setTextColor(ContextCompat.getColor(requireContext(), R.color.theme_dark))
            tvDoctor.setBackgroundResource(R.drawable.light_tv_bg)
            ivDoctor.hide()

            tvParent.setTextColor(ContextCompat.getColor(requireContext(), R.color.theme_dark))
            tvParent.setBackgroundResource(R.drawable.light_tv_bg)
            ivParent.hide()

            tvTeacher.setTextColor(ContextCompat.getColor(requireContext(), R.color.theme_dark))
            tvTeacher.setBackgroundResource(R.drawable.light_tv_bg)
            ivTeacher.hide()
        }
    }
}