package com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet

import android.app.Application
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.data.AppContainer
import com.github.dudkomatt.androidcourse.dudkomatt_lecture4viewmodelinternet.data.DefaultAppContainer

class PostsApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
