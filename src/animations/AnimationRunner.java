package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor AnimationRunner.
     * create a AnimationRunner by get the gui of the game.
     * @param gui  -- gui of the game.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }
    /**
     * run the game call to doOneFrame and show all the items in the gui.
     * @param animation  -- the animation we want to run now (pause screen , the game ..).
     */
    public void run(Animation animation) {
        this.framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);

            gui.show(d); //show all the game
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
