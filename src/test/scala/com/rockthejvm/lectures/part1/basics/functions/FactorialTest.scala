package com.rockthejvm
package lectures.part1.basics.functions

import lectures.part1.basics.Functions.factorial

import org.scalatest.freespec.AnyFreeSpec

class FactorialTest extends AnyFreeSpec {
  
  "A number" - {
    "when their factorial is computed" - {
      "should be correct" in {
        // Positive tests
        assert(factorial(0) == 1) // Edge case: factorial of zero
        assert(factorial(1) == 1) // Edge case: factorial of one
        assert(factorial(5) == 120) // Test: factorial of five
        assert(factorial(10) == 3628800) // Test: factorial of ten
        assert(factorial(20) == 2432902008176640000L) // Edge case: factorial of a large number
      }
      "should fail" in {
        // Negative tests
        intercept[IllegalArgumentException] {
          factorial(-1) // Test: negative number
          factorial(-5) // Test: another negative number
          factorial(-10) // Test: larger negative number
          factorial(-20) // Test: even larger negative number
          factorial(-100) // Edge case: very large negative number
        }
      }
    }
  }
}
