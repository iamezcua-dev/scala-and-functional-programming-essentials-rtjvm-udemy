package com.rockthejvm
package lectures.part1.basics.functions

import lectures.part1.basics.Functions.isPrime

import org.scalatest.freespec.AnyFreeSpec

class PrimesTest extends AnyFreeSpec {
  
  "A number" - {
    "when can only be divided by 1 or itself" - {
      "should be prime, like" in {
        assert(isPrime(2)) // edge case: smallest prime number
        assert(isPrime(3)) // small prime number
      }
    }
    "when can be divided by other than 1 or itself" - {
      "should not be a prime number, like" in {
        assert(!isPrime(0)) // edge case: not prime
        assert(!isPrime(1)) // edge case: not prime
        assert(!isPrime(-1)) // negative number: not prime
        assert(!isPrime(4)) // small non-prime number
      }
    }
  }
}
