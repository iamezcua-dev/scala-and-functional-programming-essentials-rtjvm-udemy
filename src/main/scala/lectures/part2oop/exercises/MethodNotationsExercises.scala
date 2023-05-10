package com.rockthejvm
package lectures.part2oop.exercises

import lectures.part2oop.exercises.Person

import scala.annotation.targetName
import scala.language.postfixOps

object MethodNotationsExercises extends App {
  private val vett = new Person("Vett", "To All the Boys I've Loved Before")
  private val `pingüina`: Person = vett + "La Pingüina"
  println(`pingüina`())

  private val mary = Person("Mary", "Inception", 27)
  println(mary.age)
  private val olderMary = +mary // infix postfix
  println(olderMary.age)

  println(vett.learns())
  println(vett learns "French")

  println(vett(20000))
  println(mary(5))
}

// 1) Overload the + operator
//  e.g.: mary + "The Rockstar" => new Person("Mary (The Rockstar)")
// 2) Add an age to the Person class
//  Add an unary + operator => new Person with the age plus one
//  e.g.: +mary => mary with the age incremented
// 3) Add a "learns" method in the Person class => "Mary learns Scala"
//  Add a learnsScala method, calls learns method with Scala
//  Use it in postfix notation
// 4) Overload the apply method:
//  mary.apply(2) => Mary watched inception 2 times
class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
  def likes(movie: String): Boolean = movie == favoriteMovie

  @targetName("hangingWith")
  def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

  @targetName("withNickname")
  def +(nickname: String): Person = new Person(s"${this.name} ($nickname)", this.favoriteMovie)

  @targetName("oneYearOlder")
  def unary_+ : Person = new Person(this.name, this.favoriteMovie, this.age + 1)

  @targetName("not")
  def unary_! : String = s"$name, what the heck?!"

  def isAlive: Boolean = true

  def apply(): String = s"Hi, my name is $name and I like \"$favoriteMovie\""

  def apply(times: Int) = s"$name watched \"$favoriteMovie\" $times times."

  def learns(subject: String = "Scala"): String = s"$name learns $subject"

}
