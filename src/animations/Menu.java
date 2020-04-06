package animations;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 * @param <T> - generic variable.
 */
public interface Menu<T> extends Animation {

    /**
     * The function add selection to the main menu.
     * @param key - the function gets the key which should press to exit the menu.
     * @param message     - the function gets the of the key.
     * @param returnVal - the function gets the return value.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return T -- the function returns the current status.
     */
    T getStatus();

    /**
     * The function add selection of sub-menu to the main menu.
     * @param key - the function gets the key which should press to exit the menu.
     * @param message     - the function gets the of the key.
     * @param subMenu - menu task.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

}
