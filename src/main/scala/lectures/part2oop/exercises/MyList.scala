package com.rockthejvm
package lectures.part2oop.exercises

import scala.annotation.{tailrec, targetName}


abstract class MyList[+A] {
  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
  */
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  override def toString: String = s"[ $printElements ]"

  def map[B >: A](transformer: A => B): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  @targetName("concatenateWith")
  def ++[B >: A](anotherList: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)

  def printElements: String = ""

  def map[B >: Nothing](transformer: Nothing => B): MyList[B] = Empty

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  @targetName("concatenateWith")
  def ++[B >: Nothing](anotherList: MyList[B]): MyList[B] = anotherList

}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = Cons(element, this)

  override def printElements: String =
    if t.isEmpty then s"$h"
    else s"$h, ${t.printElements}"

  def map[B >: A](transformer: A => B): MyList[B] = {
    Cons(transformer(head), tail.map(transformer))
  }

  def filter(predicate: A => Boolean): MyList[A] = {
    if predicate(this.head) then Cons(head, tail.filter(predicate))
    else tail.filter(predicate)
  }

  def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer(head) ++ tail.flatMap(transformer)
  }

  @targetName("concatenateWith")
  def ++[B >: A](anotherList: MyList[B]): MyList[B] = {
    Cons(head, tail ++ anotherList)
  }

  /*
  Desktop test:
  
  [1, 2, 3] ++ [8, 9, 10] =
  = [1, [2, 3] ++ [8, 9, 10]]
  = [1, 2, [3] ++ [8, 9, 10]]
  = [1, 2, [3] ++ [8, 9, 10]]
  = [1, 2, 3, Empty ++ [8, 9, 10]]
  = [1, 2, 3, 8, 9, 10]
  */
}


object ListTest {
  @main
  def main(): Unit = {
    val listOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
    val cloneListOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
    val anotherListOfIntegers: MyList[Int] = Cons(4, Cons(5, Empty))
    val listOfStrings: MyList[String] = Cons("Hello", Cons("Scala", Empty))

    println(listOfIntegers)
    println(listOfStrings)

    val doubler: Int => Int = _ * 2
    println(listOfIntegers.map(doubler))

    val evenNumbersOnlyFilter: Int => Boolean = _ % 2 == 0

    println(listOfIntegers.filter(evenNumbersOnlyFilter))

    println(listOfIntegers ++ anotherListOfIntegers)

    val elementAndElementPlusOneTransformer: Int => MyList[Int] = element => Cons(element, Cons(element + 1, Empty))
    println(listOfIntegers.flatMap(elementAndElementPlusOneTransformer))

    // Testing case-class instances with the same data
    println(cloneListOfIntegers == listOfIntegers) // this should be true, since the list's hasCode should be equal 
  }
}