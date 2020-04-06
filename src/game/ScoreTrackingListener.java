package game;

import ball.Ball;
import collision.Block;
import collision.HitListener;
import tools.Counter;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-05
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor ScoreIndicator.
     * @param scoreCounter -- the score in the start of the level.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * constructor ScoreIndicator.
     * @param beingHit -- block that get the hit.
     * @param hitter -- the ball that hit that block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (Integer.parseInt(beingHit.getHitPoints()) > 1) {
            this.currentScore.increase(5);
        }
        if (Integer.parseInt(beingHit.getHitPoints()) == 1) {
            this.currentScore.increase(15);
        }
    }
    /**
     * getter the score right now.
     * @return currentScore -- return the score right now.
     */
    public Counter getScore() {
        return this.currentScore;
    }
}

