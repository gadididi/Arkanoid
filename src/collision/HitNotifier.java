package collision;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-05
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl -- the object that implement the HitListener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl -- the object that implement the HitListener.
     */
    void removeHitListener(HitListener hl);
}

