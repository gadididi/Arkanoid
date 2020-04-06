package collision;

import biuoop.DrawSurface;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-03-12 (the version of the package this class was first added to)
 */
public class Frame {
    private double up;
    private double down;
    private double left;
    private double right;
    private java.awt.Color frameColor;

    /**
     * constructor.
     * @param up    --the up frame.
     * @param down  --the down frame.
     * @param left  --the left frame.
     * @param right --the right frame.
     * @param color --the colour frame.
     */
    public Frame(double up, double down, double left, double right, java.awt.Color color) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.frameColor = color;
    }

    /**
     * geter of up of the frame.
     * @return this.up -- up of the frame.
     */
    public double getUp() {
        return this.up;
    }

    /**
     * geter of down of the frame.
     * @return this.down -- down of the frame.
     */
    public double getDown() {
        return this.down;
    }

    /**
     * geter of left of the frame.
     * @return this.left -- left of the frame.
     */
    public double getLeft() {
        return this.left;
    }

    /**
     * geter of right of the frame.
     * @return this.right -- right of the frame.
     */
    public double getRight() {
        return this.right;
    }

    /**
     * set colour to frame on the surface.
     */
    public void setColor() {
        java.util.Random rand = new java.util.Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        java.awt.Color randomColor = new java.awt.Color(r, g, b);
        this.frameColor = randomColor;
    }

    /**
     * set frame on the surface.
     * @param surface --the board that we create before.
     */
    public void drawForFrame(DrawSurface surface) {
        surface.setColor(this.frameColor);
        surface.fillRectangle((int) this.left, (int) this.up, (int) (this.right - this.left),
                (int) (this.down - this.up));
    }
}
