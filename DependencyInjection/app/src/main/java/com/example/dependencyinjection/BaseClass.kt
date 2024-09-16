package com.example.dependencyinjection

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseClass :Application(){

}


/*
    Why the Android Manifest file Registration?

    Registering the base class in the Android Manifest file when using Hilt is crucial because it tells
    the Hilt compiler where to look for Hilt-annotated classes and components. This enables Hilt to correctly
    process and inject dependencies into your application.
    By registering this base class, you're telling Hilt where to start its dependency injection process.
*/