package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import game.Sprite;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class FourthScreenGui implements Sprite {

    /**
     * constructor FourthScreenGui empty.
     */
    public FourthScreenGui() {

    }

    /**
     * draw the back ground of level 4.
     * @param d -- DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.cyan.brighter().brighter());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white.brighter());
        for (int i = 0; i < 10; i++) {
            d.drawLine(115 + i * 10, 350, 145 + i * 10, 600);
        }
        for (int i = 0; i < 10; i++) {
            d.drawLine(620 + i * 10, 450, 650 + i * 10, 600);
        }
        d.setColor(Color.gray.brighter());
        d.fillCircle(175, 350, 30);
        d.setColor(Color.gray.brighter());
        d.fillCircle(195, 360, 30);
        d.setColor(Color.gray.brighter());
        d.fillCircle(115, 360, 30);
        d.setColor(Color.gray.brighter());
        d.fillCircle(150, 375, 35);
        d.setColor(Color.gray.brighter());
        d.fillCircle(140, 340, 30);
        d.setColor(Color.gray.brighter());
        d.fillCircle(675, 450, 30);
        d.setColor(Color.gray.brighter());
        d.fillCircle(695, 460, 30);
        d.setColor(Color.gray.brighter());
        d.fillCircle(615, 460, 30);
        d.setColor(Color.gray.brighter());
        d.fillCircle(650, 475, 35);
        d.setColor(Color.gray.brighter());
        d.fillCircle(640, 440, 30);

    }

    /**
     * nothing here.
     */
    @Override
    public void timePassed() {

    }

}
