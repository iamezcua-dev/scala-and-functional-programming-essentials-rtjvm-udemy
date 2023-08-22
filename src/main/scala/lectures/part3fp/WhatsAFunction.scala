package com.rockthejvm
package lectures.part3fp

object WhatsAFunction extends App {
  // Dream: use functions as first class elements
  // Problem: OOP

  val doubler = new Function1[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }
  println(stringToIntConverter("3") + 4)

  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A, B) => R
  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
    1.  A function which take 2 strings and concatenates them.
  */
  //  def concatenator(a: String, b: String): String = new Function2[String, String, String] {
  //    override def apply(v1: String, v2: String): String = v1 + v2
  //  }
  //  println(concatenator("Hello ", "Scala"))
  private def concatenator: Function2[String, String, String] = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(concatenator("Hello", " Scala!"))

  /*
    2.  Transform MyPredicate and MyTransformer into function types.
  */


  /*
    3.  Define a function which takes and int and returns another function which takes an int and returns and int
      - What's the type of this function
      - how to do it
   */
  private def myFunction: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(a: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(b: Int): Int = a * b
    }
  }

  println(myFunction(8)(7))
}

trait MyFunction[A, B] {
  def apply(element: A): B
}