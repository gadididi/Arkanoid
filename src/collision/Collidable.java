package collision;

import ball.Ball;
import ball.Velocity;
import graphics.Point;
import graphics.Rectangle;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-03-29
 */
public interface Collidable {
    /**
     * to get the specific shape.
     * @return Rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity.
     * @param collisionPoint  -- the point of collision.
     * @param currentVelocity -- the velocity right now.
     * @param hitter -- the ball that hit.
     * @return Velocity -- the updated velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
