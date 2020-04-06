package graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-03-31 (the version of the package this class was first added to)
 */
public class Rectangle {
    private Point upperL;
    private double width;
    private double height;
    private Line left;
    private Line right;
    private Line down;
    private Line up;

    /**
     * constructor rectangle.Create a new rectangle with location and width/height.
     * @param upperLeft -- point left up for the rectangle.
     * @param width     -- width of the rectangle.
     * @param height    -- height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperL = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
        Point leDown = new Point(upperLeft.getX(), upperLeft.getY() + this.height);
        Point riDown = new Point(leDown.getX() + this.width, leDown.getY());
        Point riUp = new Point(upperLeft.getX() + this.width, upperLeft.getY());
        this.left = new Line(upperLeft, leDown);
        this.right = new Line(riUp, riDown);
        this.down = new Line(leDown, riDown);
        this.up = new Line(upperLeft, riUp);

    }

    /**
     * find with line the intersection points. Return a (possibly empty) List of
     * intersection points with the specified line.
     * @param line -- line that we want to check it.
     * @return inter -- ArrayList<Point> of intersection line with rectangle.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> inter = new ArrayList<Point>();
        // check 4 sides
        if (line.isIntersecting(down)) {
            Point intersct1 = new Point((line.intersectionWith(down)).getX(), (line.intersectionWith(down)).getY());
            inter.add(intersct1);
        }
        if (line.isIntersecting(up)) {
            Point intersct2 = new Point((line.intersectionWith(up)).getX(), (line.intersectionWith(up)).getY());
            inter.add(intersct2);
        }
        if (line.isIntersecting(right)) {
            Point intersct3 = new Point((line.intersectionWith(right)).getX(), (line.intersectionWith(right)).getY());
            inter.add(intersct3);
        }
        if (line.isIntersecting(left)) {
            Point intersct4 = new Point((line.intersectionWith(left)).getX(), (line.intersectionWith(left)).getY());
            inter.add(intersct4);
        }
        return inter;
    }

    /**
     * getter.
     * @return width -- Return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getter.
     * @return height -- Return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getter.
     * @return upperL -- Returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperL;
    }

    /**
     * getter.
     * @return up -- Returns the up line of the rectangle.
     */
    public Line getUp() {
        return this.up;
    }

    /**
     * getter.
     * @return down -- Returns the down line of the rectangle.
     */
    public Line getDown() {
        return this.down;
    }

    /**
     * getter.
     * @return left -- Returns the left line of the rectangle.
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * getter.
     * @return right -- Returns the right line of the rectangle.
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * isOnRectangle. checks if a specific point is in a rectangle (intersection wise).
     * @param point - the point to be checked with
     * @return true if the point is in the rectangle, false otherwise.
     */
    public boolean isOnRectangle(Point point) {
        double x = point.getX();
        double y = point.getY();
        double h = this.getHeight();
        double w = this.getWidth();
        if (Math.abs(x - this.getUpperLeft().getX()) <= 0.0001
                || Math.abs(x - this.getUpperLeft().getX() - w) <= 0.0001) {
            return (y >= this.getUpperLeft().getY() && y <= this.getUpperLeft().getY() + h);
        }
        if ((Math.abs(y - this.getUpperLeft().getY()) <= 0.0001)
                || Math.abs(y - this.getUpperLeft().getY() - h) <= 0.0001) {
            return (x >= this.getUpperLeft().getX() && x <= this.getUpperLeft().getX() + w);
        }
        return false;
    }
}
