package animations;

import java.awt.Color;
import biuoop.DrawSurface;
import highscorepackge.HighScoresTable;
import highscorepackge.ScoreInfo;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class HighScoresAnimation implements Animation {
    private boolean stop;
    private HighScoresTable table;

    /**
     * constructor HighScoresAnimation.
     * @param scores  -- HighScoresTable of the game.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.stop = false;
        this.table = scores;
    }

    /**
     * draw the table.
     * @param d  -- DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.YELLOW.darker());
        d.drawText(75, 30, "High Scores", 30);
        d.setColor(Color.green.darker());
        d.drawText(140, 80, "Player Name", 23);
        d.drawText(500, 80, "Player Score", 23);
        d.setColor(Color.blue.darker());
        int i = 0;
        int indicator = 0;
        for (ScoreInfo scoreInfo : this.table.getHighScores()) { //for draw only 5 first people
            d.drawText(140, 115 + i, scoreInfo.getName(), 20);
            d.drawText(500, 115 + i, Integer.toString(scoreInfo.getScore()), 20);
            i = i + 20;
            indicator++;
            if (indicator == 5) {
                break;
            }
        }
        d.setColor(Color.black.darker());
        d.drawText(150, 400, "Press Space-Key to continue", 40);
        d.setColor(Color.blue.darker());
        d.drawText(153, 403, "Press Space-Key to continue", 40);
    }

    /**
     * return if it should stop.
     * @return this.stop -- should stop or not.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
