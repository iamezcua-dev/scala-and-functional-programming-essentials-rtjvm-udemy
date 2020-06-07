package lectures.part3.functionalprogramming

object WhatsAFunction extends App {
  /*
    1. Define a function which takes two Strings and concatenates them.
   */
  def concatenator = new ((String, String) => String) { // Using syntactic sugar of Function2[String, String, String]
    override def apply(a: String, b: String): String = a + b
  }

  assert(concatenator("Eddie", " Jobs") == "Eddie Jobs")
  println(concatenator("Eddie", " Jobs"))

  /* 2. Go to the MyList implementation and transform the MyPredicate
       and MyTransformer into function types.
   */


  /* 3. Define a function which takes an int and returns another function
       which takes an int and returns an int.
        - What's the type of this function?
        - How to do it?
   */
  // Type of the function: (Int => Int)
  def multiplier(i: Int): (Int => Int) = {
    (v1: Int) => v1 * i
  }

  println(multiplier(8)(3))

  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println( adder3(4) )
  println( superAdder(3)(4) ) // Curried function
}
