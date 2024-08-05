/* Animal Sound Simulator
   - Create a base class `Animal` with an abstract method `makeSound()`.
   - Create subclasses `Dog`, `Cat`, and `Cow` that inherit from `Animal` and implement the
 `makeSound()` method.
   - Create an array of `Animal` objects and use a loop to call the `makeSound()` method for each
 animal, printing the respective sounds.
   - **Bonus:** Add a method `displayAnimalInfo()` to print the animal's details. 
*/

fun main(){
  var animals=arrayOf(
    Dog(),
    Cat(),
    Cow()
  )

  for (animal in animals){
    animal.makeSound()
  }
}

abstract class Animal(){
  abstract fun makeSound()
}

class Dog():Animal(){
  override fun makeSound(){
    println("Bark")
  }
}
class Cat():Animal(){
  override fun makeSound(){
    println("Meow")
  }
}
class Cow():Animal(){
  override fun makeSound(){
    println("Moo")
  }
}
