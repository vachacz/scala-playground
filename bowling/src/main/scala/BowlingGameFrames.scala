
class BowlingGameFrames {

  class FirstFrame extends Frame(None)
  class LastFrame(previous : Option[Frame]) extends Frame(previous) {
    override def isComplete = if (isStrike || isSpare) rolls.size == 3 else rolls.size == 2
    override def roll(pins : Int) = if (rolls.size < 2) super.roll(pins) else bonus += pins
  }
  class Frame(val previous : Option[Frame]) {
    var bonus = 0
    var rolls = List[Int]()

    def roll(pins : Int) = {
      rolls = rolls :+ pins

      if (previous.isDefined) {
        val prev = previous.get
        if (prev.isStrike) {
          prev.bonus += pins
          if (rolls.size == 1 && prev.previous.isDefined && prev.previous.get.isStrike) prev.previous.get.bonus += pins
        }
        else if (prev.isSpare && rolls.size == 1) prev.bonus += pins
      }
    }

    def score = bonus + rolls.sum
    def isSpare = rolls.size == 2 && rolls.sum == 10
    def isStrike = rolls.size == 1 && rolls.sum == 10
    def isComplete = isStrike || rolls.size == 2
  }

  var frames : List[Frame] = List(new FirstFrame())

  def roll(pins : Int) = {
    if (currentFrame.isComplete) addFrame
    currentFrame.roll(pins)
  }

  def score() = frames.map(_.score).sum

  private def currentFrame = frames.head
  private def addFrame = {
    frames = (frames.size match {
      case s if s < 9 => new Frame(Some(currentFrame))
      case _ => new LastFrame(Some(currentFrame))
    }) :: frames
  }
}