package animations;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 * @param <T> - generic variable.
 */
public interface Task<T> {

    /**
     * The function go to game flow and run the levels he got.
     * @return T -- null.
     */
    T run();
}
