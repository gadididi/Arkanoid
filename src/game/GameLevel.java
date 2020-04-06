package game;

import java.awt.Color;
import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import collision.Block;
import collision.BlockRemover;
import ball.Ball;
import ball.BallRemover;
import collision.Paddle;
import collision.Collidable;
import collision.Frame;
import graphics.Point;
import graphics.Rectangle;
import levels.LevelInformation;
import tools.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Frame f;
    private Counter blocksCounter;
    private Counter ballsCounter = new Counter(0);
    private Counter gameScore;
    private Counter numberOfLives;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private Paddle paddle;
    private LevelInformation information;

    /**
     * constructor GameLevel.
     * @param info  -- the object info about this game.
     * @param k     -- KeyboardSensor of this game.
     * @param ar    -- the runner of this game.
     * @param score -- counter of score in the game (pass the levels).
     * @param lives -- the number of lives that stay.
     */
    public GameLevel(LevelInformation info, KeyboardSensor k, AnimationRunner ar, Counter score, Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gameScore = score;
        this.running = true;
        this.runner = ar;
        this.blocksCounter = new Counter(0);
        this.keyboard = k;
        this.f = new Frame(0, 800, 0, 800, Color.white);
        this.information = info;
        this.numberOfLives = lives;
    }

    /**
     * add Collidable to the array list of the Collidables.
     * @param c -- Collidable shape that can be Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * remove Collidable to the array list of the Collidables.
     * @param c -- Collidable shape that can be Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * add Sprite to the array list of the Sprites.
     * @param s -- Sprite shape that in the game (actually all the objects).
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove Sprite to the array list of the Sprites.
     * @param s -- Sprite shape that in the game (actually all the objects).
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and 2 Balls (and Paddle) and add.
     * them to the game.create frame(for the next assignment ) ,and create 4 blocks.
     * for border (for the balls).
     */
    public void initialize() {
        addSprite(this.information.getBackground());
        // create the borders
        Rectangle leftBorder = new Rectangle(new Point(0, 0), 25, 600);
        Rectangle rightBorder = new Rectangle(new Point(775, 0), 50, 600);
        Rectangle upBorder = new Rectangle(new Point(0, 30), 900, 30);
        Rectangle data = new Rectangle(new Point(0, 0), 820, 30);
        Rectangle deadZone = new Rectangle(new Point(50, 700), 700, 50);
        Block[] borders = new Block[5];
        borders[0] = new Block(leftBorder, Color.DARK_GRAY.brighter(), "X");
        borders[1] = new Block(rightBorder, Color.DARK_GRAY.brighter(), "X");
        borders[2] = new Block(upBorder, Color.DARK_GRAY.brighter(), "X");
        borders[3] = new Block(deadZone, Color.DARK_GRAY.brighter(), "X");
        borders[4] = new Block(data, Color.WHITE, "X");
        for (int j = 0; j < 5; j++) {
            borders[j].addToGame(this);
        }
        // create all the indicators and add them to he game.
        ScoreTrackingListener score = new ScoreTrackingListener(this.gameScore);
        ScoreIndicator indicator = new ScoreIndicator(this.gameScore, data);
        LivesIndicator lives = new LivesIndicator(this.numberOfLives, data);
        this.blocksCounter.increase(this.information.numberOfBlocksToRemove());
        BlockRemover removerBlock = new BlockRemover(this, blocksCounter);
        addSprite(indicator);
        addSprite(lives);
        addSprite(this.information.getName());
        // add all the blocks.
        for (int j = 0; j < this.information.numberOfBlocksToRemove(); j++) {
            this.information.blocks().get(j).addToGame(this);
            this.information.blocks().get(j).addHitListener(removerBlock);
            this.information.blocks().get(j).addHitListener(score);
        }
        createBalls(); // create balls
        this.paddle = new Paddle(this.keyboard, Color.YELLOW, this.information.paddleWidth(),
                this.information.paddleSpeed());
        this.paddle.addToGame(this); // create the paddle.
        BallRemover removerBall = new BallRemover(this, ballsCounter);
        borders[3].addHitListener(removerBall);
    }

    /**
     * Run the game -- start the animation loop and notice every sprite that one.
     * loop pass for change location.
     */
    public void playOneTurn() {
        while (this.numberOfLives.getValue() > 0) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new CountdownAnimation(2, 3, this.sprites, this.f)));
            this.running = true;
            this.runner.run(this);
            if (this.blocksCounter.getValue() == 0) {
                break;
            }
        }
    }

    /**
     * create balls by the number we get from the info.
     */
    public void createBalls() {
        Ball[] balls = new Ball[this.information.numberOfBalls()];
        for (int i = 0; i < this.information.numberOfBalls(); i++) {
            balls[i] = new Ball(400, 550, 5, Color.RED, this.information.initialBallVelocities().get(i));
            balls[i].addToGame(this);
            balls[i].setGameEnvironment(this.environment);
            balls[i].setFrame(f);
            this.ballsCounter.increase(1);
        }
    }

    /**
     * put the paddle in the middle of the screen.
     */
    public void fixPaddle() {
        this.paddle.removeFromGame(this);
        this.paddle = new Paddle(this.keyboard, Color.YELLOW, this.information.paddleWidth(),
                this.information.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * the logic of the game , every loop call to playOneTurn. fix the paddle if we
     * have to ,create new balls. and change the running boolean when we should.
     * @param d -- the DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        f.drawForFrame(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        if (numberOfLives.getValue() > 1) {
            if (this.ballsCounter.getValue() == 0) {
                createBalls();
                fixPaddle();
                this.numberOfLives.decrease(1);
                this.running = false;
            }
        }
        if (this.blocksCounter.getValue() == 0) {
            this.gameScore.increase(100);
            this.running = false;
        }
        if (this.ballsCounter.getValue() == 0 && this.numberOfLives.getValue() == 1) {
            this.numberOfLives.decrease(1);
            this.running = false;
        }
    }

    /**
     * indicator for stop or not the animation.
     * @return running -- this boolean indicator for stoping.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * getter number of the live.
     * @return numberOfLives -- the counter of number of the live.
     */
    public Counter numberOfLives() {
        return this.numberOfLives;
    }

    /**
     * @param block - the function gets the block to remove from the list.
     */
    public void removeFromBlocks(Block block) {
        this.information.blocks().remove(block);
    }
}