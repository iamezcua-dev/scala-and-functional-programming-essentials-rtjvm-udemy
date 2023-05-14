package com.rockthejvm
package lectures.part2oop

object Generics extends App {
  class MyList[+A] {
    // Use the type A
    def add[B >: A](element: B): MyList[B] = ???
  }
  
  class MyMap[Key, Value]
  
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]
  
  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  
  val emptyListOfIntegers = MyList.empty[Int]
  
  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal
  
  // Question: If Cat extends Animal, does a List[Cat] also extend List[Animal]?
  
  // 1) Yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  //  animalList.add(new Dog) => ??? Hard Question. Answer: We would get a List[Animal].
  
  // 2) No. = INVARIANCE
  class InvariantList[A]
  
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]
  
  // 3) Hell, no! CONTRAVARIANCE
  class Trainer[-A]
  
  val trainer: Trainer[Cat] = new Trainer[Animal]
  
  // Bounded types
  class Cage[A <: Animal](animal: A)
  
  val cage = new Cage(new Dog)
  
  class Car
  
  //  val newCage = new Cage(new Car)
}
