package graphics;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-03-12 (the version of the package this class was first added to)
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * constructor line.
     * <p>
     * put start point and end point.
     * @param start --x,y value of start point.
     * @param end   -- x,y value of end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor line.
     * <p>
     * put start point and end point by value of x1,x2,y1,y2.
     * @param x1 --x value of start point.
     * @param y1 -- y value of start point.
     * @param x2 --x value of end point.
     * @param y2 --y value of end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculate the distance between 2 points of this line by the method distance.
     * <p>
     * calculate the length of the line.
     * @return length -- Return the length of the line.
     */
    public double length() {
        double length = this.start.distance(end);
        return length;
    }

    /**
     * calculate the middle of the line.
     * <p>
     * calculate the middle of the line by average.
     * @return middle -- Returns the middle point of the line.
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;
        Point middle = new Point(middleX, middleY);
        return middle;
    }

    /**
     * geter for the start of the line.
     * @return this.start --Returns the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * geter for the end of the line.
     * @return this.end --Returns the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * calculate if 2 limit lines are intersecting .
     * <p>
     * the calculating by 2 equation and find if they are intersecting.
     * @param other --another line to calculate if they are intersecting.
     * @return Returns true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if ((this.start.getX() - this.end.getX()) == 0 || (other.start.getX() - other.end.getX() == 0)) {
            if ((this.start.getX() - this.end.getX()) == 0) {
                double mOther = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
                double bOther = -1 * mOther * other.start.getX() + other.start.getY();
                double checkX = this.start.getX();
                double chcekY = mOther * checkX + bOther;
                Point checkP = new Point(checkX, chcekY);
                double disA = ((this.start.distance(checkP)) + checkP.distance(end));
                double disB = ((other.start.distance(checkP) + checkP.distance(other.end)));
                double disAllA = this.start.distance(end);
                double disAllB = other.start.distance(other.end);
                // check if the difference between 2 distans is infinitesimal
                return checkIsIntersecting(disA, disB, disAllA, disAllB);
            } else {
                double mThis = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
                double bThis = -1 * mThis * this.start.getX() + this.start.getY();
                double checkX = other.start.getX();
                double chcekY = mThis * checkX + bThis;
                Point checkP = new Point(checkX, chcekY);
                double disA = ((this.start.distance(checkP)) + checkP.distance(end));
                double disB = ((other.start.distance(checkP) + checkP.distance(other.end)));
                double disAllA = this.start.distance(end);
                double disAllB = other.start.distance(other.end);
                // check if the difference between 2 distans is infinitesimal
                return checkIsIntersecting(disA, disB, disAllA, disAllB);
            }
        }
        double m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        if (m1 == m2) {
            return false;
        } else {
            double b1 = -1 * m1 * this.start.getX() + this.start.getY();
            double b2 = -1 * m2 * other.start.getX() + other.start.getY();
            double commonX = (b2 - b1) / (m1 - m2);
            double commonY = m1 * commonX + b1;
            Point checkPoint = new Point(commonX, commonY);
            double disA = ((this.start.distance(checkPoint)) + checkPoint.distance(end));
            double disB = ((other.start.distance(checkPoint) + checkPoint.distance(other.end)));
            double disAllA = this.start.distance(end);
            double disAllB = other.start.distance(other.end);
            // check if the difference between 2 distans is infinitesimal
            return checkIsIntersecting(disA, disB, disAllA, disAllB);
        }
    }

    /**
     * find if really intersection 2 lines by the tangle law.
     * <p>
     * the calculating by 2 equation and find the intersect point .
     * @param disA -- add 1 distance to another(3 points on the line).
     * @param disAllA --the distance of first line from the start to the end.
     * @param disB --add 1 distance to another(3 points on the line).
     * @param disAllB --the distance of second line from the start to the end..
     * @return boolean --  true if there is ,otherwise false.
     */
    public boolean checkIsIntersecting(double disA, double disB, double disAllA, double disAllB) {
        if ((Math.abs(disA - disAllA) <= 0.001) && (Math.abs(disB - disAllB) < 0.001)) {
            return true;
        }
        return false;
    }

    /**
     * find the point of the intersection .
     * <p>
     * the calculating by 2 equation and find the intersect point .
     * @param other --another line to calculate if they are intersecting.
     * @return Returns point intersect if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        if ((this.start.getX() - this.end.getX()) == 0 || (other.start.getX() - other.end.getX() == 0)) {
            if ((this.start.getX() - this.end.getX()) == 0) {
                double mOther = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
                double bOther = -1 * mOther * other.start.getX() + other.start.getY();
                double checkX = this.start.getX();
                double chcekY = mOther * checkX + bOther;
                Point checkP = new Point(checkX, chcekY);

                double disA = ((this.start.distance(checkP)) + checkP.distance(end));
                double disB = ((other.start.distance(checkP) + checkP.distance(other.end)));
                double disAllA = this.start.distance(end);
                double disAllB = other.start.distance(other.end);
                // check if the difference between 2 distans is infinitesimal
                if ((Math.abs(disA - disAllA) <= 0.001) && (Math.abs(disB - disAllB) <= 0.001)) {
                    return checkP;
                } else {
                    return null;
                }
            } else {
                double mThis = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
                double bThis = -1 * mThis * this.start.getX() + this.start.getY();
                double checkX = other.start.getX();
                double chcekY = mThis * checkX + bThis;
                Point checkP = new Point(checkX, chcekY);
                double disA = ((this.start.distance(checkP)) + checkP.distance(end));
                double disB = ((other.start.distance(checkP) + checkP.distance(other.end)));
                double disAllA = this.start.distance(end);
                double disAllB = other.start.distance(other.end);
                // check if the difference between 2 distans is infinitesimal
                if ((Math.abs(disA - disAllA) <= 0.001) && (Math.abs(disB - disAllB) <= 0.001)) {
                    return checkP;
                } else {
                    return null;
                }
            }
        }
        double m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        if (m1 == m2) {
            return null;
        } else {
            double b1 = -1 * m1 * this.start.getX() + this.start.getY();
            double b2 = -1 * m2 * other.start.getX() + other.start.getY();
            double commonX = (b2 - b1) / (m1 - m2);
            double commonY = m1 * commonX + b1;
            Point checkPoint = new Point(commonX, commonY);
            double disA = ((this.start.distance(checkPoint)) + checkPoint.distance(end));
            double disB = ((other.start.distance(checkPoint) + checkPoint.distance(other.end)));
            double disAllA = this.start.distance(end);
            double disAllB = other.start.distance(other.end);
            // check if the difference between 2 distans is infinitismal
            if ((Math.abs(disA - disAllA) <= 0.001) && (Math.abs(disB - disAllB) <= 0.001)) {
                return checkPoint;
            }
            return null;
        }
    }

    /**
     * find if 2 lines are the same.
     * <p>
     * the calculating if they same line by equals the value of x,y of start and end.
     * @param other --another line to calculate if they are same.
     * @return return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if ((this.start.getX() == other.start.getX() && this.start.getY() == other.start.getY())
                && (this.end.getX() == other.end.getX() && this.end.getY() == other.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null. Otherwise,
     * return the closest intersection point to the start of the line.
     * @param rect --the rectangle that insert with the trajectory .
     * @return closestIntersect -- the closest Intersection To Start Of Line with the rectangle.
     */

    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line check = new Line(this.start, this.end);
        if (rect.intersectionPoints(check) == null) {
            return null;
        }
        Point closestIntersect = new Point(0, 0);
        int flag = 0;
        double distanse = 10000000;
        // run all the collision point
        for (Point c : (rect.intersectionPoints(check))) {
            Point run = c;
            if (distanse > (run.distance(check.start))) {
                distanse = (run.distance(check.start));
                closestIntersect = run;
                flag = 1;
            }
        }
        // if there is insertion point at all
        if (flag == 1) {
            return closestIntersect;
        }
        return null;
    }
}