package animations;

import java.awt.Color;

import biuoop.DrawSurface;
import tools.Counter;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class LoseScreen implements Animation {

    private boolean stop;
    private Counter scoreInTheEnd;

    /**
     * constructor LoseScreen.
     * @param score -- the score of the game right now.
     */
    public LoseScreen(Counter score) {
        this.stop = false;
        this.scoreInTheEnd = score;
    }

    /**
     * the screen that show when we loose the game.
     * @param d -- the DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, d.getHeight() / 2,
                "Game Over. Your score is " + Integer.toString(this.scoreInTheEnd.getValue()), 40);
        d.setColor(Color.black.darker());
        d.drawText(150, 400, "Press Space-Key to continue", 40);
        d.setColor(Color.blue.darker());
        d.drawText(153, 403, "Press Space-Key to continue", 40);
    }

    /**
     * indicator for changing the run Animation.
     * @return running -- the answer if stop or not.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
