package com.katenumbers.dogbreedsquiz

import android.app.Application

class DogApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance : Application? = null

        fun getInstance() : Application {
            return instance!!
        }
    }
}
