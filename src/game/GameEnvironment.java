package game;

import java.util.ArrayList;
import java.util.List;

import collision.Collidable;
import collision.CollisionInfo;
import graphics.Line;
import graphics.Point;
import graphics.Rectangle;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-03-29
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * constructor GameEnvironment. set this list to "l" the list we get on parameter .
     * @param l -- list of Collidables.
     */
    public GameEnvironment(List<Collidable> l) {
        this.collidables = l;
    }

    /**
     * constructor GameEnvironment.
     */
    public GameEnvironment() {
        List<Collidable> list = new ArrayList<Collidable>();
        this.collidables = list;
    }

    /**
     * add the given collidable to the environment array list.
     * @param c -- object of Collidables.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * remove the given collidable from the environment array list.
     * @param c -- object of Collidables.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**
     * getList. This method returns the entire collision list.
     * @return collissionList - the collision list.
     */
    public List<Collidable> getList() {
        return this.collidables;
    }

    /**
     * getClosestCollision. This method returns information regarding the closest.
     * collision point. it saves the point itself as well as the shape.
     * (Paddle/Block).
     * @param trajectory - the trajectory line of the ball.
     * @return information regarding the collision point, null if collision does not exist.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> colPoints = new ArrayList<Point>();
        for (Collidable c : this.collidables) {
            Rectangle rec = c.getCollisionRectangle();
            if (!rec.intersectionPoints(trajectory).isEmpty()) {
                colPoints.add(trajectory.closestIntersectionToStartOfLine(rec));
            }
        }
        if (!colPoints.isEmpty()) {
            Point p = colsestPointToStarting(colPoints, trajectory.start());
            for (Collidable col : this.collidables) {
                Rectangle rec = col.getCollisionRectangle();
                if (rec.isOnRectangle(p)) {
                    // check if p is on rec sides
                    return new CollisionInfo(p, col);
                }
            }
        }
        return null;
    }

    /**
     * returnColsestPointToStart. This method returns the closest point to starting
     * point we want.
     * @param colPoints - gets a collision points (from game environment)
     * @param start     - a string point to be compared with.
     * @return the closest point from the collision list to the start point.
     */
    public Point colsestPointToStarting(List<Point> colPoints, Point start) {
        double minDistance = start.distance(colPoints.get(0));
        double currDistance;
        // keep the first point
        Point closest = colPoints.get(0);
        for (Point p : colPoints) { // runs on the entire collision list
            currDistance = start.distance(p); // comparing distances
            if (minDistance > currDistance) {
                minDistance = currDistance;
                closest = p;
            }
        }
        return closest;
    }
}
