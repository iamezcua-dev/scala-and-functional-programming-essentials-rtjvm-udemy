package lectures.part2.oop.objectorientedbasics

class Novel( name: String, yearOfRelease: Int, author: Writer ) {
  def authorAge = yearOfRelease - author.year
  
  def isWrittenBy( author: Writer ) = this.author == author
  
  def copy( newYearOfRelease: Int ): Novel = new Novel( name, newYearOfRelease, author )
}
