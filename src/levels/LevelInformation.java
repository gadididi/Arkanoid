package levels;

import java.util.List;
import game.Sprite;
import ball.Velocity;
import collision.Block;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public interface LevelInformation {
    /**
     * getter of number of the balls in this level.
     * @return numberOfBalls -- the number of the balls in this level.
     */
    int numberOfBalls();
    /**
     * getter list of the velocity of the balls.
     * @return initialBallVelocities -- the list of the velocity of the balls.
     */
    List<Velocity> initialBallVelocities();
    /**
     * getter speed paddle.
     * @return paddleSpeed -- speed of the paddle.
     */
    int paddleSpeed();
    /**
     * getter paddleWidth paddle.
     * @return paddleWidth -- Width of the paddle.
     */
    int paddleWidth();
    /**
     * getter levelName.
     * @return levelName -- levelName.
     */
    String levelName();
    /**
     * getter getBackground.
     * @return getBackground -- getBackground of this level.
     */
    Sprite getBackground();
    /**
     * getter list of the blocks.
     * @return blocks -- list blocks.
     */
    List<Block> blocks();
    /**
     * getter numberOfBlocksToRemove.
     * @return numberOfBlocksToRemove -- nuber blocks to remove in this game.
     */
    int numberOfBlocksToRemove();
    /**
     * getter name level as sprite.
     * @return name -- name level as sprite.
     */
    Sprite getName();
}
