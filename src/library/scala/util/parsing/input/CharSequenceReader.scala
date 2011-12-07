/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2006-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */


package scala.util.parsing.input

/** An object encapsulating basic character constants.
 *
 * @author Martin Odersky, Adriaan Moors
 */
object CharSequenceReader {
  final val EofCh = '\032'
}

<<<<<<< HEAD
/** A character array reader reads a stream of characters (keeping track of their positions) 
=======
/** A character array reader reads a stream of characters (keeping track of their positions)
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * from an array.
 *
 * @param source the source sequence
 * @param offset  starting offset.
 *
<<<<<<< HEAD
 * @author Martin Odersky 
 */
class CharSequenceReader(override val source: java.lang.CharSequence, 
=======
 * @author Martin Odersky
 */
class CharSequenceReader(override val source: java.lang.CharSequence,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                         override val offset: Int) extends Reader[Char] {
  import CharSequenceReader._

  /** Construct a `CharSequenceReader` with its first element at
   *  `source(0)` and position `(1,1)`.
   */
  def this(source: java.lang.CharSequence) = this(source, 0)

  /** Returns the first element of the reader, or EofCh if reader is at its end.
   */
<<<<<<< HEAD
  def first = 
    if (offset < source.length) source.charAt(offset) else EofCh 

  /** Returns a CharSequenceReader consisting of all elements except the first.
   * 
=======
  def first =
    if (offset < source.length) source.charAt(offset) else EofCh

  /** Returns a CharSequenceReader consisting of all elements except the first.
   *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * @return If `atEnd` is `true`, the result will be `this`;
   *         otherwise, it's a `CharSequenceReader` containing the rest of input.
   */
  def rest: CharSequenceReader =
    if (offset < source.length) new CharSequenceReader(source, offset + 1)
    else this

  /** The position of the first element in the reader.
   */
  def pos: Position = new OffsetPosition(source, offset)

  /** true iff there are no more elements in this reader (except for trailing
   *  EofCh's)
   */
  def atEnd = offset >= source.length
<<<<<<< HEAD
    
  /** Returns an abstract reader consisting of all elements except the first
   *  `n` elements.
   */ 
  override def drop(n: Int): CharSequenceReader = 
=======

  /** Returns an abstract reader consisting of all elements except the first
   *  `n` elements.
   */
  override def drop(n: Int): CharSequenceReader =
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    new CharSequenceReader(source, offset + n)
}
