package com.katenumbers.dogbreedsquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katenumbers.dogbreedsquiz.models.Dog
import com.katenumbers.dogbreedsquiz.models.TableMade
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
        if (dogs == null) {
            return 0
        }
        return dogs.size
    }

    fun createLoaded() {
        viewModelScope.launch {
            val loadedTable = TableMade(id = 0, loaded = "false")
            DogRepository.createLoaded(loadedTable)
        }
    }

    fun isLoaded(id: Int): String {
        var loaded = ""
        viewModelScope.launch {
            loaded = DogRepository.isLoaded(id)
        }
        return loaded
    }

    fun updateLoaded() {
        viewModelScope.launch {
            var tableMade = TableMade(1, "true")
            DogRepository.updateLoaded(tableMade)
        }
    }

    fun deleteUnused(id: Int) {
        viewModelScope.launch {
            DogRepository.deleteUnused(id)
        }
    }

    fun getRandom(id: Int): List<Dog> {
        var randDogs : List<Dog> = listOf()
        viewModelScope.launch {
            randDogs = DogRepository.getRandom(id)
        }
        return randDogs
    }

    fun orderByName(): List<Dog> {
        var dogsInOrder : List<Dog> = listOf()
        viewModelScope.launch {
            dogsInOrder = DogRepository.orderByName()
        }
        return dogsInOrder
    }
}