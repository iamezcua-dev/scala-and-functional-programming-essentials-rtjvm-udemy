package lectures.part3.functionalprogramming

object AnonymousFunctions extends App {
  // anonymous function (lambda)
  val doubler = (x: Int) => x * 2

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println( justDoSomething ) // function itself
  println( justDoSomething() ) // function call

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  /*
    Exercises
    1) MyList: Replace all FunctionX calls with lambdas
    2) Rewrite the "special" adder as an anonymous function
   */

  // 1) FunctionX calls replaced with lambdas in MyList.class
  // 2) Special adder rewritten in WhatsAFunction.class

}
