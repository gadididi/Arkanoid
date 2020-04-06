package graphics;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-03-12 (the version of the package this class was first added to)
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     * <p>
     * put in the value of x ,y.
     * @param x -- value of x.
     * @param y -- value of y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * setter for x of the point.
     * @param ex -- value of x.
     */
    public void setX(double ex) {
        this.x = ex;
    }

    /**
     * setter for y of the point.
     * @param yi -- value of y.
     */
    public void setY(double yi) {
        this.y = yi;
    }

    /**
     * calculate the distance between 2 points .
     * <p>
     * calculate the distance between 2 points by distance formula.
     * @param other another point to calculate with him the distance.
     * @return distance -- the distance between 2 points.
     */
    public double distance(Point other) {
        double pointsDifferenceX = Math.pow((this.x - other.getX()), 2);
        double pointsDifferenceY = Math.pow((this.y - other.getY()), 2);
        double sum = pointsDifferenceY + pointsDifferenceX;
        double distance = Math.sqrt(sum);
        return distance;
    }

    /**
     * check if 2 point are the same.
     * <p>
     * equals the x value and the y value both points.
     * @param other -- point.
     * @return true-- when the points same, false when its not.
     */
    public boolean equals(Point other) {
        if ((this.getX() == other.getX()) && (this.getY() == other.getY())) {
            return true;
        }
        return false;
    }

    /**
     * getter for x of the point.
     * <p>
     * return x of the point.
     * @return this.x-- the value of x of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * getter for y of the point.
     * <p>
     * return y of the point.
     * @return this.y-- the value of y of the point.
     */
    public double getY() {
        return this.y;
    }
}