package playground

import cats.Eval

object Playground {

  private val meaningOfLife = Eval.later {
    println("Learning cats...")
    42
  }

  def main(args: Array[String]): Unit = {
    println(meaningOfLife.value)
  }
}
