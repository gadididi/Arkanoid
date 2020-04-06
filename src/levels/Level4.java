package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
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
public class Level4 implements LevelInformation {
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
     * constructor Level4 . this constructor build all the level staff , like number
     * of balls etc..
     * @param d -- the DrawSurface of the game.
     */
    public Level4(DrawSurface d) {
        this.numberOfBalls = 3;
        this.initialBallVelocities = new ArrayList<>();
        for (int i = 0; i < numberOfBalls; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(300 + i * 60, 4));
        }
        this.paddleSpeed = 4;
        this.paddleWidth = 100;
        this.levelName = "Final Four";
        this.name = new NameOfLevel(this.levelName);
        this.background = new FourthScreenGui();
        int[] index = {0, 0, 0, 0, 0, 0, 0};
        this.blocks = new ArrayList<>();
        // create 57 blocks in special pattern in loop , and show every block in special
        // Place
        for (int i = 0; i < 105; i++) {
            if (i < 15) {
                Point p = new Point(800 - index[0] * 50 - 75, 215);
                Rectangle r = new Rectangle(p, 50, 20);
                blocks.add(new Block(r, Color.BLUE, "1"));
                index[0] += 1;
            }
            if (i < 30 && i >= 15) {
                Point p = new Point(800 - index[1] * 50 - 75, 195);
                Rectangle r = new Rectangle(p, 50, 20);
                blocks.add(new Block(r, Color.CYAN.darker().darker(), "1"));
                index[1] += 1;
            }
            if (i < 45 && i >= 30) {
                Point p = new Point(800 - index[2] * 50 - 75, 175);
                Rectangle r = new Rectangle(p, 50, 20);
                blocks.add(new Block(r, Color.YELLOW, "1"));
                index[2] += 1;
            }
            if (i < 60 && i >= 45) {
                Point p = new Point(800 - index[3] * 50 - 75, 155);
                Rectangle r = new Rectangle(p, 50, 20);
                blocks.add(new Block(r, Color.GREEN, "1"));
                index[3] += 1;
            }
            if (i < 75 && i >= 60) {
                Point p = new Point(800 - index[4] * 50 - 75, 135);
                Rectangle r = new Rectangle(p, 50, 20);
                blocks.add(new Block(r, Color.PINK, "1"));
                index[4] += 1;
            }
            if (i < 90 && i >= 75) {
                Point p = new Point(800 - index[5] * 50 - 75, 115);
                Rectangle r = new Rectangle(p, 50, 20);
                blocks.add(new Block(r, Color.RED, "2"));
                index[5] += 1;
            }
            if (i <= 105 && i >= 90) {
                Point p = new Point(800 - index[6] * 50 - 75, 95);
                Rectangle r = new Rectangle(p, 50, 20);
                blocks.add(new Block(r, Color.RED.darker(), "2"));
                index[6] += 1;
            }
        }
        this.numberOfBlocksToRemove = 105;
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
