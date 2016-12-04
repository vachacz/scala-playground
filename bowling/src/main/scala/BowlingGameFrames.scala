
class BowlingGameFrames {

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

    def roll(pins : Int) = {
      val firstFrameRoll = first.isEmpty
      if (firstFrameRoll) first = Some(pins)
      else second = Some(pins)

      if (previous.isDefined) {
        val prem v = previous.get
        if (prev.isStrike) {
          prev.bonus += pins
          if (firstFrameRoll && prev.previous.isDefined && prev.previous.get.isStrike) prev.previous.get.bonus += pins
        }
        else if (prev.isSpare && firstFrameRoll) prev.bonus += pins
      }
    }

    def score = bonus + List(first, second).filter(_.isDefined).map(_.get).sum

    def isSpare = second.isDefined && (first.get + second.get == 10)
    def isStrike = first.isDefined && first.get == 10
    def isComplete = isStrike || second.isDefined
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