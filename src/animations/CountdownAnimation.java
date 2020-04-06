package animations;

import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import collision.Frame;
import game.SpriteCollection;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class CountdownAnimation implements Animation {
    private int countingFrom;
    private SpriteCollection screenGame;
    private Sleeper sleeper;
    private boolean running;
    private double framesPerSecond;

    /**
     * constructor CountdownAnimation.
     * @param numOfSeconds -- the number of seconds that showed this screen.
     * @param countFrom    -- count from this number.
     * @param gameScreen   -- list of all the items in the game.
     * @param f            -- Frame object (for the limit of the screen).
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Frame f) {
        this.countingFrom = countFrom;
        this.screenGame = gameScreen;
        this.running = false;
        this.sleeper = new Sleeper();
        this.framesPerSecond = countFrom / numOfSeconds;
    }

    /**
     * the screen of the starting game.
     * 3 2 1 GO!. by new sleeprer that builded in the constructor.
     * @param d -- the DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        screenGame.drawAllOn(d);
        d.setColor(Color.YELLOW.darker());
        if (this.countingFrom < 0) {
            this.running = true; // for stop run this Animation.
        }
        if (this.countingFrom < 3) {
            int millisecondsPerFrame = (int) (1000 / this.framesPerSecond);
            long startTime = System.currentTimeMillis(); // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (this.countingFrom == 0) {
            d.drawText(370, d.getHeight() / 2, "Go!", 100);
            this.countingFrom = this.countingFrom - 1;
        }
        if (this.countingFrom >= 1) {
            d.drawText(370, d.getHeight() / 2, Integer.toString(this.countingFrom), 100);
            this.countingFrom = this.countingFrom - 1;
        }
    }

    /**
     * indicator for changing the run Animation.
     * @return running -- the answer if stop or not.
     */
    @Override
    public boolean shouldStop() {
        return this.running;
    }
}
