package lectures.part2.oop

import java.util.Date
import java.sql.{Date => SqlDate}

import lectures.part2.oop.objectorientedbasics.Writer
import playground.{PrinceCharming, Cinderella => Princess}

object PackagingAndImports extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Isaac", "Amezcua", 1988)

  // import the package
  val princess = new Princess // Using aliasing
  // or
  val princess2 = new playground.Cinderella

  // Package object (rarely used)
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // 1. Using fully-qualified name
  val date = new Date
  val sqlDate = new SqlDate(2020, 5, 5)

  // Default imports:
  //  - java.lang - String, Object, Exception
  //  - scala - Int, Nothing, Function
  //  - scala.Predef - println, ???
}
