import org.scalatest._

class BowlingGameTailRecursionSpec extends FlatSpec with Matchers with BeforeAndAfterEach {

  var game : BowlingGameTailRecursion = _

  override def beforeEach() {
    game = new BowlingGameTailRecursion
  }

  it should "score zero before the game starts" in {
    game.score should equal(0)
  }

  it should "roll 3 once" in {
    game.roll(3)
    game.score should equal(3)
  }

  it should "roll twice" in {
    game.roll(3)
    game.roll(3)
    game.score should equal(6)
  }

  it should "roll in the second frame" in {
    game.roll(3)
    game.roll(3)
    game.roll(3)
    game.score should equal(9)
  }

  it should "roll strike and calculate bonus from first shot after strike" in {
    game.roll(10)
    game.roll(3)
    game.score should equal(16)
  }

  it should "roll strike and calculate bonus from first and second shot after strike" in {
    game.roll(10)
    game.roll(3)
    game.roll(3)
    game.score should equal(22)
  }

  it should "not calculate bonus for the thrid shot after strike" in {
    game.roll(10)
    game.roll(3)
    game.roll(3)
    game.roll(1)
    game.score should equal(23)
  }

  it should "roll spare and calculate bonus from first shot after spare" in {
    game.roll(5)
    game.roll(5)
    game.roll(3)
    game.score should equal(16)
  }

  it should "not calculate bonus for the second shot after spare" in {
    game.roll(5)
    game.roll(5)
    game.roll(3)
    game.roll(2)
    game.score should equal(18)
  }

  it should "roll strike twice in a row and non strike after" in {
    game.roll(10)
    game.roll(10)
    game.roll(4)
    game.score should equal(24+14+4)
  }

  it should "roll 10 x strikes" in {
    for (i <- 1 to 10) game.roll(10)
    game.score should equal(270)
  }

  it should "roll 10 x strikes and twice 10 as a bonus" in {
    for (i <- 1 to 10) game.roll(10)
    game.roll(10)
    game.roll(10)
    game.score should equal(300)
  }

  it should "roll spare in the last round" in {
    for (i <- 1 to 9) game.roll(10)
    game.roll(6)
    game.roll(4)
    game.roll(1)
    game.score should equal(267)
  }

  it should "roll normal last frame" in {
    for (i <- 1 to 9) game.roll(10)
    game.roll(6)
    game.roll(3)
    game.score should equal(264)
  }

}
