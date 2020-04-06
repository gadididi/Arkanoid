package animations;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 * @param <T> - generic variable.
 */
public class MenuAnimation<T> implements Menu<T> {
    private boolean stop;
    private List<Options<T>> selections;
    private T status;
    private KeyboardSensor key;

    /**
     * constructor MenuAnimation.
     * @param k  -- KeyboardSensor of the game.
     */
    public MenuAnimation(KeyboardSensor k) {
        this.stop = false;
        this.selections = new ArrayList<Options<T>>();
        this.key = k;
    }

    /**
     * draw the menu.
     * @param d  -- DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.gray.brighter());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black.darker());
        d.fillRectangle(20, 10, 400, 250);
        d.setColor(Color.yellow.darker().darker());
        d.drawText(50, 53, "Arkanoid", 38);
        // Draw the selections.
        d.setColor(Color.white);
        d.drawText(130, 120, "(s) Start Game", 38);
        d.drawText(130, 180, "(h) High Score", 38);
        d.drawText(130, 240, "(q) Quit", 38);
        for (int i = 0; i < selections.size(); i++) { //check if some key have pressed
            if (key.isPressed(selections.get(i).getKey())) {
                stop = true;
                status = selections.get(i).getStatus();
                break;
            }
        }
    }

    /**
     * return if it should stop.
     * @return this.stop -- should stop or not.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * The function add selection to the main menu.
     * @param k - the function gets the key which should press to exit the menu.
     * @param message     - the function gets the of the key.
     * @param returnVal - the function gets the return value.
     */
    @Override
    public void addSelection(String k, String message, T returnVal) {
        this.selections.add(new Options<>(k, message, returnVal));
    }

    /**
     * @return status - the function returns the current status.
     */
    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * The function add selection of sub-menu to the main menu.
     * @param k - the function gets the key which should press to exit the menu.
     * @param message     - the function gets the of the key.
     * @param subMenu - sub menu task.
     */
    @Override
    public void addSubMenu(String k, String message, Menu<T> subMenu) {

    }

}
