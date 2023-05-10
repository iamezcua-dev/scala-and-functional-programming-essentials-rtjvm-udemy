package com.rockthejvm
package lectures.part2oop

object InheritanceAndTraits extends App {
  private class Animal {
    val creatureType = "Wild"

    def eat(): Unit = println("NomNomNom")
  }

  private class Cat extends Animal {
    def crunch(): Unit = {
      eat()
      println("Crunch Crunch Crunch")
    }
  }

  private val cat = new Cat
  cat.crunch()

  // Constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // Overriding
  private class Dog(override val creatureType: String) extends Animal {
    // override val creatureType = "domestic"
    override def eat(): Unit = {
      super.eat()
      println("Crunch, Crunch!")
    }
  }

  private val dog = new Dog(creatureType = "K9")
  dog.eat()
  println(dog.creatureType)

  // Type Substitution (broad: polymorfism)
  private val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat()

  // Overriding vs Overloading

  // super

  // preventing overrides
  // 1) Use final on member or fields.
  // 2) Use final on the entire class.
  // 3) Seal the class = extend classes in THIS FILE, prevent extension in other files.
}
