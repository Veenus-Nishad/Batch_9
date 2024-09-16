package com.example.dependencyinjection

import com.example.dependencyinjection.Car.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIModule {

    @Provides // jispe provide laga hai usi ko di bahar bhejega
    @Singleton
    fun provideCar(@Named("Engine") engine: Engine, @Boobie1 boobie: Boobs, tyre:Tyre): Car {
        return Car(engine,boobie,tyre)

    }

    @Provides
    @Singleton
    fun provideTyre(): Tyre { // working with interface
        return tyreImpl()
    }

    @Provides
    @Singleton
    @Named("Engine")
    fun provideEngine(): Engine {
        return Engine()
    }

    @Provides
    @Singleton
    @Named("Engine1") // Method 1
    fun provideEngine1(): Engine {
        return Engine()
    }

    @Provides
    @Singleton
    @Boobie1 // Method 2
    fun provideBoobs(): Boobs {
        return Boobs()
    }

    @Qualifier
    @Singleton
    @Retention(AnnotationRetention.BINARY)
    annotation class Boobie1

}

/*
    Hilt ke Module ke andar hi agar meko dendecy bhej ni hai toh sidha bas name:Type likh denge
*/

/*
    Hilt Module return type match karta hai sirf is file ke andar, so what if functions share same return type
    app will Crash
    So to tackle this we have two methods
        1.Named Parameters => @Named("Engine1")
        2.Qualifier => @Qualifier
                        @Retention(AnnotationRetention.BINARY)
                        annotation class Boobie1
                        then @Boobie1 se function ko annotate kar denge
*/