package com.rockthejvm
package lectures.part2oop

object Objects {
  // Scala does not have class-level functionality ("static" keyword)
  object Person {
    val N_EYES = 2

    def canFly: Boolean = false

    // Factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {

  }

  @main
  def main(): Unit = {
    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = Singleton Instance
    val mary = new Person("Mary")
    val john = new Person("John")
    print("Are both Mary and John the same objects? ")
    println(mary == john)

    val person1 = Person
    val person2 = Person
    print("Are both persons the same object? ")
    println(person1 == person2)

    val bobbie = Person(mary, john)
  }

}
