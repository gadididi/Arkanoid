package animations;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class ShowSubMenu implements Task<Void> {

    private AnimationRunner runner;
    private Menu<Task<Void>> animation;

    /**
     * Constructor.
     *
     * @param runner    - the function gets an animation runner.
     * @param animation - the function gets an animation to run.
     */
    public ShowSubMenu(AnimationRunner runner, Menu<Task<Void>> animation) {
        this.runner = runner;
        this.animation = animation;
    }

    /**
     * The function run the sub menu.
     *
     * @return - the function returns none.
     */
    @Override
    public Void run() {
            this.runner.run(this.animation);
            Task<Void> task = this.animation.getStatus();
            task.run();
            return null;
    }
}
