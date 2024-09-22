package com.rockthejvm
package lectures.part2.oop

object OOBasics extends App {
  val person = new Person("John", 26)
  println(person.age)
  person.greet("Daniel")
}

class Person(name: String, val age: Int = 0) {
  val x = 2
  
  println(1 + 4)
  
  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")
  
  // overloading
  def greet(): Unit = println(s"Hi, I am $name")
  
  // multiple constructor
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}
