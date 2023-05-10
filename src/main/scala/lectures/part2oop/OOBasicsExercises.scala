package com.rockthejvm
package lectures.part2oop

import lectures.part2oop.Counter
import lectures.part2oop.Counter.{DECREMENT, INCREMENT}

import java.time.LocalDateTime

object OOBasicsExercises extends App {
  private val author = new Writer("José", "Saramago", 1922)
  private val novel = new Novel("Ensayo Sobre la Ceguera", 1988, author)
  private val novel2 = novel.copy(2023)
  println(author.fullName)
  println(novel.authorAge)
  println(novel.isWrittenBy("José Saramago"))

  private val counter = new Counter(0)
  println(
    counter
      .update(Counter.INCREMENT)
      .update(Counter.INCREMENT)
      .update(Counter.INCREMENT)
      .update(Counter.INCREMENT)
      .update(Counter.DECREMENT)
      .update(Counter.INCREMENT, 20)
      .getCurrentCount
  )
}

class Writer(firstName: String, surname: String, val year: Int) {
  def fullName: String = s"$firstName $surname"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge: Int = {
    val ongoingYear = LocalDateTime.now.getYear
    ongoingYear - this.author.year
  }

  def isWrittenBy(authorName: String): Boolean = {
    authorName.equals(this.author.fullName)
  }

  def copy(newYear: Int): Novel = {
    new Novel(name, newYear, author)
  }
}

class Counter(n: Int) {
  def getCurrentCount: Int = this.n

  def update(operation: Int): Counter = {
    if operation == INCREMENT then new Counter(getCurrentCount + 1)
    else if operation == DECREMENT then new Counter(getCurrentCount - 1)
    else throw new UnsupportedOperationException("Unknown operation")
  }

  def update(operation: Int, value: Int): Counter = {
    val absolute_value = value.abs
    if operation == INCREMENT then new Counter(getCurrentCount + absolute_value)
    else if operation == DECREMENT then new Counter(getCurrentCount - absolute_value)
    else throw new UnsupportedOperationException("Unknown operation")
  }
}

object Counter {
  val INCREMENT = 1
  val DECREMENT = -1
}
