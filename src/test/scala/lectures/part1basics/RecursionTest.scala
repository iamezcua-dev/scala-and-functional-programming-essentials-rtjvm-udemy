package com.rockthejvm
package lectures.part1basics

import lectures.part1basics.Recursion.{concatenateStringTailrec, isPrimeTailrec, fibonacciTailrec}

import org.scalatest.wordspec.AnyWordSpec

class RecursionTest extends AnyWordSpec {

  // concatenateString
  "A string-concatenation function" should {
    "concatenate the string \"Hello, Scala\" three times" in {
      assert(concatenateStringTailrec(3, "Hello, Scala") == "Hello, ScalaHello, ScalaHello, Scala")
    }
  }

  // isPrimeTailrec
  "An example of prime number" should {

    "not be 0" in {
      assert(!isPrimeTailrec(0))
    }

    "not be 1" in {
      assert(!isPrimeTailrec(1))
    }

    "be 2" in {
      assert(isPrimeTailrec(2))
    }

    "be 3" in {
      assert(isPrimeTailrec(3))
    }

    "not be 4" in {
      assert(!isPrimeTailrec(4))
    }

    "be 5" in {
      assert(isPrimeTailrec(5))
    }

    "be 7" in {
      assert(isPrimeTailrec(7))
    }

    "not be 9" in {
      assert(!isPrimeTailrec(9))
    }

    "be 11" in {
      assert(isPrimeTailrec(11))
    }

    "be 89" in {
      assert(isPrimeTailrec(89))
    }

    "be 97" in {
      assert(isPrimeTailrec(97))
    }
  }

  // fibonaccitailrec
  "A fibonacciTailrec sum" when {
    "parameter is 1" should {
      "give back 1" in {
        assert(fibonacciTailrec(1) == 1)
      }
    }

    "parameter is 2" should {
      "give back 1" in {
        assert(fibonacciTailrec(2) == 1)
      }
    }

    "parameter is 3" should {
      "give back 2" in {
        assert(fibonacciTailrec(3) == 2)
      }
    }

    "parameter is 4" should {
      "give back 3" in {
        assert(fibonacciTailrec(4) == 3)
      }
    }
    
    "parameter is 5" should {
      "give back 5" in {
        assert(fibonacciTailrec(5) == 5)
      }
    }

    "parameter is 6" should {
      "give back 8" in {
        assert(fibonacciTailrec(6) == 8)
      }
    }

    "parameter is 8" should {
      "give back 21" in {
        assert(fibonacciTailrec(8) == 21)
      }
    }
  }
}
