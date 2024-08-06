/* Course Enrollment System
   - Create a class `Course` with properties `courseName`, `courseCode`, and `enrolledStudents` (an
Assignment Batch 9
 array of `Student` objects).
   - Implement methods `enrollStudent(student: Student)` and `removeStudent(student: Student)` to
 add and remove students from the `enrolledStudents` array.
   - Create an array of `Course` objects and use a loop to print the course details and the names of
 all enrolled students.
   - **Bonus:** Add a method `displayCourseInfo()` to print the course's details.
    */

    data class Student(val student:String)
    class Course(val courseName:String,val courseCode:Int,var enrolledStudents:MutableList<Student>){
      fun enrollStudent(student:Student){
        enrolledStudents.add(student)
      }
      fun removeStudent( student:Student){
        enrolledStudents.remove(student)
      }
    }

    fun main(){
      var courseObject=arrayOf(
        Course("a",1,mutableListOf(Student("x"),Student("y"))),
        Course("b",2,mutableListOf(Student("e"),Student("f"))),
        Course("c",4,mutableListOf(Student("g"),Student("h"))),
      )
      val courseToRemoveStudentFrom = courseObject[1] // Access the second course
      val studentToRemove = Student("f")
      courseToRemoveStudentFrom.removeStudent(studentToRemove)

    }