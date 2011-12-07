/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2007-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.swing

import event._
import javax.swing.{JList, JComponent, JComboBox, JTextField, ComboBoxModel, AbstractListModel, ListCellRenderer}
import java.awt.event.ActionListener

object ComboBox {
  /**
   * An editor for a combo box. Let's you edit the currently selected item.
<<<<<<< HEAD
   * It is highly recommended to use the BuiltInEditor class. For anything 
   * else, one cannot guarantee that it integrates nicely with the current
   * LookAndFeel. 
=======
   * It is highly recommended to use the BuiltInEditor class. For anything
   * else, one cannot guarantee that it integrates nicely with the current
   * LookAndFeel.
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   *
   * Publishes action events.
   */
  trait Editor[A] extends Publisher {
    lazy val comboBoxPeer: javax.swing.ComboBoxEditor = new javax.swing.ComboBoxEditor with Publisher {
<<<<<<< HEAD
      def addActionListener(l: ActionListener) { 
=======
      def addActionListener(l: ActionListener) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        this match {
          // TODO case w: Action.Trigger.Wrapper =>
          //  w.peer.addActionListener(l)
          case _ =>
            this.subscribe(new Reactions.Wrapper(l) ({
               case ActionEvent(c) => l.actionPerformed(new java.awt.event.ActionEvent(c.peer, 0, ""))
            }))
         }
      }
      def removeActionListener(l: ActionListener) {
        this match {
          // TODO case w: Action.Trigger.Wrapper =>
          //  w.peer.removeActionListener(l)
          case _ =>
            this.unsubscribe(new Reactions.Wrapper(l)({ case _ => }))
        }
      }
      def getEditorComponent: JComponent = Editor.this.component.peer
      def getItem(): AnyRef = item.asInstanceOf[AnyRef]
      def selectAll() { startEditing() }
      def setItem(a: Any) { item = a.asInstanceOf[A] }
    }
    def component: Component
    def item: A
    def item_=(a: A)
    def startEditing()
  }
<<<<<<< HEAD
  
  /**
   * Use this editor, if you want to reuse the builtin editor supplied by the current 
   * Look and Feel. This is restricted to a text field as the editor widget. The 
   * conversion from and to a string is done by the supplied functions.
   *
   * It's okay if string2A throws exceptions. They are caught by an input verifier. 
   */
  class BuiltInEditor[A](comboBox: ComboBox[A])(string2A: String => A, 
=======

  /**
   * Use this editor, if you want to reuse the builtin editor supplied by the current
   * Look and Feel. This is restricted to a text field as the editor widget. The
   * conversion from and to a string is done by the supplied functions.
   *
   * It's okay if string2A throws exceptions. They are caught by an input verifier.
   */
  class BuiltInEditor[A](comboBox: ComboBox[A])(string2A: String => A,
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                         a2String: A => String) extends ComboBox.Editor[A] {
    protected[swing] class DelegatedEditor(editor: javax.swing.ComboBoxEditor) extends javax.swing.ComboBoxEditor {
      var value: A = {
        val v = comboBox.peer.getSelectedItem
        try {
          v match {
            case s: String => string2A(s)
            case _ => v.asInstanceOf[A]
          }
        } catch {
<<<<<<< HEAD
          case _: Exception => 
            throw new IllegalArgumentException("ComboBox not initialized with a proper value, was '" + v + "'.")
        }
      }
      def addActionListener(l: ActionListener) { 
=======
          case _: Exception =>
            throw new IllegalArgumentException("ComboBox not initialized with a proper value, was '" + v + "'.")
        }
      }
      def addActionListener(l: ActionListener) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        editor.addActionListener(l)
      }
      def removeActionListener(l: ActionListener) {
       editor.removeActionListener(l)
      }
<<<<<<< HEAD
    
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      def getEditorComponent: JComponent = editor.getEditorComponent.asInstanceOf[JComponent]
      def selectAll() { editor.selectAll() }
      def getItem(): AnyRef = { verifier.verify(getEditorComponent); value.asInstanceOf[AnyRef] }
      def setItem(a: Any) { editor.setItem(a) }
<<<<<<< HEAD
      
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      val verifier = new javax.swing.InputVerifier {
        // TODO: should chain with potentially existing verifier in editor
        def verify(c: JComponent) = try {
          value = string2A(c.asInstanceOf[JTextField].getText)
          true
  	    }
  	    catch {
<<<<<<< HEAD
          case e: Exception => false 
        }
      }
      
=======
          case e: Exception => false
        }
      }

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      def textEditor = getEditorComponent.asInstanceOf[JTextField]
      textEditor.setInputVerifier(verifier)
      textEditor.addActionListener(Swing.ActionListener{ a =>
        getItem() // make sure our value is updated
        textEditor.setText(a2String(value))
      })
<<<<<<< HEAD
    }                  
    
    override lazy val comboBoxPeer: javax.swing.ComboBoxEditor = new DelegatedEditor(comboBox.peer.getEditor)
    
