package com.katenumbers.dogbreedsquiz

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katenumbers.dogbreedsquiz.models.Dog
import com.katenumbers.dogbreedsquiz.models.TableMade
import kotlinx.coroutines.*

class DogViewModel: ViewModel() {
    var dogsInOrder = ObservableArrayList<Dog>()
    var dogsLoadedInOrder = MutableLiveData(false)
    var dogs = ObservableArrayList<Dog>()
    var correctText = MutableLiveData("")
    var isCorrect = MutableLiveData(false)
    var currentDog = MutableLiveData<Dog>()
    var currentIndex = 0
    var listOfN = ObservableArrayList<Dog>()
    var random3 = ObservableArrayList<String>()
    var isRandom = MutableLiveData(false)
    var generateNewListOf5 = MutableLiveData(true)
    var isLoaded = MutableLiveData(false)
    var firstRow = ""


    init {
        loadDogs()
    }
    private fun loadDogs() {
        viewModelScope.launch {
            deleteExtras()
            getFirstRow()
            val loadedDogs = DogRepository.getAll()
            dogs.addAll(loadedDogs)
            val loadedDogsInOrder = DogRepository.orderByName()
            dogsInOrder.addAll(loadedDogsInOrder)
            getRandom()
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

    fun getRandom() {
        viewModelScope.launch {
            val newList = DogRepository.getRandom(5)
            listOfN.addAll(newList)
            generateNewListOf5.value = false
            currentDog.value = listOfN[currentIndex]
            getRandom3()
            isRandom.value = true
        }
    }

    fun orderByName() {
        viewModelScope.launch {
            val dogsList = DogRepository.orderByName()
            dogsInOrder.addAll(dogsList)
            dogsLoadedInOrder.value = true
        }
    }

    fun deleteExtras() {
        viewModelScope.launch {
            DogRepository.deleteExtras()
        }
    }

    fun checkAnswer(dogName: String) {
        correctText.value = ""

        if (dogName != currentDog.value?.name) {
            correctText.value = "INCORRECT"
            viewModelScope.launch {
                delay(3000)
                correctText.value = ""
            }
            return
        }

        if (currentIndex == listOfN.size - 1) {
            listOfN.clear()
            generateNewListOf5.value = true
            currentIndex = 0
            return
        }

        currentIndex += 1
        currentDog.value = listOfN[currentIndex]
        isRandom.value = false
        getRandom3()
    }
    fun getRandom3() {
        viewModelScope.launch {
            random3.clear()
            random3.addAll(DogRepository.getRandom3(currentDog.value!!.name))
            random3.add((0..2).shuffled().last(), currentDog.value!!.name)
            generateNewListOf5.value = false
            isRandom.value = true
        }
    }

    fun getFirstRow() {
        viewModelScope.launch {
            firstRow = DogRepository.getFirstRow()
        }
    }
}