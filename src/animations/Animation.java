package animations;

import biuoop.DrawSurface;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public interface Animation {

    /**
     * the logic of the game , every loop call to playOneTurn and make one frame.
     * @param d -- DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * indicator for changing the run Animation.
     * @return indicator -- the indocator if stop or not.
     */
    boolean shouldStop();
}
