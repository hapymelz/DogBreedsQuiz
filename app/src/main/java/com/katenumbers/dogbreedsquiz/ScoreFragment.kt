package com.katenumbers.dogbreedsquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.katenumbers.dogbreedsquiz.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentScoreBinding.inflate(inflater, container, false)
        val viewModel : DogQuizViewModel by activityViewModels()

        binding.score.text = viewModel.score.toString()

        binding.toHome.setOnClickListener {
            findNavController().navigate(R.id.score_to_home)
        }

        return binding.root
    }

}