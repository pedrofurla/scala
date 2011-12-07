class Bug {
  val z = (
<<<<<<< HEAD
      for {
        x = 3
        y <- 0 to 100
      } yield y
    ).toArray
=======
    for {
      x = 3
      y <- 0 to 100
    } yield y
  ).toArray
>>>>>>> 426c65030df3df0c3e038931b64199fc4e83c1a0
}
