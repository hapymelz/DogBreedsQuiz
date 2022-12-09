package com.katenumbers.dogbreedsquiz

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katenumbers.dogbreedsquiz.models.Dog
import kotlinx.coroutines.launch

class DogDataViewModel: ViewModel() {
    var dogsInOrder = ObservableArrayList<Dog>()
    var dogsLoadedInOrder = MutableLiveData(false)

    init {
        loadDogs()
    }

    private fun loadDogs() {
        viewModelScope.launch {
            val loadedDogsInOrder = DogRepository.orderByName()
            dogsInOrder.addAll(loadedDogsInOrder)
        }
    }

    fun orderByName() {
        viewModelScope.launch {
            val dogsList = DogRepository.orderByName()
            dogsInOrder.addAll(dogsList)
            dogsLoadedInOrder.value = true
        }
    }

}