package com.rockthejvm
package lectures.part2oop.exercises

import scala.annotation.{ tailrec, targetName }


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
  def map[B >: A](transformer: MyTransformer[A, B]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  @targetName("concatenateWith")
  def ++[B >: A](anotherList: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)
  def printElements: String = ""
  def map[B >: Nothing](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
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
  def map[B >: A](transformer: MyTransformer[A, B]): MyList[B] = {
    Cons(transformer.transform(head), tail.map(transformer))
  }
  def filter(predicate: MyPredicate[A]): MyList[A] = {
    if predicate.test(this.head) then Cons(head, tail.filter(predicate))
    else tail.filter(predicate)
  }
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(head) ++ tail.flatMap(transformer)
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

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}

object ListTest {
  @main
  def main(): Unit = {
    val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
    val cloneMyList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
    println(myList)
    println(myList == cloneMyList)
    
    val anotherList: MyList[String] = Cons("Hello", Cons("Scala", Empty))
    println(anotherList)
  }
}