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
public class Level1 implements LevelInformation {
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
    private Sprite name;
    // List of all blocks in the game.
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * constructor Level1 .
     * this constructor build all the level staff , like number of balls etc..
     * @param d -- the DrawSurface of the game.
     */
    public Level1(DrawSurface d) {
        this.numberOfBalls = 1;
        this.initialBallVelocities = new ArrayList<>();
        for (int i = 0; i < numberOfBalls; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(0, 4));
        }
        this.paddleSpeed = 3;
        this.paddleWidth = 100;
        this.levelName = "Direct hit";
        this.background = new FirstScreenGui();
        this.blocks = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(388, 185), 30, 30), Color.RED, "1");
        blocks.add(block);
        this.numberOfBlocksToRemove = 1;
        this.name = new NameOfLevel(this.levelName);
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
     * @return numberOfBlocksToRemove -- nuber blocks to remove in this game.
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
