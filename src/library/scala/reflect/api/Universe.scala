package scala.reflect
package api

abstract class Universe extends Symbols
<<<<<<< HEAD
                           with Types 
                           with Constants 
=======
                           with Types
                           with Constants
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                           with Scopes
                           with Names
                           with Trees
                           with Positions
<<<<<<< HEAD
=======
                           with TreePrinters
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                           with AnnotationInfos
                           with StandardDefinitions {
  type Position
  val NoPosition: Position

}
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
