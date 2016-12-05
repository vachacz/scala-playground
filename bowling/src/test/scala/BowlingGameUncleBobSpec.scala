import org.scalatest._

class BowlingGameUncleBobSpec extends FlatSpec with Matchers with BeforeAndAfterEach {

  var game : BowlingGameUncleBob = _

  override def beforeEach() {
    game = new BowlingGameUncleBob
  }

  it should "testGutterGame" in {
    rollMany(20, 0)
    game.score should equal(0)
  }

  it should "testAllOnes" in {
    rollMany(20, 1)
    game.score should equal(20)
  }

  it should "testOneSpare" in {
    rollSpare
    game.roll(3)
    rollMany(17, 0)
    game.score should equal(16)
  }

  it should "testOneStrike" in {
    rollStrike
    game.roll(3)
    game.roll(4)
    rollMany(16, 0)
    game.score should equal(24)
  }

  it should "testPerfectGame" in {
    rollMany(12, 10)
    game.score should equal(300)
  }

  it should "testSpareInLastFrame" in {
    rollMany(18, 0)
    rollSpare()
    game.roll(2)
    game.score should equal(12)
  }

  def rollStrike() = game.roll(10)
  def rollSpare() = {
    game.roll(5)
    game.roll(5)
  }

  def rollMany(n: Int, pins: Int) = for (i <- 0 until n) game.roll(pins)

}
