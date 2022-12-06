package com.katenumbers.dogbreedsquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.katenumbers.dogbreedsquiz.databinding.FragmentHomeBinding
import com.katenumbers.dogbreedsquiz.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentQuizBinding.inflate(inflater, container, false)
        val viewModel : DogViewModel by activityViewModels()

        viewModel.isRandom.observe(viewLifecycleOwner) {
            if (it) {
                binding.answerButton1.text = viewModel.random3[0]
                binding.answerButton2.text = viewModel.random3[1]
                binding.answerButton3.text = viewModel.random3[2]
            }
        }

        viewModel.generateNewListOf5.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.getRandom()
                findNavController().navigateUp()
            }
        }

        viewModel.correctText.observe(viewLifecycleOwner) { correctText ->
            binding.correctText.text = correctText
        }

        binding.answerButton1.setOnClickListener {
            viewModel.checkAnswer(binding.answerButton1.text.toString())
        }
        binding.answerButton2.setOnClickListener {
            viewModel.checkAnswer(binding.answerButton2.text.toString())
        }
        binding.answerButton3.setOnClickListener {
            viewModel.checkAnswer(binding.answerButton3.text.toString())
        }

        viewModel.currentDog.observe(viewLifecycleOwner) {
            binding.imageView4.setImageResource(viewModel.currentDog.value!!.image)
            binding.sectionQuiz.text = viewModel.currentDog.value!!.section
            binding.groupQuiz.text = viewModel.currentDog.value!!.group
            binding.countryQuiz.text = viewModel.currentDog.value!!.country
        }

        return binding.root
    }

}