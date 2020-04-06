package game;

import biuoop.DrawSurface;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-03-31 (the version of the package this class was first added to)
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d -- Surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
