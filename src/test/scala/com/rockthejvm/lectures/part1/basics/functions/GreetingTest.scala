package com.rockthejvm
package lectures.part1.basics.functions

import lectures.part1.basics.Functions.greeting

import org.scalatest.freespec.AnyFreeSpec

class GreetingTest extends AnyFreeSpec {
  
  "The greeting function" - {
    "should return 'Hi, my name is Alice, and I am 30 years old.' for input 'Alice' with age 30" in {
      assert(greeting("Alice", 30) == "Hi, my name is Alice, and I am 30 years old.")
    }
    "should return 'Hi, my name is Bob, and I am 25 years old.' for input 'Bob' with age 25" in {
      assert(greeting("Bob", 25) == "Hi, my name is Bob, and I am 25 years old.")
    }
    "should return 'Hi, my name is Carol, and I am 40 years old.' for input 'Carol' with age 40" in {
      assert(greeting("Carol", 40) == "Hi, my name is Carol, and I am 40 years old.")
    }
    "should return 'Hi, my name is Dave, and I am 35 years old.' for input 'Dave' with age 35" in {
      assert(greeting("Dave", 35) == "Hi, my name is Dave, and I am 35 years old.")
    }
    "should return 'Hi, my name is Eve, and I am 20 years old.' for input 'Eve' with age 20" in {
      assert(greeting("Eve", 20) == "Hi, my name is Eve, and I am 20 years old.")
    }
  }
}