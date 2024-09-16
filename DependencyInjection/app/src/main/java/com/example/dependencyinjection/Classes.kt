package com.example.dependencyinjection

import android.util.Log


class Car(val engine: Engine,val boobie: Boobs, val tyr:Tyre) {

    // working with interface
    interface Tyre{
        fun startTyre()
    }

    class tyreImpl:Tyre{
        override fun startTyre() {
            Log.d("TAG","Tyre started")
        }
    }
    fun StartCar(){
        tyr.startTyre()
        boobie.startBoobs()
        engine.startEngine()
        Log.d("TAG","Car started")
    }
}

class Engine() {
    fun startEngine(){
        Log.d("TAG","Engine started")
    }
}

class Boobs(){
    fun startBoobs(){
        Log.d("TAG","Boobs started")
    }
}


