package ball;

import game.Sprite;
import biuoop.DrawSurface;
import collision.Frame;
import collision.Paddle;
import game.GameLevel;
import game.GameEnvironment;
import graphics.Line;
import graphics.Point;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-03-29
 */
public class Ball implements Sprite {
    private int size;
    private java.awt.Color ballColor;
    private Point location;
    private Velocity velocity;
    private Frame frame;
    private GameEnvironment game;

    /**
     * constructor ball.
     * <p>
     * put in the value of x ,y.
     * @param center -- value of x,y of the center of the circle.
     * @param r      -- value of radius.
     * @param color  -- colour of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.location = center;
        this.size = r;
        this.ballColor = color;
        this.velocity = new Velocity(2, -2);
    }

    /**
     * constructor ball.
     * <p>
     * put in the value of x ,y.
     * @param x     -- value of x, of the center of the circle.
     * @param y     -- value of y, of the center of the circle.
     * @param r     -- value of radios.
     * @param color -- colour of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        Point mid = new Point(x, y);
        this.location = mid;
        this.size = r;
        this.ballColor = color;
        this.velocity = new Velocity(3, -4);
    }
    /**
     * constructor ball.
     * <p>
     * put in the value of x ,y.
     * @param x     -- value of x, of the center of the circle.
     * @param y     -- value of y, of the center of the circle.
     * @param r     -- value of radios.
     * @param v     -- velocity.
     * @param color -- colour of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color, Velocity v) {
        Point mid = new Point(x, y);
        this.location = mid;
        this.size = r;
        this.ballColor = color;
        this.velocity = v;
    }

    /**
     * setGameEnvironment. set the game environment.
     * @param gameEnviroment - a game environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnviroment) {
        this.game = gameEnviroment;
    }

    /**
     * getGame. get the game environment.
     * @return game - a game environment.
     */
    public GameEnvironment getGame() {
        return this.game;
    }
    /**
     * getGame. get the center.
     * @return Point - a Point loction.
     */
    public Point getLocation() {
        return this.location;
    }
    /**
     * geter of x of the center of the ball.
     * @return x -- value x of the center.
     */
    public double getX() {
        return this.location.getX();
    }

    /**
     * geter of y of the center of the ball.
     * @return y -- value y of the center.
     */
    public double getY() {
        return this.location.getY();
    }

    /**
     * geter of radius of the ball.
     * @return r -- value radious of the ball.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * geter of colour of the ball.
     * @return ballColor -- colour of the ball.
     */
    public java.awt.Color getColor() {
        return this.ballColor;
    }

    /**
     * draw the ball on the given DrawSurface.
     * <p>
     * print the ball on the GUI surface.
     * @param surface --the board.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.ballColor);
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.size);
    }

    /**
     * after every part of time call to moveonestep.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * set velocity by parameter velocity.
     * @param v --the velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set velocity by dx and dy ,changing of locations.
     * @param dx --the delta x location.
     * @param dy --the delta y location.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * geter of Velocity of the ball.
     * @return this.velocity -- Velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * seter default of Velocity of the ball.
     */
    public void setVelocity() {
        this.velocity = new Velocity(20, 10);
    }
    /**
     * Remove the ball from the game.
     *
     * @param g - the function gets the level.
     */
    public void removeFrom(GameLevel g) {
        g.removeSprite(this);
    }
    /**
     * set frame by frame parameter ,to limit the ball moves and the colour of the
     * board. in this ass3 we don't need frame because i standing border block. but
     * for the next ass frame should be.
     * @param framed --the frame for this ball.
     */
    public void setFrame(Frame framed) {
        this.frame = framed;
    }

    /**
     * set frame by parameters ,to limit the ball moves.
     * @param up    --the up frame for this ball.
     * @param down  --the down frame for this ball.
     * @param left  --the left frame for this ball.
     * @param right --the right frame for this ball.
     * @param f     --the colour frame for this ball.
     */
    public void setFrame(double up, double down, double left, double right, java.awt.Color f) {
        this.frame = new Frame(up, down, left, right, f);
    }

    /**
     * set the next location of this ball , change his velocity ( +/-) by the
     * directions. chose the next location by the kind of the coladble.
     */
    public void moveOneStep() {
        double deltaX = this.velocity.getDX();
        double deltaY = this.velocity.getDY();
        Point direct = new Point(this.location.getX() + deltaX, this.location.getY() + deltaY);
        Line direction = new Line(this.location, direct);
        try {
            Paddle p = (Paddle) this.getGame().getClosestCollision(direction).collisionObject();
            if (this.location.getX() > p.getUperLeftOfPaddle().getX()
                    && this.location.getX() < p.getUperLeftOfPaddle().getX() + 120
                    && this.location.getY() > p.getUperLeftOfPaddle().getY()) {
                Velocity newVel = this.getGame().getClosestCollision(direction).collisionObject().hit(this,
                        this.getGame().getClosestCollision(direction).collisionPoint(), this.velocity);
                this.velocity = newVel;
                this.location = this.getVelocity()
                        .applyToPoint(new Point(this.location.getX(), this.location.getY() - 5));
            }
        } catch (RuntimeException e) {
            /* This block will only when the coladble is block */
            System.out.print("");
        }
        if (game.getClosestCollision(direction) != null) { // check if there collision with blcok
            // update the velocity
            Velocity newVel = this.getGame().getClosestCollision(direction).collisionObject().hit(this,
                    this.getGame().getClosestCollision(direction).collisionPoint(), this.velocity);
            this.velocity = newVel;
        } else { // hit in the border of the frame
            if (((this.velocity.getDX() < 0)
                    && (this.getX() - this.size + this.velocity.getDX() < this.frame.getLeft()))
                    || ((this.velocity.getDX() > 0)
                            && (this.getX() + this.size + this.velocity.getDX() > this.frame.getRight()))) {
                deltaX = -deltaX;
            }
            if (((this.velocity.getDY() > 0)
                    && (this.getY() + this.size + this.velocity.getDY() > this.frame.getDown()))
                    || ((this.velocity.getDY() < 0)
                            && (this.getY() - this.size + this.velocity.getDY() < this.frame.getUp()))) {
                deltaY = -deltaY;
            }
            this.velocity = new Velocity(deltaX, deltaY);
            this.location = this.getVelocity().applyToPoint(this.location);
        }
    }

    /**
     * @param g --the object game that we create ,add this ball to the arrylist of
     *          sprite.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}