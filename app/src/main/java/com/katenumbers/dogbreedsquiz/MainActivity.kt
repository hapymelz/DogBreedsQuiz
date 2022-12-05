package com.katenumbers.dogbreedsquiz

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private val viewModel = DogViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.createLoaded()

//        val loaded = viewModel.isLoaded(1) // Line 15
//        println("LOADED IS __${loaded}__") // Line 16

//        if (loaded == "false") { // Line 18
//        }
//        buildDatabase()
        val mInput = InputStreamReader(assets.open("dog-breeds-correct-edited.txt"))
        val reader = BufferedReader(mInput)
        var line : String

        while (reader.readLine().also { it.also { line = it } } != "END") {
            val row: List<String> = line.split(",").toList()
            var imageName = row[5].substring(row[5].indexOf("/") + 1, row[5].indexOf("."))
            if (imageName.startsWith("0")) {
                imageName = "_${imageName.substring(1)}"
            }
            val resourceID = getResourceID(imageName)
            println("${imageName}, $resourceID")

            viewModel.createDog(row, resourceID)
        }
//        viewModel.updateLoaded()
//
//        viewModel.deleteUnused(1)

        println("DONE!")

        setContentView(R.layout.activity_main)
        viewModel.deleteExtras()
    }

    private fun getResourceID(imageName: String): Int {
        val resources: Resources = resources
        return resources.getIdentifier(imageName, "drawable", this.packageName)

    }
}
