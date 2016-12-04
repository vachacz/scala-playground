
object BowlingGameFramesDummyTest {

  def main(args: Array[String]): Unit = {

    // FRAME        1         2    3         4         5    6    7     8           9                10
    val rolls = 4 ~ 4 I  4 ~  4 I 10 I  2 ~  8 I  5 ~  2 I 10 I 10 I  10 I   1 ~   1 I  10 ~   6 ~   3
    val score = 4 ~ 8 I 12 ~ 16 I 26 I 30 ~ 46 I 56 ~ 58 I 68 I 88 I 118 I 121 ~ 123 I 133 ~ 139 ~ 142

    test(rolls.list, score.list)

    // FRAME      1    2    3    4     5     6     7     8     9                10
    val rolls2 = 10 I 10 I 10 I 10 I  10 I  10 I  10 I  10 I  10 I  10 ~  10 ~  10
    val score2 = 10 I 30 I 60 I 90 I 120 I 150 I 180 I 210 I 240 I 270 ~ 290 ~ 300

    test(rolls2.list, score2.list)

    // FRAME         1       2       3       4       5       6       7       8       9      10
    val rolls3 = 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0
    val score3 = 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0

    test(rolls3.list, score3.list)
  }

  def test(rolls: List[Int], score: List[Int]) = {
    val game = new BowlingGameFrames()
    (rolls zip score).foreach { e =>
      game.roll(e._1)
      assert(e._2 == game.score())
    }
  }

  implicit def intToListBuilder(e: Int) : ListBuilder = new ListBuilder(List(e))

  class ListBuilder(var list : List[Int] = List()) {
    def ~(e : Int) = new ListBuilder(list ::: List(e))
    def I(e : ListBuilder) = new ListBuilder(list ::: e.list)
  }
}