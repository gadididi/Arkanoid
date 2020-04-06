package collision;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import game.GameLevel;
import game.Sprite;
import ball.Ball;
import ball.Velocity;
import biuoop.DrawSurface;
import graphics.Point;
import graphics.Rectangle;;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-03-29
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color = Color.white.darker();
    private String countHits;
    private List<HitListener> hitListeners = new ArrayList<>();
    private List<Color> colors;
    private Color stroke = null;
    private List<Image> images = null;
    // private boolean scoreBlock = false;

    /**
     * constructor blcok.
     * <p>
     * create blcok by get rectangle color and string of the number of hits.
     * @param rect -- value of x,y of the center of the circle.
     * @param s    -- the text on the block.
     * @param c    -- color of the block.
     */
    public Block(Rectangle rect, Color c, String s) {
        this.rectangle = rect;
        this.color = c;
        this.countHits = s;
        // this.scoreBlock = true;
    }

    /**
     * constructor blcok.
     * <p>
     * create blcok by get rectangle color and string of the number of hits.
     * @param rectangle -- rectangle of block.
     * @param colors    -- list color for block.
     * @param hitPoints    -- hit points of the block.
     * @param stroke    -- border of the block.
     * @param imageList    -- list images.
     */
    public Block(Rectangle rectangle, List<Color> colors, int hitPoints, Color stroke, List<Image> imageList) {
        this.rectangle = rectangle;
        this.colors = colors;
        this.countHits = Integer.toString(hitPoints);
        this.stroke = stroke;
        this.images = imageList;
    }

    /**
     * do nothing ,block don't move.
     */
    @Override
    public void timePassed() {

    }

    /**
     * draw the block on the given DrawSurface.
     * print the block on the GUI surface.
     * @param surface --the board.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        if (stroke != null) { // if thehe are border to the block
            surface.setColor(stroke);
            surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(), (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
        }
        if (this.colors != null) { // if there are colors for this block
            if (!this.colors.isEmpty()) {
                if (Integer.parseInt(this.countHits) > colors.size()) {
                    surface.setColor(this.colors.get(colors.size() - 1));
                    surface.fillRectangle((int) this.rectangle.getUpperLeft().getX() + 1,
                            (int) this.rectangle.getUpperLeft().getY() + 1, (int) this.rectangle.getWidth(),
                            (int) this.rectangle.getHeight());
                } else {
                    surface.setColor(this.colors.get(Integer.parseInt(this.countHits) - 1));
                    surface.fillRectangle((int) this.rectangle.getUpperLeft().getX() + 1,
                            (int) this.rectangle.getUpperLeft().getY() + 1, (int) this.rectangle.getWidth(),
                            (int) this.rectangle.getHeight());
                }

            }
        }
        if (this.images != null) { // if there are list of imge for this block
            if (!this.images.isEmpty()) {
                if (Integer.parseInt(this.countHits) > images.size()) {
                    surface.drawImage((int) this.rectangle.getUpperLeft().getX() + 1,
                            (int) this.rectangle.getUpperLeft().getY() + 1, this.images.get(images.size() - 1));
                } else {
                    surface.drawImage((int) this.rectangle.getUpperLeft().getX() + 1,
                            (int) this.rectangle.getUpperLeft().getY() + 1,
                            this.images.get(Integer.parseInt(this.countHits) - 1));
                }
            }
        } else { // nothing just draw the defualt color
            surface.setColor(this.color);
            surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                    (int) this.rectangle.getUpperLeft().getY(), (int) this.rectangle.getWidth(),
                    (int) this.rectangle.getHeight());
        }
        surface.setColor(Color.black);
    }

    /**
     * get the shape of this block.
     * @return this.rectangle -- rectangle of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * now we know that ball hit this block so we need to check which velocity we
     * need to update. in addition we update the text on this block.
     * @param collisionPoint  --point collision that the ball hit on the block.
     * @param currentVelocity --the velocity of the ball that collision.
     * @param hitter          --the ball that hit this block.
     * @return updateVel -- updated velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        /*
         * all this is distance of all the line , and distance of start line until the
         * collision plus distance collision point to the end. i make variable for esey
         * Visibility conditions. culculate every distance and check by Triangle
         * inequality law
         */
        double a = this.getCollisionRectangle().getLeft().start()
                .distance(this.getCollisionRectangle().getLeft().end());
        double b = (this.getCollisionRectangle().getLeft()).start().distance(collisionPoint)
                + (this.getCollisionRectangle().getLeft()).end().distance(collisionPoint);
        double c = (this.getCollisionRectangle().getRight()).start()
                .distance((this.getCollisionRectangle().getRight()).end());
        double d = (this.getCollisionRectangle().getRight()).start().distance(collisionPoint)
                + (this.getCollisionRectangle().getRight()).end().distance(collisionPoint);
        double e = (this.getCollisionRectangle().getUp()).start()
                .distance((this.getCollisionRectangle().getUp()).end());
        double f = (this.getCollisionRectangle().getUp()).start().distance(collisionPoint)
                + (this.getCollisionRectangle().getUp()).end().distance(collisionPoint);
        double g = ((this.getCollisionRectangle().getDown()).start()
                .distance((this.getCollisionRectangle().getDown()).end()));
        double h = (this.getCollisionRectangle().getDown()).start().distance(collisionPoint)
                + (this.getCollisionRectangle().getDown()).end().distance(collisionPoint);
        // check if its Vertices before the regular collision
        if ((((this.getCollisionRectangle().getLeft()).start()).equals(collisionPoint))
                || ((this.getCollisionRectangle().getRight()).start()).equals(collisionPoint)
                || ((this.getCollisionRectangle().getRight()).end()).equals(collisionPoint)
                || ((this.getCollisionRectangle().getLeft()).end()).equals(collisionPoint)) {
            Velocity updateVel = new Velocity(-1 * currentVelocity.getDX(), -1 * currentVelocity.getDY());
            this.notifyHit(hitter);
            setNewText(this.countHits); // update the text on the block

            return updateVel;
        }
        // update the velocity depend the direction of the velocity
        if (Math.abs(a - b) <= 0.0001 || Math.abs(c - d) <= 0.0001) {
            Velocity updateVel = new Velocity(-1 * currentVelocity.getDX(), currentVelocity.getDY());
            this.notifyHit(hitter);
            setNewText(this.countHits); // update the text on the block

            return updateVel;

        }
        if (Math.abs(e - f) <= 0.0001 || Math.abs(g - h) <= 0.0001) {
            Velocity updateVel = new Velocity(currentVelocity.getDX(), -1 * currentVelocity.getDY());
            this.notifyHit(hitter);
            setNewText(this.countHits); // update the text on the block

            return updateVel;

        }
        this.notifyHit(hitter);
        setNewText(this.countHits); // update the text on the block

        return currentVelocity;
    }

    /**
     * run all listeners and notify all of them about the hitting.
     * @param hitter -- the ball that hit on the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * update the text on the text by the hits of the ball(until X).
     * @param s --the text on this block.
     */
    public void setNewText(String s) {
        if (this.countHits.equals("1")) {
            this.countHits = "0";
            return;
        }
        if (!this.countHits.equals("X")) {
            Integer x = Integer.parseInt(s);
            x--;
            this.countHits = Integer.toString(x);
        }
    }

    /**
     * the text of the blcok.
     * @return this.countHits --the text of the blcok.
     */
    public String getHitPoints() {
        return this.countHits;
    }

    /**
     * add to the game this block, its meaning to add to 2 arrylist.
     * @param g --the game object.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove the block from the game.
     * @param game - the function gets the level.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     * @param hl - the function gets listeners.HitListener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - the function gets listeners.HitListener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        // If their is no king of object throw exception.
        try {
            hitListeners.remove(hl);
        } catch (Exception e) {
            System.out.println("Their is no such kind of object in my list.");
        }
    }
}