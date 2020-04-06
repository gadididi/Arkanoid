package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String theKey;
    private Animation runAnimation;
    private boolean keyHasBeenPressed = false;
    private boolean isAlreadyPressed = true;

    /**
     * constructor KeyPressStoppableAnimation.
     * @param sensor    -- the KeyboardSensor of this game.
     * @param key       -- String key, here is the PRESS- SPACE-KEY.
     * @param animation -- animation that run now (pause ,win or lose screen).
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.runAnimation = animation;
        this.theKey = key;
    }

    /**
     * the logic of the KeyPressStoppableAnimation , every loop is called by playOneTurn
     * and make one frame of screen that wait to PRESS- KEY for stop itself.
     * @param d -- the DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        runAnimation.doOneFrame(d);
        if (keyboardSensor.isPressed(theKey) && !isAlreadyPressed) {
            keyHasBeenPressed = true;
            isAlreadyPressed = false;
        }
        if (!keyboardSensor.isPressed(theKey)) {
            isAlreadyPressed = false;
            keyHasBeenPressed = false;
        }
    }

    /**
     * indicator for changing the run Animation.
     * @return running -- the answer if stop or not.
     */
    @Override
    public boolean shouldStop() {
        if (keyHasBeenPressed) {
            // For next generation.
            keyHasBeenPressed = false;
            return true;
        }
        return runAnimation.shouldStop();
    }
}
