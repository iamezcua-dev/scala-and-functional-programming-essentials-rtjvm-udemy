package com.rockthejvm
package lectures.part3fp

import scala.annotation.tailrec

object HOFsCurries extends App {
  /**
   * Applies a provided function n times and accumulates the result each time.
   *
   * @param f The function to apply.
   * @param n The number of times <code>f</code> should be applied.
   * @param x The accumulator used on each iteration.
   * @return The accumulated value of <code>x</code>.
   */
  @tailrec
  private def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if n <= 0 then x
    else nTimes(f, n - 1, f(x))
  }

  private val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 100, 1))

  // ntb(f, n) = x => f(f(f...(x)))
  // increment10 = ntb(plusOne, 10) = x => plusOne(plusOne...(x))

  /**
   *
   *
   * @param f
   * @param n
   * @return
   */
  private def nTimesBetter(f: Int => Int, n: Int): Int => Int = {
    if n <= 0 then (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))
  }

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(0))

  /*
  (x: Int) =>                                 nTimesBetter(f, 9)(f(0))
  (f(0): Int) =>                              nTimesBetter(f, 8)(f(f(0)))
  (f(f(0)): Int) =>                           nTimesBetter(f, 7)(f(f(f(0))))
  (f(f(f(0))): Int) =>                        nTimesBetter(f, 6)(f(f(f(f(0)))))
  (f(f(f(f(0)))): Int) =>                     nTimesBetter(f, 5)(f(f(f(f(f(0))))))
  (f(f(f(f(f(0))))): Int) =>                  nTimesBetter(f, 4)(f(f(f(f(f(f(0)))))))
  (f(f(f(f(f(f(0)))))): Int) =>               nTimesBetter(f, 3)(f(f(f(f(f(f(f(0))))))))
  (f(f(f(f(f(f(f(0))))))): Int) =>            nTimesBetter(f, 2)(f(f(f(f(f(f(f(f(0)))))))))
  (f(f(f(f(f(f(f(f(0)))))))): Int) =>         nTimesBetter(f, 1)(f(f(f(f(f(f(f(f(f(0))))))))))
  (f(f(f(f(f(f(f(f(f(0))))))))): Int) =>      nTimesBetter(f, 0)(f(f(f(f(f(f(f(f(f(f(0)))))))))))
  (f(f(f(f(f(f(f(f(f(f(0)))))))))): Int) =>  f(f(f(f(f(f(f(f(f(f(0)))))))))) = 10
   */

  // curried functions
  private val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  private val add3: Int => Int = superAdder(3)
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  private def curriedFormatter(c: String)(x: Double): String = c.format(x)

  private val standardFormat: Double => String = curriedFormatter("%4.2f")
  private val preciseFormat: Double => String = curriedFormatter("%10.8f")
  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

}