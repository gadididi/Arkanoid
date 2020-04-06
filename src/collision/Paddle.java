package collision;

import java.awt.Color;

import game.GameLevel;
import game.Sprite;
import ball.Ball;
import ball.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import graphics.Point;
import graphics.Rectangle;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-03-29
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private Color colorOfPaddle;
    private double widthOfPaddle;
    private int paddleSpeed;

    /**
     * constructor paddle,create new paddle.
     * @param keyboard -- the keyboard of the game.
     * @param c        -- colour of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, Color c) {
        this.keyboard = keyboard;
        this.paddle = new Rectangle(new Point(360, 580), 120, 15);
        this.colorOfPaddle = c;
        this.widthOfPaddle = 120;
        this.paddleSpeed = 3;
    }

    /**
     * constructor paddle,create new paddle.
     * @param keyboard -- the keyboard of the game.
     * @param c        -- colour of the paddle.
     * @param width    -- width of the paddle.
     * @param speed    -- speed of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, Color c, double width, int speed) {
        this.keyboard = keyboard;
        this.paddle = new Rectangle(new Point(800 / 2 - width / 2, 580), width, 15);
        this.colorOfPaddle = c;
        this.widthOfPaddle = width;
        this.paddleSpeed = speed;
    }

    /**
     * make 1 step left.
     */
    public void moveLeft() {
        this.paddle = new Rectangle(
                new Point(this.paddle.getUpperLeft().getX() - this.paddleSpeed, this.paddle.getUpperLeft().getY()),
                this.widthOfPaddle, 15);
    }

    /**
     * make 1 step right.
     */
    public void moveRight() {
        this.paddle = new Rectangle(
                new Point(this.paddle.getUpperLeft().getX() + this.paddleSpeed, this.paddle.getUpperLeft().getY()),
                this.widthOfPaddle, 15);
    }

    /**
     * check witch arrow the user press and go to run it.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && this.paddle.getUpperLeft().getX() > 30) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) && this.paddle.getRight().start().getX() < 770) {
            this.moveRight();
        }
    }

    /**
     * draw the paddle on the surface.
     * @param d -- the surface that we can draw on this.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        d.setColor(this.colorOfPaddle);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX() + 1, (int) this.paddle.getUpperLeft().getY() + 1,
                (int) this.paddle.getWidth() - 1, (int) this.paddle.getHeight() - 1);
    }

    /**
     * getter upper left of the paddle.
     * @return getUpperLeft -- puuer left point of paddle.
     */
    public Point getUperLeftOfPaddle() {
        return this.paddle.getUpperLeft();
    }

    /**
     * getter.
     * @return paddle -- this paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * set new velocity to the ball depend the side of the collision with the
     * paddle.
     * @param collisionPoint  -- point of the collision.
     * @param currentVelocity -- the velocity right now.
     * @param hitter          -- the ball that hit this paddle.
     * @return v -- the update velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // check the all side of the potential collision ,and update the velocity, by
        // the situation
        double velo = Math.sqrt(currentVelocity.getDX() * currentVelocity.getDX()
                + currentVelocity.getDY() * currentVelocity.getDY());
        if (collisionPoint.getX() >= this.paddle.getUpperLeft().getX()
                && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + this.paddle.getWidth() / 5) {
            Velocity v = Velocity.fromAngleAndSpeed(300, velo);
            return v;
        }
        if (collisionPoint.getX() >= this.paddle.getUpperLeft().getX() + this.paddle.getWidth() / 5
                && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + 2 * this.paddle.getWidth() / 5) {
            Velocity v = Velocity.fromAngleAndSpeed(330, velo);
            return v;
        }
        if (collisionPoint.getX() >= this.paddle.getUpperLeft().getX() + 3 * this.paddle.getWidth() / 5
                && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + 4 * this.paddle.getWidth() / 5) {
            Velocity v = Velocity.fromAngleAndSpeed(30, velo);
            return v;
        }
        if (collisionPoint.getX() >= this.paddle.getUpperLeft().getX() + 4 * this.paddle.getWidth() / 5
                && collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + this.paddle.getWidth()) {
            Velocity v = Velocity.fromAngleAndSpeed(60, velo);
            return v;
        }
        Velocity v = Velocity.fromAngleAndSpeed(0, velo);
        return v;
    }

    /**
     * Add this paddle to the array of spirtes and collidable.
     * @param g -- object of game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove the paddle from the game.
     * @param game - the function gets the level.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * make new paddle in the middle of the screen.
     */
    public void middle() {
        this.paddle = new Rectangle(new Point(370, 580), 120, 15);
    }
}
