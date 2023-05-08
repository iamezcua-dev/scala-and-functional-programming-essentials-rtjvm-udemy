package com.rockthejvm
package lectures.part1basics

import scala.annotation.tailrec

object CBNvsCBV extends App {
  def calledByValue(x: Long): Unit = {
    println(s"By value $x")
    println(s"By value $x")
  }

  def calledByName(x: => Long): Unit = {
    println(s"By name $x")
    println(s"By name $x")
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  @tailrec
  def infinite(): Int = infinite()

  def printFirst(x: Int, y: => Int): Unit = println(x)

  //  printFirst(infinite(), 34)
  printFirst(34, infinite())
}
