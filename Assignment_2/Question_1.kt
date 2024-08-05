/*  Student Management System
   - Create a class `Student` with properties `name`, `rollNumber`, and `marks` (an array of integers).
   - Implement a method `calculateAverage()` in the `Student` class to calculate and return the
 average marks of the student.
   - Create an array of `Student` objects and use a loop to print the name, roll number, and average
 marks of each student.
   - **Bonus:** Add a method `displayDetails()` to print all details of the student. */

    fun main() {
      val students=arrayOf(
        Student("Poonam", 1, intArrayOf(95, 88, 72)),
        Student("Ashwini", 2, intArrayOf(80, 92, 75)),
        Student("Sanjana", 3, intArrayOf(78, 85, 90))
      )

      for (student in students){
        println("${student.name}: ${student.calculateAverage()}")
      }

      for (student in students){
        student.displayDetails()
      }
   }

   class Student(val name:String,val rollNumber:Int,var marks:IntArray){
    fun calculateAverage():Double{
      var average:Double=0.0
      for (i in marks){
        average+=i
      }
      return (average/marks.size)
    }
    fun displayDetails(){
      println("Name : ${name}")
      println("RollNo. : ${rollNumber}")
      println("marks :")
      for (mark in marks) {
        print("$mark ")
    }
    println()
    }
   }