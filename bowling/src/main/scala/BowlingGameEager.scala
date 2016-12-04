
class BowlingGameEager() {

  var score = 0
  var frame = 1

  var frameShot = 0
  var frameScore = 0

  var multiplier = 0
  var next_multiplier= 0

  var countPins = 1

  def roll(pins : Int) = {
    frameShot += 1
    frameScore += pins

    score += pins * (countPins + multiplier)

    multiplier = next_multiplier
    next_multiplier = 0

    if (isStrike || isSpare) multiplier += 1
    if (isStrike)            next_multiplier += 1

    if (isFrameComplete) {
      if (isLastFrame) countPins = 0
      frame += 1
      frameShot = 0
      frameScore = 0
    }
  }

  def isStrike = frameShot == 1 && frameScore == 10 && frame < 11
  def isSpare = frameShot == 2 && frameScore == 10 && frame < 11
  def isFrameComplete = isStrike || frameShot == 2
  def isLastFrame = frame == 10

}
