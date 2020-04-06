package levels;

import java.awt.Color;
import biuoop.DrawSurface;
import game.Sprite;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-21
 */
public class ThirdSreenGui implements Sprite {

    /**
     * constructor ThirdSreenGui empty.
     */
    public ThirdSreenGui() {

    }
    /**
     * draw the back ground of level 4.
     * @param d -- DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN.darker().darker());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.DARK_GRAY.darker());
        d.fillRectangle(95, 400, 125, 300);
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(145, 350, 35, 50);
        d.setColor(Color.DARK_GRAY.brighter());
        d.fillRectangle(157, 250, 10, 100);
        d.setColor(Color.orange.darker());
        d.fillCircle(161, 235, 15);
        d.setColor(Color.red.brighter());
        d.fillCircle(161, 235, 10);
        d.setColor(Color.white.darker());
        d.fillCircle(161, 235, 5);
        d.setColor(Color.white);
        int[] index = {0, 0, 0, 0, 0};
        for (int i = 0; i < 30; i++) {
            if (i < 6) {
                d.fillRectangle(104 + index[0] * 20, 410, 10, 30);
                index[0]++;
                continue;
            }
            if (i < 12) {
                d.fillRectangle(104 + index[1] * 20, 450, 10, 30);
                index[1]++;
                continue;
            }
            if (i < 18) {
                d.fillRectangle(104 + index[2] * 20, 490, 10, 30);
                index[2]++;
                continue;
            }
            if (i < 24) {
                d.fillRectangle(104 + index[3] * 20, 530, 10, 30);
                index[3]++;
                continue;
            }
            if (i < 30) {
                d.fillRectangle(104 + index[4] * 20, 570, 10, 30);
                index[4]++;
                continue;
            }
        }
    }
    /**
     * nothing here.
     */
    @Override
    public void timePassed() {

    }

}
