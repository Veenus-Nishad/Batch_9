package com.example.dependencyinjection

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
/*
 Saves the code of object creation using @Inject
* */

//Step 1-> All apps that use Hilt must contain an Application/Base class that is annotated
// with @HiltAndroidApp.
//@HiltAndroidApp triggers Hilt's code generation, including a base class for your
// application that serves as the application-level dependency container.
// agar base class hain app mein toh sabse pehle vo run hogi than Main then rest

//@HiltAndroidApp
//class ExampleApplication : Application() {  }

//2 -> Once Hilt is set up in your Application class and an application-level component
// is available, Hilt can provide dependencies to other Android classes that have the
// @AndroidEntryPoint annotation:
// jo hum main activity ke upar lagaenge

//3-> Define Hilt bindings two ways
// 1.Use the @Inject annotation on the constructor of a class to tell Hilt how to
// provide instances of that class:
/*
class AnalyticsAdapter @Inject constructor(
  private val service: AnalyticsService
) { ... }
*/

// Step 2.Create HiltModule
/*
    @Module
    @InstallIn(SingletonComponent::class)
    object HiltModule{
    @Provides
    fun provideMyRepository(): MyRepository {
        return MyRepositoryImpl()
    }
}
*/

/*
 @InstallIn(SingletonComponent::class) ye tab use karte hai jab hum chahte hai ki jitni der meri app
 run karegi utni der meri hilt ka module exist karega
 Vaise hi ActivityComponent::class hota hai vo activity lifecycle ke according chalega
*/