package collision;

import ball.Ball;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-05
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param hitter -- the ball that make the hit.
     * @param beingHit -- the block that get the hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
