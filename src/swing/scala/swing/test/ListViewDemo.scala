package scala.swing
package test

object ListViewDemo extends SimpleSwingApplication {
  def top = new MainFrame {
    case class City(name: String, country: String, population: Int, capital: Boolean)
    val items = List(City("Lausanne", "Switzerland", 129273, false),
<<<<<<< HEAD
                              City("Paris", "France", 2203817, true), 
=======
                              City("Paris", "France", 2203817, true),
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
                              City("New York", "USA", 8363710 , false),
                              City("Berlin", "Germany", 3416300, true),
                              City("Tokio", "Japan", 12787981, true))
    import ListView._
    contents = new FlowPanel(new ScrollPane(new ListView(items) {
      renderer = Renderer(_.name)
    }))
                             //new ScrollPane(new Table(items)))
  }
}
