package ball;

import graphics.Point;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-03-12 (the version of the package this class was first added to)
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx --delta x of axis x.
     * @param dy --delta y of axis y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * kind of constructor to set the velocity by angle and vector of speed.
     * @param angle --angle of the direction velocity.
     * @param speed --the speed of the velocity.
     * @return Velocity -- update the velocity from angle in dx dy .
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleBall = Math.toRadians(angle - 90);
        double dx = speed * Math.cos(angleBall);
        double dy = speed * Math.sin(angleBall);
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point. with position (x+dx,
     * y+dy).
     * @param p --the center of the last location of the ball.
     * @return updateP -- the new location.
     */
    public Point applyToPoint(Point p) {
        Point updateP = new Point(this.dx + p.getX(), this.dy + p.getY());
        return updateP;
    }

    /**
     * geter for dx of the velocity.
     * @return this.dx-- the value of dx of the velocity.
     */
    public double getDX() {
        return dx;
    }

    /**
     * geter for dy of the velocity.
     * @return this.dy-- the value of dy of the velocity.
     */
    public double getDY() {
        return dy;
    }
}