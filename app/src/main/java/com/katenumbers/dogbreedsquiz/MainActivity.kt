package com.katenumbers.dogbreedsquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private val viewModel = DogViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.createLoaded()
        var loaded = viewModel.isLoaded(1)

        if (loaded == "false") {
            buildDatabase()
            viewModel.updateLoaded()
        }

        viewModel.deleteUnused(1)

        println("DONE!")

        setContentView(R.layout.activity_main)
    }

    private fun buildDatabase() {
        val mInput = InputStreamReader(assets.open("dog-breeds-correct-edited.txt"))
        val reader = BufferedReader(mInput)
        var line : String

        while (reader.readLine().also { it.also { line = it } } != "END") {
            val row: List<String> = line.split(",")
            viewModel.createDog(row)
        }
    }
}
