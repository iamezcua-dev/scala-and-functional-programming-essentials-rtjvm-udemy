package lectures.part1.basics

object ValuesVariablesTypes extends App {
  val x = 42
  println( x )
  
  // val objects are immutable.
  
  // Compiler can infer types
  val aString: String = "hello"
  val anotherString = "goodbye"
  
  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4316
  val aLong: Long = 345678909876543L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.1415926535d
  
  // variables
  var aVariable: Int = 4
  aVariable = 5 // Side effects
}
