package com.rockthejvm
package lectures.part1.basics

import scala.annotation.tailrec

object DefaultArgs extends App {
  @tailrec
  private def tailrecFactorial(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else tailrecFactorial(n - 1, n * acc)
  }
  
  val fact10 = tailrecFactorial(10, 2)
  
  private def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture ...")
  savePicture()
  savePicture(width = 800)
  
  /**
   * In case you want to provide a default value not located at the beginning, either:
   *  - you should pass every leading required parameter until you reach the default parameter you want to change.
   *  - you name the parameter(s) you want to modify
   *
   * e.g:
   */
  savePicture(height = 600)
  savePicture(height = 1280, width = 1024, format = "tiff")
}
