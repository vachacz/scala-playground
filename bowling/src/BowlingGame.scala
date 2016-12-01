
class BowlingGame {

  class FirstFrame extends Frame(None)
  class LastFrame(previous : Option[Frame]) extends Frame(previous) {
    var thirdRolled = false
    override def isComplete = if (isStrike || isSpare) thirdRolled else second.isDefined
    override def roll(pins : Int) = if (second.isEmpty) super.roll(pins) else bonus += pins
  }
  class Frame(val previous : Option[Frame]) {
    var bonus = 0

    var first : Option[Int] = None
    var second : Option[Int] = None

    def isSpare = second.isDefined && (first.get + second.get == 10)
    def isStrike = first.isDefined && first.get == 10
    def isComplete = isStrike || second.isDefined

    def roll(pins : Int) = {
      val firstRoll = first.isEmpty
      if (firstRoll) first = Some(pins)
      else second = Some(pins)

      if (previous.isDefined) {
        val prev = previous.get
        if (prev.isStrike) {
          prev.bonus += pins
          if (firstRoll && prev.previous.isDefined && prev.previous.get.isStrike) prev.previous.get.bonus += pins
        }
        else if (prev.isSpare && firstRoll) prev.bonus += pins
      }
    }

    def score = bonus + List(first, second).filter(_.isDefined).map(_.get).sum
  }

  var frames : List[Frame] = List()

  def roll(pins : Int) = {
    if (frames.isEmpty || currentFrame.isComplete) addFrame
    currentFrame.roll(pins)
  }

  def score() = frames.map(_.score).sum

  private def currentFrame = frames.head
  private def addFrame = {
    frames = (frames.size match {
      case s if s == 0 => new FirstFrame()
      case s if s < 9 => new Frame(Some(currentFrame))
      case _ => new LastFrame(Some(currentFrame))
    }) :: frames
  }
}

object BowlingGameTest {

  def test(rolls: List[Int], score: List[Int]) = {
    val game2 = new BowlingGame()
    (rolls zip score).foreach { e =>
      game2.roll(e._1)
      assert(e._2 == game2.score())
    }
  }

  def main(args: Array[String]): Unit = {

    //              1         2    3         4         5    6    7     8           9                10
    val rolls = 4 ~ 4 I  4 ~  4 I 10 I  2 ~  8 I  5 ~  2 I 10 I 10 I  10 I   1 ~   1 I  10 ~   6 ~   3
    val score = 4 ~ 8 I 12 ~ 16 I 26 I 30 ~ 46 I 56 ~ 58 I 68 I 88 I 118 I 121 ~ 123 I 133 ~ 139 ~ 142

    test(rolls.list, score.list)

    //            1    2    3    4     5     6     7     8     9                10
    val rolls2 = 10 I 10 I 10 I 10 I  10 I  10 I  10 I  10 I  10 I  10 ~  10 ~  10
    val score2 = 10 I 30 I 60 I 90 I 120 I 150 I 180 I 210 I 240 I 270 ~ 290 ~ 300

    test(rolls2.list, score2.list)

    //              1       2       3       4       5       6       7       8       9      10
    val rolls3 = 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0
    val score3 = 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0 I 0 ~ 0

    test(rolls3.list, score3.list)
  }

  implicit def intToListBuilder(e: Int) : ListBuilder = new ListBuilder(List(e))

  def game(e: Int) = new ListBuilder(List(e))

  class ListBuilder(var list : List[Int] = List()) {
    def ~(e : Int) = new ListBuilder(list ::: List(e))
    def I(e : Int) = this.~(e)
    def ~(e : ListBuilder) = new ListBuilder(list ::: e.list)
    def I(e : ListBuilder) = this.~(e)
  }
}