
package game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import animations.AnimationRunner;
import animations.ExitGame;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import animations.Menu;
import animations.MenuAnimation;
import animations.Options;
import animations.ShowHiScoresTask;
import animations.ShowSubMenu;
import animations.SubMenuAnimation;
import animations.Task;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levelfromfile.LevelSetFileReader;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class Ass7Game {

    /**
     * main. main of the game intitializing and run.
     * @param args -- can be the level we want to run.
     */
    public static void main(String[] args) {
        GUI g = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(g);
        KeyboardSensor keyboard = g.getKeyboardSensor();
        while (true) {
            GameFlow flow = new GameFlow(runner, keyboard, g);
            InputStream is;
            if (args.length == 1) { //if there are path by args run the path
                is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
            } else {
                is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
            }
            InputStreamReader r = new InputStreamReader(is);
            BufferedReader b = null;
            b = new BufferedReader(r);
            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard);
            Menu<Task<Void>> subMenu = new SubMenuAnimation<>(keyboard);
            List<Options<Task<Void>>> options = null;
            try {
                LevelSetFileReader levelSetFileReader = new LevelSetFileReader(flow);
                options = levelSetFileReader.fromReader(b);
                for (int i = 0; i < options.size(); i++) { //scan all the diffrents level set from the file
                    subMenu.addSelection(options.get(i).getKey(), options.get(i).getName(),
                            options.get(i).getStatus());
                }
            } catch (Exception e) {
                System.out.print("");
            }
            KeyPressStoppableAnimation showing = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                    new HighScoresAnimation(flow.getTable()));
            //add the task for the main menu
            menu.addSelection("h", "High scores", new ShowHiScoresTask(runner, showing));
            menu.addSelection("q", "Quit", new ExitGame());
            menu.addSelection("s", "play", new ShowSubMenu(runner, subMenu));
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run(); //run the chosen task
        }
    }
}
