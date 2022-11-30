package com.katenumbers.dogbreedsquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.katenumbers.dogbreedsquiz.models.Dog
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mInput = InputStreamReader(assets.open("dog-breeds-correct-edited.txt"))
        val reader = BufferedReader(mInput)

        var line : String

        while (reader.readLine().also { line = it } != null) {
            val row : List<String> = line.split(",")
            val dog = Dog(id = 0, name = row[1], section = row[2], group = row[3], country = row[4], image = row[5])
            DogRepository.createDog(dog)
        }


    }
}