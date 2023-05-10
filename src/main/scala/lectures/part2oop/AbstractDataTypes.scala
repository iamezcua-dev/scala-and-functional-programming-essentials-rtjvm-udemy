package com.rockthejvm
package lectures.part2oop

object AbstractDataTypes extends App {
  // abstract
  abstract class Animal {
    val creatureType: String

    def eat(): Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    def eat(): Unit = println("crunch crunch")
  }

  trait Carnivore {
    def eat(animal: Animal): Unit
    def preferredMeal: String = "fresh meat"
  }

  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Croc"
    override def eat(): Unit = println("nomnomnom")
    override def eat(animal: Animal): Unit = println(s"I'm a $creatureType and I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc eat dog
  println(croc.preferredMeal)
  
  // Traits vs abstract classes
  // 1) Traits do not have constructors
  // 2) Multiple traits may be inherited by the same class
  // 3) Traits define BEHAVIOR, whereas Abstract classes define THINGS
}
