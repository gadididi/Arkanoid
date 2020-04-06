package levelfromfile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import animations.Options;
import animations.RunGameTask;
import animations.Task;
import game.GameFlow;
import levels.LevelInformation;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class LevelSetFileReader {
    private GameFlow gameFlow;

    /**
     * Constructor.
     * @param gameFlow - the function gets member of game flow.
     */
    public LevelSetFileReader(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    /**
     * The function build tasks for the sub menu.
     * @param bufferedReader - buffer reader for the level set.
     * @return options- returns list of tasks.
     */
    public List<Options<Task<Void>>> fromReader(BufferedReader bufferedReader) {
        List<Options<Task<Void>>> options = new ArrayList<>();
        LineNumberReader lineNumberReader = null;
        try {
            lineNumberReader = new LineNumberReader(bufferedReader);
        } catch (Exception e) {
            System.out.println("damn");
        }
        String str;
        String option = "", description = "";
        List<LevelInformation> levels;
        try {
            while ((str = lineNumberReader.readLine()) != null) { //read all the file
                if (lineNumberReader.getLineNumber() % 2 != 0) { // the key of the set level
                    option = str.split(":")[0];
                    description = str.split(":")[1];
                } else { // the path to the key we have read before
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(str);
                    InputStreamReader r = new InputStreamReader(is);
                    BufferedReader bb = new BufferedReader(r);
                    LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
                    levels = levelSpecificationReader.fromReader(bb);
                    options.add(new Options<Task<Void>>(option, description, new RunGameTask(gameFlow, levels)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return options;
    }

}
