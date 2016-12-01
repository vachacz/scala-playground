
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
          if (firstRoll && previous.isDefined && prev.previous.get.isStrike) prev.previous.get.bonus += pins
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
      println(game2.score())
      //assert(e._2 == game2.score())
    }
  }

  def main(args: Array[String]): Unit = {

    //               1         2    3         4         5    6    7     8           9                10
    val rolls = (4 ~ 4 |  4 ~  4 | 10 |  2 ~  8 |  5 ~  2 | 10 | 10 |  10 |   1 ~   1 |  10 ~   6 ~   3).list
    val score = (4 ~ 8 | 12 ~ 16 | 26 | 30 ~ 46 | 56 ~ 58 | 68 | 88 | 118 | 121 ~ 123 | 133 ~ 139 ~ 142).list

    test(rolls, score)

    //             1    2    3    4     5     6     7     8     9    10
    val rolls2 = (10 | 10 | 10 | 10 |  10 |  10 |  10 |  10 |  10 |  10).list
    val score2 = (10 | 30 | 60 | 90 | 120 | 150 | 180 | 210 | 240 | 270).list

    println(rolls2)
    test(rolls2, score2)
  }

  implicit def intToListBuilder(e: Int) : ListBuilder = new ListBuilder(List(e))

  class ListBuilder(var list : List[Int] = List()) {
    def ~(e : Int) = new ListBuilder(list ::: List(e))
    def |(e : Int) = this.~(e)
    def ~(e : ListBuilder) = new ListBuilder(list ::: e.list)
    def |(e : ListBuilder) = this.~(e)
  }
}