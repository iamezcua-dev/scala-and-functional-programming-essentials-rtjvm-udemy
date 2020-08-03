package exercises

abstract class Maybe[ +T ] {
  def map[ U ]( f: T => U ): Maybe[ U ]
  def filter( condition: T => Boolean ): Maybe[ T ]
  def flatMap[ U ]( f: T => Maybe[ U ] ): Maybe[ U ]
  def listContents: String
  override def toString: String = s"$listContents"
}

case object MaybeNot extends Maybe[ Nothing ] {
  override def map[ U ]( f: Nothing => U ): Maybe[ U ] = MaybeNot
  override def flatMap[ U ]( f: Nothing => Maybe[ U ] ): Maybe[ U ] = MaybeNot
  override def filter( condition: Nothing => Boolean ): Maybe[ Nothing ] = MaybeNot
  override def listContents: String = ""
}

case class Just[ +T ]( value: T ) extends Maybe[ T ] {
  override def map[ U ]( f: T => U ): Maybe[ U ] =
    Just( f( value ) )
  
  override def flatMap[ U ]( f: T => Maybe[ U ] ): Maybe[ U ] = f( value )
  
  override def filter( condition: T => Boolean ): Maybe[ T ] =
    if ( condition( value ) ) this
    else MaybeNot
  
  override def listContents: String = s"$value"
}
