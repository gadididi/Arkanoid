package levels;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import ball.Velocity;
import biuoop.DrawSurface;
import collision.Block;
import game.Sprite;
import graphics.Point;
import graphics.Rectangle;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class Level3 implements LevelInformation {
    // Members of the class.
    private int numberOfBalls;
    // List of velocity for each ball.
    private List<Velocity> initialBallVelocities;
    // collidables.Paddle properties.
    private int paddleSpeed;
    private int paddleWidth;
    // The name of the level.
    private String levelName;
    // Level gui.
    private Sprite background;
    // List of all blocks in the game.
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private Sprite name;

    /**
     * constructor Level3 . this constructor build all the level staff , like number
     * of balls etc..
     * @param d -- the DrawSurface of the game.
     */
    public Level3(DrawSurface d) {
        this.numberOfBalls = 2;
        this.initialBallVelocities = new ArrayList<>();
        for (int i = 0; i < numberOfBalls; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(330 + i * 60, 4));
        }
        this.paddleSpeed = 4;
        this.paddleWidth = 100;
        this.levelName = "Green 3";
        this.name = new NameOfLevel(this.levelName);
        this.background = new ThirdSreenGui();
        int[] index = {0, 0, 0, 0, 0, 0};
        this.blocks = new ArrayList<>();
        // create 57 blocks in special pattern in loop , and show every block in special
        // Place
        for (int i = 0; i < 40; i++) {
            if (i <= 5) {
                Point p = new Point(800 - index[0] * 50 - 75, 195);
                Rectangle r = new Rectangle(p, 50, 20);
                blocks.add(new Block(r, Color.BLUE, "1"));
                index[0] += 1;
            }
            if (i < 13 && i >= 6) {
                Point p = new Point(800 - index[1] * 50 - 75, 175);
                Rectangle r = new Rectangle(p, 50, 20);
                blocks.add(new Block(r, Color.CYAN, "1"));
                index[1] += 1;
            }
            if (i <= 20 && i >= 13) {
                Point p = new Point(800 - index[2] * 50 - 75, 155);
                Rectangle r = new Rectangle(p, 50, 20);
                blocks.add(new Block(r, Color.YELLOW, "1"));
                index[2] += 1;
            }
            if (i <= 29 && i > 20) {
                Point p = new Point(800 - index[3] * 50 - 75, 135);
                Rectangle r = new Rectangle(p, 50, 20);
                blocks.add(new Block(r, Color.GREEN, "1"));
                index[3] += 1;
            }
            if (i <= 39 && i >= 30) {
                Point p = new Point(800 - index[4] * 50 - 75, 115);
                Rectangle r = new Rectangle(p, 50, 20);
                blocks.add(new Block(r, Color.red, "1"));
                index[4] += 1;
            }
        }
        this.numberOfBlocksToRemove = 40;
    }
    /**
     * getter of number of the balls in this level.
     * @return numberOfBalls -- the number of the balls in this level.
     */
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    /**
     * getter list of the velocity of the balls.
     * @return initialBallVelocities -- the list of the velocity of the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }
    /**
     * getter speed paddle.
     * @return paddleSpeed -- speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**
     * getter paddleWidth paddle.
     * @return paddleWidth -- Width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }
    /**
     * getter levelName.
     * @return levelName -- levelName.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }
    /**
     * getter getBackground.
     * @return getBackground -- getBackground of this level.
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }
    /**
     * getter list of the blocks.
     * @return blocks -- list blocks.
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }
    /**
     * getter numberOfBlocksToRemove.
     * @return numberOfBlocksToRemove -- number blocks to remove in this game.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
    /**
     * getter name level as sprite.
     * @return name -- name level as sprite.
     */
    @Override
    public Sprite getName() {
        return this.name;
    }

}
