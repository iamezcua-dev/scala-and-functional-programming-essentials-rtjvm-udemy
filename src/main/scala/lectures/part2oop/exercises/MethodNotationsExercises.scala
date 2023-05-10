package com.rockthejvm
package lectures.part2oop.exercises

import lectures.part2oop.exercises.Person

import scala.annotation.targetName

object MethodNotationsExercises extends App {
  private val vett = new Person("Vett", "Todos los hombres de los que me enamoré")
  private val `pingüina`: Person = vett + "La Pingüina"
  println(`pingüina`.name)
}

// 1) Overload the + operator
//  e.g.: mary + "The Rockstar" => new Person("Mary (The Rockstar)")
class Person(val name: String, favoriteMovie: String) {
  def likes(movie: String): Boolean = movie == favoriteMovie

  @targetName("plus")
  def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

  def +(nickname: String): Person = new Person(s"${this.name} ($nickname)", this.favoriteMovie)

  @targetName("not")
  def unary_! : String = s"$name, what the heck?!"

  def isAlive: Boolean = true

  def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
}
