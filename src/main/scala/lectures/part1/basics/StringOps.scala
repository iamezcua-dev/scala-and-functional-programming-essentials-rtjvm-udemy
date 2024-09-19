package com.rockthejvm
package lectures.part1.basics

object StringOps extends App {
  val str: String = "Hello, I am learning Scala"
  
  println(str.charAt(3))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length)
  
  val aNumberString = "2"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')
  println(str.reverse)
  println(str.take(2))
  
  
  // Scala-specific: String interpolators.
  
  // S-interpolators
  val name = "Erena"
  val age = 28
  val greeting = s"Hello, my name is $name, and I am $age years old"
  val anotherGreeting = s"Hello, my name is $name, and I will be turning ${age + 1} years old."
  println(greeting)
  println(anotherGreeting)
  
  // F-interpolators
  val speed = 1.2f
  val myth = f"$name can finish $speed%2.2f courses per month."
  println(myth)
  println("The aforementioned is actually true, isn't it? 🐸")
  
  // raw-interpolators
  println(raw"This is a \n newline")
  val escaped = "This is a \n newline"
  println(raw"$escaped")
}
