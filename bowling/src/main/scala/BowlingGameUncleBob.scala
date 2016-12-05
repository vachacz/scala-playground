
class BowlingGameUncleBob {

  val rolls = new Array[Int](21)
  var currentRoll = 0

  def roll(pins: Int) {
    rolls(currentRoll) = pins
    currentRoll += 1
  }

  def score = {
    var (score, frameIndex) = (0, 0)
    for (frame <- 0 to 9) {
      if (isStrike(frameIndex)) {
        score += 10 + strikeBonus(frameIndex)
        frameIndex += 1
      } else if (isSpare(frameIndex)) {
        score += 10 + spareBonus(frameIndex)
        frameIndex += 2
      } else {
        score += sumOfBallsInFrame(frameIndex)
        frameIndex += 2
      }
    }
    score
  }

  def isStrike(frameIndex: Int) = rolls(frameIndex) == 10
  def isSpare(frameIndex: Int) = rolls(frameIndex) + rolls(frameIndex + 1) == 10

  def sumOfBallsInFrame(frameIndex: Int) = rolls(frameIndex) + rolls(frameIndex + 1)

  def spareBonus(frameIndex: Int) = rolls(frameIndex + 2)
  def strikeBonus(frameIndex: Int) = rolls(frameIndex + 1) + rolls(frameIndex + 2)
}