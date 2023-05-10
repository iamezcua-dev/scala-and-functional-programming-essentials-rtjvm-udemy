package com.rockthejvm
package lectures.part2oop

import scala.annotation.targetName
import scala.language.postfixOps

object MethodNotations extends App {
  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    @targetName("plus")
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    @targetName("not")
    def unary_! : String = s"$name, what the heck?!"

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
  }

  private val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // infix notation

  // Operators in Scala
  private val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // All operators are methods
  // Akka actors have ! ?

  // Prefix notation
  val x = -1
  val y = 1.unary_-
  // unary_ prefix only works with + - ~ !

  println(!mary)
  println(mary.unary_!)

  // Postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equivalent
}
