package com.katenumbers.dogbreedsquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katenumbers.dogbreedsquiz.models.Dog
import kotlinx.coroutines.launch

class DogViewModel: ViewModel() {
    private var dogs : List<Dog> = listOf()

    init {
        loadDogs()
    }
    private fun loadDogs() {
        viewModelScope.launch {
            val loadedDogs = DogRepository.getAll()
            dogs = loadedDogs
        }
    }


    fun createDog(row: List<String>) {
        viewModelScope.launch {
            val dog = Dog(id = 0, name = row[1], section = row[2], group = row[3], country = row[4], image = row[5])
            DogRepository.createDog(dog)
        }
    }

    fun getLength(): Int {
        return dogs.size
    }
}