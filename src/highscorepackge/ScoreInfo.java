package highscorepackge;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class ScoreInfo implements Comparable<ScoreInfo> {
    private String name;
    private int score;

    /**
     * constructor ScoreInfo.
     * @param name -- name of the player.
     * @param score -- score for this player.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * name of ScoreInfo.
     * @return name -- name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * getter score player.
     * @return score -- score of the player.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * compare 2 scores Info.
     * @param another -- another player.
     * @return number -- negetive or positive depend by comparator.
     */
    @Override
    public int compareTo(ScoreInfo another) {
        int info = another.score;
        return (info - this.score);
    }
}
