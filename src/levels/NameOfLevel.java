package levels;

import biuoop.DrawSurface;
import game.Sprite;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class NameOfLevel implements Sprite {
    private String name;
    /**
     * constructor NameOfLevel.
     * @param s -- string of this name.
     */
    public NameOfLevel(String s) {
        this.name = s;
    }
    /**
     * draw the name of the level.
     * @param d -- DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(480, 20, "Level Name: " + this.name, 20);
    }
    /**
     * nothing here.
     */
    @Override
    public void timePassed() {
    }

}
