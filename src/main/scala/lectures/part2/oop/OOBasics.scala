package com.rockthejvm
package lectures.part2.oop

import com.typesafe.scalalogging.LazyLogging

import java.time.LocalDate

object OOBasics extends App with LazyLogging {
  val person = new Person("John", 26)
  println(person.age)
  person.greet("Daniel")
  
  /**
   * Exercises:
   */
  /**
   *  - Novel and Writer
   *    - Writer:
   *      - fields:
   *        - first name, surname, yearOfBirth
   *      - methods:
   *        - full name
   *
   *    - Novel:
   *      - fields:
   *        - name, yearOfRelease, author
   *      - methods:
   *        - authorAge
   *        - isWrittenBy(author)
   *        - copy(newYearOfRelease): new instance of Novel
   */
  val shakespeare: Writer = new Writer("William", "Shakespeare", 1564)
  val hamlet: Novel = new Novel("The Tragedy of Hamlet, Prince of Denmark", 1599, shakespeare)
  logger.info(s"Writer's full name: ${shakespeare.fullName()}")
  
  // Novel author's today's age
  private val novelAuthorName: String = hamlet.author.fullName()
  logger.info(s"$novelAuthorName${if (novelAuthorName.endsWith("s")) "'" else "\'s"} age is ${hamlet.authorAge()}.")
  // Was a book written by author?
  private val wasWrittenByShakespeare = hamlet.isWrittenBy(shakespeare)
  logger.info(s"${hamlet.name}, ${if (wasWrittenByShakespeare) "was" else "n't"} written by ${shakespeare.fullName()}")
  logger.info(s"Year of release: ${hamlet.yearOfRelease}")
  // Simulating publishing a new edition of the same book
  logger.info(s"* Creating an updated version of ${hamlet.name}")
  private val hamlet2 = hamlet.copy(newYearOfRelease = 2024)
  logger.info(s"- Book name: ${hamlet2.name}")
  logger.info(s"- Author: ${hamlet2.author.fullName()}")
  logger.info(s"- Author age: ${hamlet2.authorAge()}")
  logger.info(s"- Year of release: ${hamlet2.yearOfRelease}")
  
  /**
   *  - Counter class:
   *    - receives an int value
   *    - methods:
   *      - current count
   *      - increment/decrement: new Counter
   *      - overload increment/decrement to receive an amount
   */
  private val counter = new Counter(7)
  logger.info(s"Current count: ${counter.currentCount()}")
  logger.info(s"Current count: ${counter.increment().currentCount()}")
  logger.info(s"Current count: ${counter.increment().increment().currentCount()}")
  logger.info(s"Current count: ${counter.increment().increment().increment().currentCount()}")
  logger.info(s"Current count: ${counter.increment().increment().increment().decrement().currentCount()}")
  logger.info(s"Current count: ${counter.increment(8).decrement(4).currentCount()}")
  
}

class Counter(val value: Int = 0) {
  def currentCount(): Int = this.value
  def increment(): Counter = new Counter(value + 1)
  def increment(value: Int): Counter = new Counter(this.value + value)
  def decrement(): Counter = new Counter(value - 1)
  def decrement(value: Int): Counter = new Counter(this.value - value)
}

class Writer(firstName: String, surname: String, val yearOfBirth: Int) {
  def fullName(): String = {
    s"$firstName $surname"
  }
}

class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {
  def authorAge(): Int = LocalDate.now().getYear - author.yearOfBirth
  def isWrittenBy(author: Writer): Boolean = this.author.fullName() == author.fullName()
  def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}

class Person(name: String, val age: Int = 0) {
  val x = 2
  
  println(1 + 4)
  
  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")
  
  // overloading
  def greet(): Unit = println(s"Hi, I am $name")
  
  // multiple constructor
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}
