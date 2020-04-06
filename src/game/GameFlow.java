package game;

import java.io.File;
import java.io.IOException;
import java.util.List;
import animations.AnimationRunner;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import animations.LoseScreen;
import animations.WinScreen;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import highscorepackge.HighScoresTable;
import highscorepackge.ScoreInfo;
import levels.LevelInformation;
import tools.Counter;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class GameFlow {
    private KeyboardSensor sensor;
    private AnimationRunner runner;
    private GUI gui;
    private Counter lives = new Counter(7);
    private Counter score = new Counter(0);
    private HighScoresTable table;
    private File file;

    /**
     * constructor GameFlow.
     * @param ar -- the AnimationRunner we run this game.
     * @param ks -- KeyboardSensor of this game.
     * @param g  -- the gui of this game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI g) {
        this.gui = g;
        this.sensor = ks;
        this.runner = ar;
        this.file = new File("HighScore.txt");
        this.table = build(this.file);
    }

    /**
     * build high score for GameFlow.
     * @param f -- file of data highScoresTable.
     * @return highScoresTable  -- highScoresTable of this game.
     */
    private HighScoresTable build(File f) {
        // Creating our HighScore table.
        HighScoresTable highScoresTable = new HighScoresTable(5);
        highScoresTable = HighScoresTable.loadFromFile(f);
        return highScoresTable;
    }

    /**
     * flow the level by loop and check in the way if there is lives or we win. call
     * to the method playOneTurn for run the animation
     * @param levels -- list of the level we run.
     */
    public void runLevels(List<LevelInformation> levels) {
        int countHowManyLevels = 0;
        for (LevelInformation levelInfo : levels) {
            countHowManyLevels++;
            GameLevel level = new GameLevel(levelInfo, this.sensor, this.runner, this.score, this.lives);
            level.initialize();
            while (!level.shouldStop()) {
                level.playOneTurn();
            }
            // lose
            if (this.lives.getValue() == 0) {
                KeyPressStoppableAnimation loose = new KeyPressStoppableAnimation(this.sensor, KeyboardSensor.SPACE_KEY,
                        new LoseScreen(this.score));
                this.runner.run(loose);
                highScoreShowing();
                break;
            }
            // winning
            if (countHowManyLevels == levels.size() && levelInfo.blocks().size() == 0) {
                KeyPressStoppableAnimation win = new KeyPressStoppableAnimation(this.sensor, KeyboardSensor.SPACE_KEY,
                        new WinScreen(this.score));
                this.runner.run(win);
                highScoreShowing();
                break;
            }
        }
    }

    /**
     * show the high score table from the file.
     */
    private void highScoreShowing() {
        if (this.table.getRank(this.score.getValue()) <= 5) {
            DialogManager dialog = this.gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "Anonymous");
            table.add(new ScoreInfo(name, this.score.getValue()));
            try {
                table.save(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        KeyPressStoppableAnimation highScore = new KeyPressStoppableAnimation(this.sensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(this.table));
        this.runner.run(highScore); //run the animation of high score until get spacse key.
    }

    /**
     * get high score.
     * @return table -- the high score table.
     */
    public HighScoresTable getTable() {
        return this.table;
    }
}
