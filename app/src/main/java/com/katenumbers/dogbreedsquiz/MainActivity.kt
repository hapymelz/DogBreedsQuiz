package com.katenumbers.dogbreedsquiz

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: DogViewModel by viewModels()

        val mInput = InputStreamReader(assets.open("dog-breeds-correct-edited.txt"))
        val reader = BufferedReader(mInput)
        var line : String


        while (reader.readLine().also { line = it } != "END") {
            val row: List<String> = line.split(",").toList()
            var imageName = editImageName(row[5])
            val resourceID = getResourceID(imageName)
            println("${imageName}, $resourceID")

            viewModel.createDog(row, resourceID)
        }


        println("DONE!")

        viewModel.deleteExtras()
        setContentView(R.layout.activity_main)
    }

    private fun getResourceID(imageName: String): Int {
        val resources: Resources = resources
        return resources.getIdentifier(imageName, "drawable", this.packageName)
    }

    private fun editImageName(imageName: String): String {
        var edited = "_"
        edited += imageName.substring(imageName.indexOf("/") + 1, imageName.indexOf("."))
        return edited
    }
}
