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

        val viewModel = DogViewModel()

        val lenData = viewModel.getLength()

        val mInput = InputStreamReader(assets.open("dog-breeds-correct-edited.txt"))
        val reader = BufferedReader(mInput)

        var line : String

        while (reader.readLine().also { line = it } != null) {
            val row: List<String> = line.split(",")
            viewModel.createDog(row)
        }

    }

}