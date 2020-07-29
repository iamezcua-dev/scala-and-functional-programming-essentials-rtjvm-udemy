package lectures.part3.functionalprogramming

object HOFsCurries extends App {
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // Functions that either, take functions as parameters or return functions as results are called:
  //  ===> High Order Functions

  // map, flatMap, filter in MyList

  // A function that applies a function n times over a value x
  // nTimes(f, x, n)
  // nTimes(f, x, 3) = f(f(f(x)))
  /**
   *
   * @param f The function to be applied
   * @param x The value that is subject to function application
   * @param n The number of times the function is applied
   * @return The value resulting after the application of the chained function calls
   */
  def nTimes(f: Int => Int, x: Int, n: Int): Int =
    if (n <= 0) x
    else nTimes(f, f(x), n - 1)

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 1, 10))

  /**
   *
   * @param f The function to be applied
   * @param n The number of times the function is applied
   * @return A function
   */
  // nTimesBetter(f, n) = x => f(f(f...(x)))
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val plusTen = nTimesBetter(plusOne, 10)
  println(plusTen(1))

  // Curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  println(superAdder(10)(3))

  // Functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
}
