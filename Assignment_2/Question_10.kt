/* Employee Attendance Tracker
Assignment Batch 9
    - Create a class `Employee` with properties `name`, `employeeId`, and `attendance` (an array of
 booleans representing days of the month).
    - Implement methods `markAttendance(day: Int, present: Boolean)` and `calculateAttendance()`
 to update and calculate the attendance percentage.
    - Create an array of `Employee` objects and use a loop to print the attendance details of each
 employee.
    - **Bonus:** Add a method `displayAttendance()` to print the attendance details of the employee */

data class Attendance(val day:Int,val present:Boolean)
class Employee(val name:String,val employeeId:Int,var attendance:MutableList<Attendance>){
  fun markAttendance(day:Int,present:Boolean){
    attendance.add(Attendance(day, present))
  }
  fun calculateAttendance(attendance:Attendance){
        val totalDays = attendance.size
        val presentDays = attendance.count { day.present } 
        return (presentDays.toDouble() / totalDays) * 100.0 
  }
  fun displayAttendance() {
    println("Employee Name: $name")
    println("Employee ID: $employeeId")
    println("Attendance:")
    attendance.forEach { attendance ->
        println("Day ${attendance.day}: ${if (attendance.present) "Present" else "Absent"}")
    }
}
}
fun main() {
  val employees = arrayOf(
      Employee("A", 101, mutableListOf()),
      Employee("B", 102, mutableListOf())
  )
  employees[0].markAttendance(1, true)
  employees[0].markAttendance(2, false)
  employees[1].markAttendance(3, true)

  for (employee in employees) {
      employee.calculateAttendance()
      employee.displayAttendance()
      println("Attendance Percentage: ${employee.calculateAttendance()}%")
      println() 
  }
}