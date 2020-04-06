package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import game.Sprite;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class FirstScreenGui implements Sprite {

    /**
     * constructor FirstScreenGui empty.
     */
    public FirstScreenGui() {

    }

    /**
     * draw the back ground of level 1.
     * @param d -- DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black.darker());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 200, 75);
        d.drawCircle(400, 200, 100);
        d.drawCircle(400, 200, 125);
        d.drawLine(260, 200, 540, 200);
        d.drawLine(400, 10, 400, 340);
    }
    /**
     * nothing here.
     */
    @Override
    public void timePassed() {

    }
}
