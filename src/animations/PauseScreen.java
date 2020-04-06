package animations;

import biuoop.DrawSurface;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * constructor of PauseScreen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * the screen that show when we pause the game.
     * @param d -- the DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(120, d.getHeight() / 2, "paused -- press space to continue", 40);
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
