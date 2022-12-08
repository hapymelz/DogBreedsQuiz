package com.katenumbers.dogbreedsquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.katenumbers.dogbreedsquiz.databinding.FragmentDataBinding
import com.katenumbers.dogbreedsquiz.models.Dog

class DataFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDataBinding.inflate(inflater, container, false)
        val viewModel : DogViewModel by activityViewModels()



        println("ORDERING DOGS...")
        for (dog in viewModel.dogsInOrder) {
            println(dog.name)
        }
        println(viewModel.dogsInOrder.size)
        fun onDogClick(dog: Dog) {
            findNavController().navigate(R.id.data_to_dog)
        }



        binding.recyclerView.adapter = DogAdapter(viewModel.dogsInOrder)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)


        return binding.root
    }

}