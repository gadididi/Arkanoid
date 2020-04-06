package game;

import biuoop.DrawSurface;
import graphics.Rectangle;
import tools.Counter;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-05
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;
    private Rectangle show;

    /**
     * constructor ScoreIndicator.
     * @param r -- the Rectangle that we draw on it.
     * @param scoreCounter -- how many score there are in the game.
     */
    public ScoreIndicator(Counter scoreCounter, Rectangle r) {
        this.currentScore = scoreCounter;
        this.show = r;
    }

    /**
     * draw the score number.
     * @param d -- the drawsurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText((int) this.show.getUpperLeft().getX() + 300, (int) this.show.getUpperLeft().getY() + 20,
                "Score: " + Integer.toString(this.currentScore.getValue()), 20);

    }
    /**
     * nothing here.
     */
    @Override
    public void timePassed() {
    }
}
