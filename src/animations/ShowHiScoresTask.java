package animations;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class ShowHiScoresTask implements Task<Void> {

    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * constructor ShowHiScoresTask.
     * @param runner      -- AnimationRunner.
     * @param highScoresAnimation   -- Animation of level sets.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * The function go to game flow and run the levels of specific this level set.
     * @return null -- null.
     */
    @Override
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }

}
