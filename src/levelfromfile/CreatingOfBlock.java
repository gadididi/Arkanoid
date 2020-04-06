package levelfromfile;

import java.awt.Image;
import java.util.List;
import collision.Block;
import graphics.Point;
import graphics.Rectangle;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class CreatingOfBlock implements BlockCreator {
    private Integer width = null;
    private Integer height = null;
    private Integer hitPoints = null;
    private List<java.awt.Color> color = null;
    private java.awt.Color stroke = null;
    private List<Image> imageList = null;

    /**
     * empty constactor.
     */
    public CreatingOfBlock() {

    }

    /**
     * Setter.
     * @param widthOfBlock - the function gets width to set.
     */
    public void setWidth(int widthOfBlock) {
        this.width = widthOfBlock;
    }
    @Override
    public Integer getWidth() {
        return this.width;
    }
    /**
     * Setter.
     * @param heightOfBlock - the function gets height to set.
     */
    public void setHeight(int heightOfBlock) {
        this.height = heightOfBlock;
    }

    /**
     * Setter.
     * @param hitPointsOfBlock - the function gets hit points to set.
     */
    public void setHitPoints(int hitPointsOfBlock) {
        this.hitPoints = hitPointsOfBlock;
    }
    /**
     * getter.
     * @return hitPoints - hitPoints.
     */
    public Integer getHitPoints() {
        return this.hitPoints;
    }
    /**
     * getter height.
     * @return height - height.
     */
    public Integer getHeight() {
        return this.height;
    }
    /**
     * Setter.
     * @param colorOfBlock - the function gets list of colors to set.
     */
    public void setColor(List<java.awt.Color> colorOfBlock) {
        this.color = colorOfBlock;
    }

    /**
     * Setter.
     * @param imageListOfBlock - the function gets list of images to set.
     */
    public void setImageList(List<Image> imageListOfBlock) {
        this.imageList = imageListOfBlock;
    }

    /**
     * Setter.
     * @param strokeOfBlock - the function gets stroke to set.
     */
    public void setStroke(java.awt.Color strokeOfBlock) {
        this.stroke = strokeOfBlock;
    }

    /**
     * @param xpos - the function gets a specific x position for the block.
     * @param ypos - the function gets a specific y position for the block.
     * @return - Create a block at the specified location.
     */
    @Override
    public Block create(int xpos, int ypos) {
        Point upperLeft = new Point(xpos, ypos);
        Rectangle rectangle = new Rectangle(upperLeft, width, height);
        return new Block(rectangle, this.color, this.hitPoints, this.stroke, imageList);
    }

}
