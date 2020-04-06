package levelfromfile;

import java.util.Map;
import collision.Block;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * Constructor BlocksFromSymbolsFactory.
     * @param spacerWidths -- map of spacerWidths.
     * @param blockCreators -- map of blockCreators.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * @param s - the function gets a string to check in map.
     * @return - returns true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }

    /**
     * getter BlockCreator Map.
     * @return blockCreators - blockCreators map.
     */
    public Map<String, BlockCreator> getBlockCteator() {
        return this.blockCreators;
    }

    /**
     * @param s - the function gets a string to check in map.
     * @return - returns true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    /**
     * @param s - the function gets a string to map.
     * @param x - x position of block.
     * @param y - y position of block.
     * @return - Returns the block associated with the given block-symbol.
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    /**
     * @param s - the function gets a string to check in map.
     * @return - Returns the width in pixels associated with the given
     *         spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
