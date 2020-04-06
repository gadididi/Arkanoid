package collision;

import graphics.Point;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-03-29
 */
public class CollisionInfo {
    private Point collisionP;
    private Collidable collisionOb;

    /**
     * constructor CollisionInfo.
     * <p>
     * create a CollisionInfo by get Point and Collidable shape.
     * @param collisP -- the Point of the collision.
     * @param shape   -- Collidable shape that the ball collision with .
     */
    public CollisionInfo(Point collisP, Collidable shape) {
        this.collisionP = collisP;
        this.collisionOb = shape;
    }

    /**
     * get the collisionPoint.
     * @return collisionP -- the collisionPoint.
     */
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return collisionP;
    }

    /**
     * get the object of the collision.
     * @return collisionOb -- the collision Object.
     */
    // the collidable object involved in the collision.
    public Collidable collisionObject() {
        return collisionOb;
    }
}
