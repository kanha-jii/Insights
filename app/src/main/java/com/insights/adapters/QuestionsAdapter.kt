package com.insights.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.insights.R
import com.insights.databinding.ItemQuestionsBinding
import com.insights.models.Question

class QuestionsAdapter(val onClickAnswer: (Int) -> Unit) : ListAdapter<Question, QuestionsAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemQuestionsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question,position: Int) {

            binding.tvQuestion.setText("${position+1}" + ". " + question.question)
            binding.tvAnswer1.text = question.answers[0]
            binding.tvAnswer2.setText(question.answers[1])
            binding.tvAnswer3.setText(question.answers[2])
            binding.tvAnswer4.setText(question.answers[3])
            binding.tvAnswer5.setText(question.answers[4])

            binding.cv1.setOnClickListener {
                resetAll()
                binding.cv1.setCardBackgroundColor(binding.root.context.getColor(R.color.theme_dark))
                onClickAnswer(position)
            }

            binding.cv2.setOnClickListener {
                resetAll()
                binding.cv2.setCardBackgroundColor(binding.root.context.getColor(R.color.theme_dark))
                onClickAnswer(position)
            }

            binding.cv3.setOnClickListener {
                resetAll()
                binding.cv3.setCardBackgroundColor(binding.root.context.getColor(R.color.theme_dark))
                onClickAnswer(position)
            }

            binding.cv4.setOnClickListener {
                resetAll()
                binding.cv4.setCardBackgroundColor(binding.root.context.getColor(R.color.theme_dark))
                onClickAnswer(position)
            }

            binding.cv5.setOnClickListener {
                resetAll()
                binding.cv5.setCardBackgroundColor(binding.root.context.getColor(R.color.theme_dark))
                onClickAnswer(position)
            }
        }

        private fun resetAll() {
            binding.cv1.setCardBackgroundColor(binding.root.context.getColor(R.color.light_theme_dark))
            binding.cv2.setCardBackgroundColor(binding.root.context.getColor(R.color.light_theme_dark))
            binding.cv3.setCardBackgroundColor(binding.root.context.getColor(R.color.light_theme_dark))
            binding.cv4.setCardBackgroundColor(binding.root.context.getColor(R.color.light_theme_dark))
            binding.cv5.setCardBackgroundColor(binding.root.context.getColor(R.color.light_theme_dark))
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemQuestionsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),position)
    }

}