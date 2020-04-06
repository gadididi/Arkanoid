package ball;

import collision.Block;
import collision.HitListener;
import game.GameLevel;
import tools.Counter;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-05
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    /**
     * constructor ball remover.
     * @param game -- object Game of our game.
     * @param removedBalls   -- how many ball there are now.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }
    /**
     * check if the ball arrive  to the dead zone so remove it.
     * @param beingHit -- the block that get the hit by the ball.
     * @param hitter   -- the ball that hit in this event.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (hitter.getY() > 610) {
            this.remainingBalls.decrease(1);
            hitter.removeFrom(this.game);
        }
    }
    /**
     * getter of the number of the balls right now.
     * @return remainingBalls-- how many balls stay.
     */
    public Counter getCountOfBalls() {
        return this.remainingBalls;
    }
}

