package lectures.part1.basics

object StringOps extends App {
	val str: String = "Hello, I am learning Scala"
	
	println( str.charAt( 2 ) )
	println( str.substring( 7, 11 ) )
	println( str.split( " " ).toList )
	println( str.startsWith( "Hello" ) )
	println( str.replace( " ", "-" ) )
	println( str.toLowerCase )
	println( str.length )
	
	val aNumberString = "2"
	val aNumber = aNumberString.toInt
	// Prepends the character 'a' to `aNumberString`
	println( 'a' +: aNumberString :+ 'z' )
	
	// Prepending and appending operators are scala specific.
	
	println( str.reverse )
	println( str.take( 2 ) )
	
	/*
		Scala-specific: String interpolators
	 */
	
	/*
	 	S-interpolators
	 */
	val name = "David"
	val age = 12
	val greeting = s"Hello, my name is $name and I am $age years old."
	val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old."
	println( anotherGreeting )
	
	/*
		F-interpolators
	 */
	val speed = 1.2f
	val myth = f"$name%s can eat $speed%4.2f burgers per minute"
	println( myth )
	
	/*
		raw-interpolator
	 */
	
	// raw interpolator ignores escaped characters
	println( raw"This is a newline: \n" )
	// raw interpolator is unable to ignore characters that are inside variables
	val escaped = "This is a new line: \n"
	println( raw"** $escaped **" )
	// if this is the expected behavior, then the variable must be rawed at
	val rawEscaped = raw"This is a new line: \n"
	println( raw"** $escaped **" )
}
