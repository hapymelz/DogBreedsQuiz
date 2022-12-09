package com.katenumbers.dogbreedsquiz

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katenumbers.dogbreedsquiz.models.Dog
import com.katenumbers.dogbreedsquiz.models.TableMade
import kotlinx.coroutines.*

class DogViewModel: ViewModel() {
    var dogs = ObservableArrayList<Dog>()
    var isLoaded = MutableLiveData(false)
    var firstRow = ""


    init {
        loadDogs()
    }
    private fun loadDogs() {
        viewModelScope.launch {
            deleteExtras()
            firstRow()
            val loadedDogs = DogRepository.getAll()
            dogs.addAll(loadedDogs)
        }
    }

    fun createDog(row: List<String>, resourceID: Int) {
        viewModelScope.launch {
            println("__${resourceID}__") // This number, and dog.image below, are both the same
            val dog = Dog(id = 0, name = row[1], section = row[2], group = row[3], country = row[4], image = resourceID)
            println("__${dog.image}__")
            DogRepository.createDog(dog)
        }
    }

    fun getLength(): Int {
        return dogs.size
    }

    fun createLoaded() {
        viewModelScope.launch {
            val loadedTable = TableMade(id = 0, loaded = "false")
            DogRepository.createLoaded(loadedTable)
            isLoaded.value = true
        }
    }

    fun isLoaded(id: Int): String {
        var loaded = ""
        viewModelScope.launch {
            val table = DogRepository.isLoaded(id)
            loaded = table[0]
            println("LOADED FROM DVM: __${loaded}__")
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

    fun deleteExtras() {
        viewModelScope.launch {
            DogRepository.deleteExtras()
        }
    }

    fun firstRow() {
        viewModelScope.launch {
            val first = DogRepository.getFirstRow()
            if (first == null) {
                firstRow = ""
            }
            else {
                firstRow = first
            }
        }
    }
}