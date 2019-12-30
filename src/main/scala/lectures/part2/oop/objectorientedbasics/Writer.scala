package lectures.part2.oop.objectorientedbasics

class Writer( firstName: String, surname: String, val year: Int ) {
  def fullName = s"$firstName $surname"
}