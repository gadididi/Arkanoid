package collision;

import ball.Ball;
import game.GameLevel;
import tools.Counter;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-05
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor BlockRemover.
     * a BlockRemover is in charge of removing blocks from the game, as well as keeping Counter
     * of the number of blocks that remain.
     * @param game -- the object game of our game.
     * @param removedBlocks -- how many block there are in the game
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed.
     * from the game. Remember to remove this listener from the block that is being removed from the game.
     * @param beingHit -- the block the get hit from the hitter.
     * @param hitter -- the ball that hit on this block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints().equals("1")) {
            remainingBlocks.decrease(1);
            // Remove the block from the game.
            beingHit.removeFromGame(this.game);
            // Remove from the blocks.
            game.removeFromBlocks(beingHit);
        }
    }
}
