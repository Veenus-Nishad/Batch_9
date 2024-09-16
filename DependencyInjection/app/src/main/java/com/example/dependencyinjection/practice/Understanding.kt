//package com.example.dependencyinjection.practice
//
////class Engine{
////    fun start(){
////        println("Engine started")
////    }
////}
//
////Without dependency injection
//class Car_wdi {
//
//    private val engine = Engine()
//
//    fun start() {
//        engine.start()
//    }
//}
//
//// Manual dependency injection
//// Engine ka object parameter mehi bana diya
//// Constructor Injection
//class Car_mdi(private val engine: Engine) {
//    fun start() {
//        engine.start()
//    }
//}
//
//
//class Car_mdi_2 {
//    //Field Injection (or Setter Injection). Certain Android framework classes such as
//    // activities and fragments are instantiated by the system, so constructor injection
//    // is not possible. With field injection, dependencies are instantiated after the
//    // class is created. The code would look like this:
//    lateinit var engine: Engine // field ( nothing but a variable )
//
//    fun start() {
//        engine.start()
//    }
//}
//
//fun main() {
//
//    val car_1 = Car_wdi()
//    car_1.start()
//
//    val engine = Engine()
//    val car_2 = Car_mdi(engine)
//    car_2.start()
//
//    val car_3 = Car_mdi_2()
//    car_3.engine = Engine()
//    car_3.start()
//}