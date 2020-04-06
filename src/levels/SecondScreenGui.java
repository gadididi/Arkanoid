package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import game.Sprite;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class SecondScreenGui implements Sprite {

    /**
     * constructor SecondScreenGui empty.
     */
    public SecondScreenGui() {

    }
    /**
     * draw the back ground of level 4.
     * @param d -- DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.orange.darker());
        for (int i = 0; i < 8; i++) {
            d.drawLine(150, 150, 150 - 15 * i, 250);
        }
        for (int i = 0; i < 40; i++) {
            d.drawLine(150, 150, 150 + 15 * i, 250);
        }
        d.setColor(Color.orange.darker());
        d.fillCircle(150, 150, 60);
        d.setColor(Color.yellow.darker());
        d.fillCircle(150, 150, 50);
        d.setColor(Color.yellow.brighter());
        d.fillCircle(150, 150, 40);
    }
    /**
     * nothing here.
     */
    @Override
    public void timePassed() {

    }

}
