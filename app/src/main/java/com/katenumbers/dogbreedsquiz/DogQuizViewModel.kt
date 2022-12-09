package com.katenumbers.dogbreedsquiz

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katenumbers.dogbreedsquiz.models.Dog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DogQuizViewModel: ViewModel() {
    var correctText = MutableLiveData("")
    var currentDog = MutableLiveData<Dog>()
    var currentIndex = 0
    var listOfN = ObservableArrayList<Dog>()
    var random3 = ObservableArrayList<String>()
    var isRandom = MutableLiveData(false)
    var generateNewListOf5 = MutableLiveData(true)
    var roundDone = MutableLiveData(false)
    var score = 0

    init {
        loadDogs()
    }
    private fun loadDogs() {
        viewModelScope.launch {
            getRandom()
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

    fun checkAnswer(dogName: String) {
        correctText.value = ""

        if (dogName != currentDog.value?.name) {
            correctText.value = "INCORRECT"
            score--
            viewModelScope.launch {
                delay(3000)
                correctText.value = ""
            }
            return
        }

        score++

        if (currentIndex == listOfN.size - 1) {
            listOfN.clear()
            generateNewListOf5.value = true
            currentIndex = 0
            roundDone.value = false
            return
        }

        currentIndex += 1
        currentDog.value = listOfN[currentIndex]
        isRandom.value = false
        getRandom3()
    }

    private fun getRandom3() {
        viewModelScope.launch {
            random3.clear()
            random3.addAll(DogRepository.getRandom3(currentDog.value!!.name))
            random3.add((0..2).shuffled().last(), currentDog.value!!.name)
            generateNewListOf5.value = false
            isRandom.value = true
        }
    }
}