package com.rockthejvm
package lectures.part2oop

object CaseClasses extends App {
  /*
    equals, hashCode, toString
  */
  case class Person(name: String, age: Int)
  
  // 1. class parameters are promoted as fields
  val jim = new Person("Jim", 34)
  println(jim.name)
  
  // 2. sensible toString
  // println(instance) == println(instance.toString) // syntactic sugar
  println(jim)
  
  // 3. equals and hashCode are implemented out of the box
  private val jim2 = new Person("Jim", 34)
  println(jim == jim2)
  
  // 4. Case classes have handy copy method
  val jim3 = jim.copy(age = 45)
  
  // 5. Case classes have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)
  
  // 6. Case classes are serializable
  // this is useful in frameworks such as Akka
  
  // 7. Case classes have extractor patterns = Case classes can be used in Pattern Matching
  case class UnitedKingdom() {
    def name: String = "The UK of GB and NI"
    
  }
  
  /*
    Expand MyList - use case classes and case objects
  */
}
