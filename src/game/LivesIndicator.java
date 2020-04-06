package game;

import biuoop.DrawSurface;
import graphics.Rectangle;
import tools.Counter;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-05
 */
public class LivesIndicator implements Sprite {
    private Counter currentLives;
    private Rectangle show;

    /**
     * constructor LivesIndicator.
     * a LivesIndicator is in charge of showing the score in the game.
     * @param r -- the Rectangle that we draw on it.
     * @param liveCounter -- how many live there are in the game
     */
    public LivesIndicator(Counter liveCounter, Rectangle r) {
        this.currentLives = liveCounter;
        this.show = r;
    }
    /**
     * draw the lives number.
     * @param d -- the draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText((int) this.show.getUpperLeft().getX() + 100, (int) this.show.getUpperLeft().getY() + 20,
                "Lives: " + Integer.toString(this.currentLives.getValue()), 20);
    }
    /**
     * nothing here.
     */
    @Override
    public void timePassed() {
    }
}

