package animations;

import java.util.List;
import game.GameFlow;
import levels.LevelInformation;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class RunGameTask implements Task<Void> {

    private GameFlow gameFlow;
    private List<LevelInformation> levels;

    /**
     * Constructor.
     * @param gameFlow - the function gets a member of game flow.
     * @param levels   - the function gets a list of levels to run.
     */
    public RunGameTask(GameFlow gameFlow, List<LevelInformation> levels) {
        this.gameFlow = gameFlow;
        this.levels = levels;
    }

    /**
     * The function go to game flow and run the levels he got.
     * @return null -- null.
     */
    @Override
    public Void run() {
        gameFlow.runLevels(levels);
        return null;
    }

}
