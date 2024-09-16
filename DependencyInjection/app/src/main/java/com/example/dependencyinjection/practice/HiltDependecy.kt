//package com.example.dependencyinjection.practice
//
//import javax.inject.Inject
//
//class Engine{
//    fun start(){
//        println("Engine started")
//    }
//}
//class Car{
//    @Inject lateinit var engine: Engine
//    fun start(){
//        engine.start()
//    }
//}
//
//fun main(){
//    val car=Car()
//
//}