package com.rockthejvm
package lectures.part1basics

import org.scalatest.wordspec.AnyWordSpec
import lectures.part1basics.Functions.{factorial, fibonacci, isPrime}

import scala.math.BigInt

class FunctionsTest extends AnyWordSpec {

  "A Fibonacci sum" should {
    "give back 1" in {
      assert(fibonacci(1) == 1)
    }

    "give back 2" in {
      assert(fibonacci(2) == 1)
    }

    "give back 3" in {
      assert(fibonacci(3) == 2)
    }

    "give back 4" in {
      assert(fibonacci(4) == 3)
    }

    "give back 5" in {
      assert(fibonacci(5) == 5)
    }

    "give back 6" in {
      assert(fibonacci(6) == 8)
    }

    "give back 8" in {
      assert(fibonacci(8) == 21)
    }

  }

  "Factorial of 8" should {
    "give back 40320" in {
      assert(factorial(8) == BigInt(40320))
    }
  }

  "An example of prime number" should {

    "not be 0" in {
      assert(!isPrime(0))
    }
    
    "not be 1" in {
      assert(!isPrime(1))
    }

    "be 2" in {
      assert(isPrime(2))
    }

    "be 3" in {
      assert(isPrime(3))
    }

    "not be 4" in {
      assert(!isPrime(4))
    }

    "be 5" in {
      assert(isPrime(5))
    }

    "be 7" in {
      assert(isPrime(7))
    }

    "not be 9" in {
      assert(!isPrime(9))
    }

    "be 11" in {
      assert(isPrime(11))
    }

    "be 89" in {
      assert(isPrime(89))
    }

    "be 97" in {
      assert(isPrime(97))
    }
  }
}
