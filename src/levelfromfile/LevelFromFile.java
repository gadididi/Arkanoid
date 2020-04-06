package levelfromfile;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.imageio.ImageIO;
import ball.Velocity;
import biuoop.DrawSurface;
import collision.Block;
import game.Sprite;
import levels.LevelInformation;
import levels.NameOfLevel;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class LevelFromFile implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private String pathOfBlockDef;
    private Sprite background = null;
    private BufferedImage img;
    private Sprite name;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private int rowHeight;

    /**
     * empty contractor.
     */
    public LevelFromFile() {
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
     * set Colour for back ground.
     * @param c -- the Colour for background.
     */
    public void setBackByColor(Color c) {
        this.background = new Sprite() {

            /**
             * draw the back ground.
             * @param d -- the DrawSurface.
             */
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(c);
                d.fillRectangle(0, 0, 800, 600);
            }
            /**
             * nothing.
             */
            @Override
            public void timePassed() {
            }
        };
    }

    /**
     * set image for  the back ground.
     * @param path -- the path for image.
     */
    public void setImg(String path) {
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
            this.img = ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Failed reading image");
            e.printStackTrace(System.err);
            return;
        }
        this.background = new Sprite() {

            /**
             * draw the back ground.
             * @param d -- the DrawSurface.
             */
            @Override
            public void drawOn(DrawSurface d) {
                d.drawImage(0, 0, img);
            }

            /**
             * nothing.
             */
            @Override
            public void timePassed() {
            }
        };
    }

    /**
     * getter pathOfBlockDef.
     * @return pathOfBlockDef -- the path for blocks.
     */
    public String getDefOfBlock() {
        return this.pathOfBlockDef;
    }

    /**
     * setter pathOfBlockDef.
     * @param path -- the path for blocks.
     */
    public void setDefOfBlock(String path) {
        this.pathOfBlockDef = path;
    }

    /**
     * getter rowHeight.
     * @return rowHeight -- the rowHeight for blocks.
     */
    public int rowHeighting() {
        return this.rowHeight;
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
     * set list of the blocks.
     * @param list -- list blocks.
     */
    public void setBlocks(List<Block> list) {
        this.numberOfBlocksToRemove = list.size();
        this.blocks = list;
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

    /**
     * set name level.
     * @param n -- name.
     */
    public void setName(String n) {
        this.levelName = n;
        this.name = new NameOfLevel(n);
    }
    /**
     * set width paddle.
     * @param width -- width.
     */
    public void setPaddleWidth(String width) {
        this.paddleWidth = Integer.parseInt(width);
    }
    /**
     * set speed paddle.
     * @param speed -- speed.
     */
    public void setPaddleSpeed(String speed) {
        this.paddleSpeed = Integer.parseInt(speed);
    }
    /**
     * set velocity speed for balls.
     * @param vel -- list of velocity.
     */
    public void setVelForBalls(List<Velocity> vel) {
        this.initialBallVelocities = vel;
        this.numberOfBalls = vel.size();
    }
    /**
     * set number of blocks.
     * @param num -- string of number.
     */
    public void setNumberOfBlocksToRemove(String num) {
        this.numberOfBlocksToRemove = Integer.parseInt(num);
    }
    /**
     * set row of blocks.
     * @param row -- string of row.
     */
    public void setRowHeight(String row) {
        this.rowHeight = Integer.parseInt(row);
    }
}
