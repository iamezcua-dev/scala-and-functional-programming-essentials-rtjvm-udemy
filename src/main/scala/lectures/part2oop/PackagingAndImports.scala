package com.rockthejvm
package lectures.part2oop

import lectures.part2oop.exercises.Writer
import playground.{ Cinderella, PrinceCharming }

import java.sql.Date as SqlDate
import java.util.Date


object PackagingAndImports extends App {
  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)
  
  // import the package
  val princess = new Cinderella // playground.Cinderella = fully-qualified name
  
  // packages are in hierarchy
  // matching folder structure
  
  // package object
  sayHello()
  println(SPEED_OF_LIGHT)
  
  // imports
  val prince = new PrinceCharming()
  
  val date = new SqlDate(2023, 5, 4)
  val sqlDate = new Date()
  
  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