=======
    }

    override lazy val comboBoxPeer: javax.swing.ComboBoxEditor = new DelegatedEditor(comboBox.peer.getEditor)

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    def component = Component.wrap(comboBoxPeer.getEditorComponent.asInstanceOf[JComponent])
    def item: A = { comboBoxPeer.asInstanceOf[DelegatedEditor].value }
    def item_=(a: A) { comboBoxPeer.setItem(a2String(a)) }
    def startEditing() { comboBoxPeer.selectAll() }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  implicit def stringEditor(c: ComboBox[String]): Editor[String] = new BuiltInEditor(c)(s => s, s => s)
  implicit def intEditor(c: ComboBox[Int]): Editor[Int] = new BuiltInEditor(c)(s => s.toInt, s => s.toString)
  implicit def floatEditor(c: ComboBox[Float]): Editor[Float] = new BuiltInEditor(c)(s => s.toFloat, s => s.toString)
  implicit def doubleEditor(c: ComboBox[Double]): Editor[Double] = new BuiltInEditor(c)(s => s.toDouble, s => s.toString)
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def newConstantModel[A](items: Seq[A]): ComboBoxModel = {
    new AbstractListModel with ComboBoxModel {
      private var selected: A = if (items.isEmpty) null.asInstanceOf[A] else items(0)
      def getSelectedItem: AnyRef = selected.asInstanceOf[AnyRef]
<<<<<<< HEAD
      def setSelectedItem(a: Any) { 
=======
      def setSelectedItem(a: Any) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
        if ((selected != null && selected != a) ||
            selected == null && a != null) {
          selected = a.asInstanceOf[A]
          fireContentsChanged(this, -1, -1)
        }
<<<<<<< HEAD
      } 
=======
      }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      def getElementAt(n: Int) = items(n).asInstanceOf[AnyRef]
      def getSize = items.size
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  /*def newMutableModel[A, Self](items: Seq[A] with scala.collection.mutable.Publisher[scala.collection.mutable.Message[A], Self]): ComboBoxModel = {
    new AbstractListModel with ComboBoxModel {
      private var selected = items(0)
      def getSelectedItem: AnyRef = selected.asInstanceOf[AnyRef]
<<<<<<< HEAD
      def setSelectedItem(a: Any) { selected = a.asInstanceOf[A] } 
=======
      def setSelectedItem(a: Any) { selected = a.asInstanceOf[A] }
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
      def getElementAt(n: Int) = items(n).asInstanceOf[AnyRef]
      def getSize = items.size
    }
  }
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  def newConstantModel[A](items: Seq[A]): ComboBoxModel = items match {
    case items: Seq[A] with scala.collection.mutable.Publisher[scala.collection.mutable.Message[A], Self] => newMutableModel
    case _ => newConstantModel(items)
  }*/
}

/**
 * Let's the user make a selection from a list of predefined items. Visually,
<<<<<<< HEAD
 * this is implemented as a button-like component with a pull-down menu. 
 * 
=======
 * this is implemented as a button-like component with a pull-down menu.
 *
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
 * @see javax.swing.JComboBox
 */
class ComboBox[A](items: Seq[A]) extends Component with Publisher {
  override lazy val peer: JComboBox = new JComboBox(ComboBox.newConstantModel(items)) with SuperMixin
<<<<<<< HEAD
  
=======

>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
  object selection extends Publisher {
    def index: Int = peer.getSelectedIndex
    def index_=(n: Int) { peer.setSelectedIndex(n) }
    def item: A = peer.getSelectedItem.asInstanceOf[A]
    def item_=(a: A) { peer.setSelectedItem(a) }

    peer.addActionListener(Swing.ActionListener { e =>
      publish(event.SelectionChanged(ComboBox.this))
    })
  }
<<<<<<< HEAD
  
  /**
   * Sets the renderer for this combo box's items. Index -1 is 
   * passed to the renderer for the selected item (not in the pull-down menu).
   *
   * The underlying combo box renders all items in a <code>ListView</code> 
   * (both, in the pull-down menu as well as in the box itself), hence the 
   * <code>ListView.Renderer</code>.
   *
   * Note that the UI peer of a combo box usually changes the colors 
   * of the component to its own defaults _after_ the renderer has been 
=======

  /**
   * Sets the renderer for this combo box's items. Index -1 is
   * passed to the renderer for the selected item (not in the pull-down menu).
   *
   * The underlying combo box renders all items in a <code>ListView</code>
   * (both, in the pull-down menu as well as in the box itself), hence the
   * <code>ListView.Renderer</code>.
   *
   * Note that the UI peer of a combo box usually changes the colors
   * of the component to its own defaults _after_ the renderer has been
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * configured. That's Swing's principle of most suprise.
   */
  def renderer: ListView.Renderer[A] = ListView.Renderer.wrap(peer.getRenderer)
  def renderer_=(r: ListView.Renderer[A]) { peer.setRenderer(r.peer) }
<<<<<<< HEAD
  
  /* XXX: currently not safe to expose:
  def editor: ComboBox.Editor[A] = 
  def editor_=(r: ComboBox.Editor[A]) { peer.setEditor(r.comboBoxPeer) }
  */
  def editable: Boolean = peer.isEditable
  
  /**
   * Makes this combo box editable. In order to do so, this combo needs an 
=======

  /* XXX: currently not safe to expose:
  def editor: ComboBox.Editor[A] =
  def editor_=(r: ComboBox.Editor[A]) { peer.setEditor(r.comboBoxPeer) }
  */
  def editable: Boolean = peer.isEditable

  /**
   * Makes this combo box editable. In order to do so, this combo needs an
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
   * editor which is supplied by the implicit argument. For default
   * editors, see ComboBox companion object.
   */
  def makeEditable()(implicit editor: ComboBox[A] => ComboBox.Editor[A]) {
    peer.setEditable(true)
    peer.setEditor(editor(this).comboBoxPeer)
  }
<<<<<<< HEAD
  
  def prototypeDisplayValue: Option[A] = toOption[A](peer.getPrototypeDisplayValue)
  def prototypeDisplayValue_=(v: Option[A]) { 
=======

  def prototypeDisplayValue: Option[A] = toOption[A](peer.getPrototypeDisplayValue)
  def prototypeDisplayValue_=(v: Option[A]) {
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
    peer.setPrototypeDisplayValue(v map toAnyRef orNull)
  }
}
