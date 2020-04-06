package animations;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 * @param <T> - generic variable.
 */
public class Options<T> {

    private String key;
    private String name;
    private T status;

    /**
     * constructor MenuAnimation.
     * @param k  -- key.
     * @param n  -- name.
     * @param status  -- the status of this option.
     */
    public Options(String k, String n, T status) {
        this.key = k;
        this.name = n;
        this.status = status;
    }

    /**
     * @return name- the function returns the current name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return key- the function returns the current key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return status - the function returns the current status.
     */
    public T getStatus() {
        return this.status;
    }
}
