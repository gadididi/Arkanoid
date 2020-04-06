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
public class SubMenuAnimation<T> implements Menu<T> {
    private T status;
    private boolean continueTheAnimation = false;
    private List<Options<T>> selections;
    private KeyboardSensor keyboardSensor;

    /**
     * Constructor.
     * @param keyboardSensor - the function gets the keyboard of the user.
     */
    public SubMenuAnimation(KeyboardSensor keyboardSensor) {
        this.selections = new ArrayList<>();
        this.keyboardSensor = keyboardSensor;
    }

    /**
     * draw the menu.
     * @param d -- DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // Creating the background color.
        d.setColor(Color.gray.brighter());
        d.fillRectangle(0, 0, 800, 600);
        // Draw the up text.
        d.setColor(Color.black.darker());
        d.fillRectangle(20, 10, 400, 250);
        d.setColor(Color.yellow);
        d.drawText(50, 50, "Arkanoid", 38);
        // Draw the selections.
        d.setColor(Color.green);
        for (int j = 0; j < selections.size(); j++) {
            d.drawText(130, 120 + 40 * j, "(" + selections.get(j).getKey() + ")  " + selections.get(j).getName(), 38);
        }
        for (int i = 0; i < selections.size(); i++) { // check if some task have pressed
            if (keyboardSensor.isPressed(selections.get(i).getKey())) {
                continueTheAnimation = true;
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
        boolean breakOrNot = continueTheAnimation;
        continueTheAnimation = false;
        return breakOrNot;
    }

    /**
     * The function add selection to the main menu.
     * @param key       - the function gets the key which should press to exit the menu.
     * @param message   - the function gets the of the key.
     * @param returnVal - the function gets the return value.
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        selections.add(new Options<>(key, message, returnVal));
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
     * @param key     - the function gets the key which should press to exit the menu.
     * @param message - the function gets the of the key.
     * @param subMenu - subMenu.
     */
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {

    }
}
