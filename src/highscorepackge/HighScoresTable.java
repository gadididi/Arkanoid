package highscorepackge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class HighScoresTable {

    private List<ScoreInfo> highScoreList;
    private int lenghOfList;
    private int howManyCell;

    /**
     * constructor   Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores..
     * @param size -- size of table.
     */
    public HighScoresTable(int size) {
        this.highScoreList = new ArrayList<ScoreInfo>(size);
        this.lenghOfList = size;
        this.howManyCell = 0;
    }
    /**
     * Add a high-score.
     * @param score -- score for table.
     */
    public void add(ScoreInfo score) {
        if (getRank(score.getScore()) <= this.lenghOfList) {
            highScoreList.add(score);
            this.howManyCell = this.howManyCell + 1;
        }
    }
    /**
     * Return table size.
     * @return size -- size of table.
     */
    public int size() {
        return this.highScoreList.size();
    }
    /**
     * Return the current high scores.The list is sorted such that the highest scores come first.
     * @return highScoreList -- list of table.
     */
    public List<ScoreInfo> getHighScores() {
        Collections.sort(this.highScoreList);
        return this.highScoreList;
    }
    /**
     * return the rank of the current score: where will it be on the list if added?
     * Rank 1 means the score will be highest on the list. Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not be added to the list.
     * @param score - score of the player.
     * @return place -- place in the table.
     */

    public int getRank(int score) {
        if (this.highScoreList.size() == 0) {
            return 1;
        }
        int place = 1;
        for (int i = 0; i < howManyCell; i++) {
            if (score > this.highScoreList.get(i).getScore()) {
                return place;
            }
            place += 1;
        }
        return place;
    }
    /**
     * Clears the table.
     */
    public void clear() {
        this.highScoreList.clear();
    }
    /**
     * Load table data from file.Current table data is cleared.
     * @throws IOException -- cant load the file.
     * @param filename -- the file.
     */
    public void load(File filename) throws IOException {
        this.highScoreList.clear();
        this.highScoreList = readFromFile(filename); //go to read the file
    }
    /**
     *  Save table data to the specified file.
     * @throws IOException -- cant save the file.
     * @param filename -- the file.
     */
    public void save(File filename) throws IOException {
        PrintWriter pw = null;
        try {
            FileWriter fw = new FileWriter(filename.getName());
            pw = new PrintWriter(fw);
            int indicator = 0;
            for (ScoreInfo scoreInfo : this.getHighScores()) {
                pw.printf("%s:%d\r\n", scoreInfo.getName(), scoreInfo.getScore());
                indicator++;
                if (indicator == this.lenghOfList) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
    /**
     *  Read a table from file and return it.
     * @param filename -- the file.
     * @return highScoresTable -- table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        List<ScoreInfo> scoreInfos = readFromFile(filename);
        if (scoreInfos == null || scoreInfos.size() == 0) { //dosnot exist or empty file.
            return new HighScoresTable(5); //create new
        }
        HighScoresTable highScoresTable = new HighScoresTable(5);
        for (ScoreInfo scoreInfo : scoreInfos) {
            highScoresTable.add(scoreInfo);
        }
        return highScoresTable;
    }

    /**
     *  Read a table from file and return list of scoreInfo.
     * @param filename -- the file.
     * @return scoreTable -- list of score info.
     */
    private static List<ScoreInfo> readFromFile(File filename) {
        List<ScoreInfo> scoreTable = new ArrayList<ScoreInfo>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String nameOfPlayer = line.substring(0, line.indexOf(":"));
                int scoreOfPlayer = Integer.valueOf(line.substring(line.indexOf(":") + 1));
                scoreTable.add(new ScoreInfo(nameOfPlayer, scoreOfPlayer));
            }

        } catch (FileNotFoundException ex) {
            // handle the FileNotFoundException
            System.err.println("Unable to find file: " + filename.getName());
        } catch (IOException e) {
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e2) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
        return scoreTable;
    }
}
