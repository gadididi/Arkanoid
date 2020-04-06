package levelfromfile;

import collision.Block;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     * @param xpos -- start x point.
     * @param ypos -- start y point.
     * @return Block -- new block.
     */
    Block create(int xpos, int ypos);

    /**
     * getter of Width block.
     * @return Width -- Width block.
     */
    Integer getWidth();
}
