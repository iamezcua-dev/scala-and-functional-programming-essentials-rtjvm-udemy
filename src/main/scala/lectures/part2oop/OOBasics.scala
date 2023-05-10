package com.rockthejvm
package lectures.part2oop

import lectures.part2oop.Person

object OOBasics extends App {
  private val person = new Person("John", 26)
  println(person.age)
  person.greet("Daniel")
  person.greet()
}

// Constructor
class Person(name: String, val age: Int) {
  val x = 2

  println(1 + 3)

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // method overloading: same name, but different signatures
  def greet(): Unit = println(s"Hi, I am $name")

  // Multiple constructors
  def this(name: String) = this(name, 0)

  def this() = this("John Doe")

}

// Class parameters are not FIELDS!