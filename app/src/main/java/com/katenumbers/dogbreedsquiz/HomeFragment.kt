package com.katenumbers.dogbreedsquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.katenumbers.dogbreedsquiz.databinding.FragmentHomeBinding
import com.katenumbers.dogbreedsquiz.models.Dog

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val viewModel : DogViewModel by activityViewModels()
        val dogsInOrder = viewModel.orderByName()


        binding.dataButton.setOnClickListener {
            if (viewModel.dogsLoadedInOrder.value == true) {
                findNavController().navigate(R.id.home_to_data)
            }
        }

        binding.quizButton.setOnClickListener {
            if (viewModel.generateNewListOf5.value == false) {
                findNavController().navigate(R.id.home_to_quiz)
            }
        }


        return binding.root
    }

}