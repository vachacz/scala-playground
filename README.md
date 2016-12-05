# scala-playground

I use this repository to learn Scala programming language. As a part of the learning process i've implemented (or adapted) a few implementations of the bowling game kata.

Classes
- **BowlingGameFrames**
 - First naive bowling game kata. It uses heavy object oriented apprach. Game is divided into Frames. Rolls are passed to current Frame. Each Frame knows it's predecessor in order to pass bonus points back in case of spare and strike. The code is quite ugly and difficult to optimise.
 
- **BowlingGameEager**
 - Second attempt to bowling game kata. It uses eager approach. Rolls are not remembered anywhere. Instead score is computed on the fly. To make it possible presence of strike or spare bonus need to be remembered for future use. This approach is quite conciese but handling of last frame is a bit hacky.
 
- **BowlingGameFunctional**
 - Third attepmt. Noone would implementent bowling game this way, but it's quite instructive. The idea here is, to store anonymous functions which compute bonuses whenever one execute score. It's quite elegant, but again handling ot the last frame is painful.
 
- **BowlingGameUncleBob**
 - This is a port of UncleBob's kata written in Java. It's really graeat piece of code. Short and cute last frame. With conciese Scala syntax it could be opimised a bit but i left original implementation as is.
 
- **BowlingGameTailRecursion**
 - Pure art of programming. Solution is based on [this](https://qualityswdev.com/2011/03/14/the-bowling-game-kata-in-scala/) article by [@ManuelKublbock](https://twitter.com/ManuelKublbock). I've added a few optimisations e.g. removed nested match by using tuple matching.
