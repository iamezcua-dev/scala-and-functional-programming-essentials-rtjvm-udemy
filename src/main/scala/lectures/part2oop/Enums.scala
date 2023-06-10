package com.rockthejvm
package lectures.part2oop

object Enums {
  private enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    // add fields/methods
    def openDocument(): Unit =
      if this == READ then println("Opening document ...")
      else println("Reading not allowed")
  }

  private val somePermissions: Permissions = Permissions.READ

  // constructor args
  private enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) // 100
    case WRITE extends PermissionsWithBits(2) // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0) // 000
  }

  private object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = // Whatever
      PermissionsWithBits.NONE
  }

  // standard API
  private val somePermissionsOrdinal = somePermissions.ordinal
  private val allPermissions = PermissionsWithBits.values // Array of all possible values of the enum
  private val readPermission: Permissions = Permissions.valueOf("READ") // Permissions.READ

  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionsOrdinal)
    allPermissions foreach println
    println(readPermission)
  }
}
