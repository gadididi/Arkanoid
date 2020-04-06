package animations;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class ExitGame implements Task<Void> {

    /**
     * constructor exit empty.
     */
    public ExitGame() {
    }

    /**
     * exit gui.
     * @return null - nothing.
     */
    @Override
    public Void run() {
        System.exit(0);
        return null;
    }

}
