package com.rockthejvm
package lectures.part1.basics.functions

import lectures.part1.basics.Functions.fibonacci

import org.scalatest.freespec.AnyFreeSpec

class FibonacciTest extends AnyFreeSpec {
  
  "A Fibonacci function" - {
    
    "when called with n = 0" - {
      "should return 0" in {
        assert(fibonacci(0) == 0)
      }
    }
    
    "when called with n = 1" - {
      "should return 1" in {
        assert(fibonacci(1) == 1)
      }
    }
    
    "when called with n = 2" - {
      "should return 1" in {
        assert(fibonacci(2) == 1)
      }
    }
    
    "when called with n = 3" - {
      "should return 2" in {
        assert(fibonacci(3) == 2)
      }
    }
    
    "when called with n = 4" - {
      "should return 3" in {
        assert(fibonacci(4) == 3)
      }
    }
    
    "when called with n = 5" - {
      "should return 5" in {
        assert(fibonacci(5) == 5)
      }
    }
    
    "when called with n = 6" - {
      "should return 8" in {
        assert(fibonacci(6) == 8)
      }
    }
    
    "when called with n = 10" - {
      "should return 55" in {
        assert(fibonacci(10) == 55)
      }
    }
    
    "when called with n = 15" - {
      "should return 610" in {
        assert(fibonacci(15) == 610)
      }
    }
    
    "when called with n = 20" - {
      "should return 6765" in {
        assert(fibonacci(20) == 6765)
      }
    }
    
    "when called with a negative number" - {
      "should throw IllegalArgumentException" in {
        assertThrows[IllegalArgumentException] {
          fibonacci(-1)
          fibonacci(-16)
          fibonacci(-200)
        }
      }
    }
  }
}
