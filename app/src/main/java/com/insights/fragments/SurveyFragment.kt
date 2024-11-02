package com.insights.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.insights.R
import com.insights.adapters.QuestionsAdapter
import com.insights.base.BaseFragment
import com.insights.databinding.FragmentSurveyBinding
import com.insights.models.Question

class SurveyFragment : BaseFragment<FragmentSurveyBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSurveyBinding = FragmentSurveyBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questions = mutableListOf<Question>()

        loader.show()

        var answerHashMap:HashMap<Int,Boolean> = HashMap()


        firestore.collection("questions").get().addOnSuccessListener { it ->

            loader.dismiss()
            it.documents.forEach {
                questions.add(it.toObject(Question::class.java)!!)
            }

            val adapter = QuestionsAdapter(onClickAnswer = {
                answerHashMap[it] = true
            })
            binding.rvQuestions.adapter = adapter
            binding.rvQuestions.layoutManager = LinearLayoutManager(requireContext())
            adapter.submitList(questions)

            for (i in 0..<questions.size){
                answerHashMap[i] = false
            }

        }



        binding.tvNext.setOnClickListener {
            answerHashMap.forEach {
                if (!it.value){
                   showErrorSnackBar("please select answer of ${it.key+1}",false)
                    return@setOnClickListener
                }
            }
            var type:Int = (1..3).random()


            if(type == 1) {
                findNavController().navigate(R.id.action_surveyFragment_to_highMaintenanceFragment)
            }
            else if(type == 2){
                findNavController().navigate(R.id.action_surveyFragment_to_shyFragment)
            }
            else{
                findNavController().navigate(R.id.action_surveyFragment_to_socialChildFragment)
            }



        }



    }
}