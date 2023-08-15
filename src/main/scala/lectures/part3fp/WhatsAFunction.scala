package com.rockthejvm
package lectures.part3fp

object WhatsAFunction extends App {
  // Dream: use functions as first class elements
  // Problem: OOP
  
  trait Action[A, B] {
    def execute(element: A): B = ???
  }
  
  val doubler = new Function1[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  
  println(doubler(2))
  
  
  /*
    1.  A function which take 2 strings and concatenates them.
  */
  def concatenator(a: String, b: String): String = new Function1[String, String] {
    override def apply(v1: String): String = a + b
  }
  println(concatenator("Hello ", "Scala"))
  
  /*
    2.  Transform MyPredicate and MyTransformer into function types.
  */
  
  
  /*
    3.  Define a function which takes and int and returns another function which takes an int and returns and int
      - What's the type of this function
      - how to do it
   */
  
  
}
