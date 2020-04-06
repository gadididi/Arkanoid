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
public class Level2 implements LevelInformation {
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
     * constructor Level2 . this constructor build all the level staff , like number
     * of balls etc..
     * @param d -- the DrawSurface of the game.
     */
    public Level2(DrawSurface d) {
        this.numberOfBalls = 10;
        this.initialBallVelocities = new ArrayList<>();
        for (int i = 0; i < numberOfBalls / 2; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(350 - 6 * i, 4));
        }
        for (int j = numberOfBalls / 2; j < numberOfBalls; j++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(10 + 6 * j, 4));
        }
        this.paddleSpeed = 2;
        this.paddleWidth = 400;
        this.levelName = "Wide Easy";
        this.name = new NameOfLevel(this.levelName);
        this.background = new SecondScreenGui();
        this.blocks = new ArrayList<>();
        this.numberOfBlocksToRemove = 15;
        Color c = Color.YELLOW;
        for (int i = 0; i < this.numberOfBlocksToRemove; i++) {
            if (i < 4 && i > 1) {
                c = Color.ORANGE;
            }
            if (i < 6 && i > 3) {
                c = Color.RED;
            }
            if (i < 9 && i > 5) {
                c = Color.GREEN;
            }
            if (i < 11 && i > 8) {
                c = Color.BLUE;
            }
            if (i < 13 && i > 10) {
                c = Color.PINK.darker();
            }
            if (i < 15 && i > 12) {
                c = Color.CYAN.darker();
            }
            blocks.add(new Block(new Rectangle(new Point(32 + i * 49, 250), 49, 30), c, "1"));
        }
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
