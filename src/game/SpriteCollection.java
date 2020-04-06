package game;

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-03-31 (the version of the package this class was first added to)
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * constructor.
     */
    public SpriteCollection() {
        List<Sprite> listOfSprite = new ArrayList<Sprite>();
        this.sprites = listOfSprite;
    }

    /**
     * add object to array list of the Sprite of the game.
     * @param s -- Sprite array list.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * remove object from the array list of the Sprite of the game.
     * @param s -- Sprite array list.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites..
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites..
     * @param d -- Surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite spr : this.sprites) {
            spr.drawOn(d);
        }
    }
}
