package com.insights.fragments.childtype

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.insights.R
import com.insights.base.BaseFragment
import com.insights.databinding.FragmentSocialChildBinding

class SocialChildFragment : BaseFragment<FragmentSocialChildBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSocialChildBinding = FragmentSocialChildBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}