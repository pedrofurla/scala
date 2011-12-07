/* NSC -- new Scala compiler
 * Copyright 2005-2011 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.reflect.internal.util

object HashSet {
  def apply[T >: Null <: AnyRef](): HashSet[T] = this(16)
  def apply[T >: Null <: AnyRef](label: String): HashSet[T] = this(label, 16)
  def apply[T >: Null <: AnyRef](initialCapacity: Int): HashSet[T] = this("No Label", initialCapacity)
  def apply[T >: Null <: AnyRef](label: String, initialCapacity: Int): HashSet[T] =
    new HashSet[T](label, initialCapacity)
}

class HashSet[T >: Null <: AnyRef](val label: String, initialCapacity: Int) extends Set[T] {
  private var used = 0
  private var table = new Array[AnyRef](initialCapacity)
  private def index(x: Int): Int = math.abs(x % table.length)

  def size: Int = used
  def clear() {
    used = 0
    table = new Array[AnyRef](initialCapacity)
  }

  def findEntryOrUpdate(x: T): T = {
    var h = index(x.##)
    var entry = table(h)
    while (entry ne null) {
      if (x == entry)
        return entry.asInstanceOf[T]
<<<<<<< HEAD
        
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      h = index(h + 1)
      entry = table(h)
    }
    table(h) = x
    used += 1
    if (used > (table.length >> 2)) growTable()
    x
  }

  def findEntry(x: T): T = {
    var h = index(x.##)
    var entry = table(h)
    while ((entry ne null) && x != entry) {
      h = index(h + 1)
      entry = table(h)
    }
    entry.asInstanceOf[T]
  }

  def addEntry(x: T) {
    var h = index(x.##)
    var entry = table(h)
    while (entry ne null) {
      if (x == entry) return
      h = index(h + 1)
      entry = table(h)
    }
    table(h) = x
    used += 1
    if (used > (table.length >> 2)) growTable()
  }
  def addEntries(xs: TraversableOnce[T]) {
    xs foreach addEntry
  }

  def iterator = new Iterator[T] {
    private var i = 0
    def hasNext: Boolean = {
      while (i < table.length && (table(i) eq null)) i += 1
      i < table.length
    }
<<<<<<< HEAD
    def next: T =
      if (hasNext) { i += 1; table(i - 1).asInstanceOf[T] }
      else null
  }
  
=======
    def next(): T =
      if (hasNext) { i += 1; table(i - 1).asInstanceOf[T] }
      else null
  }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  private def addOldEntry(x: T) {
    var h = index(x.##)
    var entry = table(h)
    while (entry ne null) {
      h = index(h + 1)
      entry = table(h)
    }
    table(h) = x
  }

  private def growTable() {
    val oldtable = table
    val growthFactor =
      if (table.length <= initialCapacity) 8
      else if (table.length <= (initialCapacity * 8)) 4
      else 2
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    table = new Array[AnyRef](table.length * growthFactor)
    var i = 0
    while (i < oldtable.length) {
      val entry = oldtable(i)
      if (entry ne null) addOldEntry(entry.asInstanceOf[T])
      i += 1
    }
  }
  override def toString() = "HashSet %s(%d / %d)".format(label, used, table.length)
}
