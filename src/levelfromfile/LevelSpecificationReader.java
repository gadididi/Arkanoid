package levelfromfile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import ball.Velocity;
import collision.Block;
import levels.LevelInformation;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class LevelSpecificationReader {
    private List<LevelInformation> levels;

    /**
     * Constructor.
     */
    public LevelSpecificationReader() {
        this.levels = new ArrayList<>();
    }

    /**
     * The function build list of LevelInformation and return it.
     * @param reader - buffer reader for the level set.
     * @return levels - returns list of LevelInformation.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        String line;
        BufferedReader bufferReader = new BufferedReader(reader);
        try {

            while ((line = bufferReader.readLine()) != null) {
                List<String> level = new ArrayList<>();
                level.add(line);
                while (!line.contains("END_LEVEL")) {
                    line = bufferReader.readLine();
                    level.add(line);
                }
                level.add(line);
                LevelFromFile l = buildLevel(level); //send to build the level in another method.
                if (l != null) {
                    levels.add(l);
                }
                level.clear(); //clean the list of string to another level.
            }
        } catch (FileNotFoundException ex) {
            // handle the FileNotFoundException
            System.err.println("Unable to find file");
        } catch (IOException e) {
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
        }
        return this.levels;
    }

    /**
     * The function build one level ,step by step run the list of string that get as argument.
     * @param level - list of string that contain all the level details.
     * @return levelFrom - returns LevelFromFile that implement LevelInformation.
     */
    public LevelFromFile buildLevel(List<String> level) {
        LevelFromFile levelFrom = new LevelFromFile();
        int indicator = 0;
        Integer startX = null;
        Integer startY = null;
        for (int i = 0; i < level.size(); i++) {
            if (level.get(i).contains("level_name")) {
                levelFrom.setName(sepaetor(level.get(i)));
                indicator = indicator + 1;
            }
            if (level.get(i).contains("paddle_speed")) {
                indicator = indicator + 1;
                levelFrom.setPaddleSpeed(sepaetor(level.get(i)));
            }
            if (level.get(i).contains("paddle_width")) {
                indicator = indicator + 1;
                levelFrom.setPaddleWidth((sepaetor(level.get(i))));
            }
            if (level.get(i).contains("ball_velocities")) {
                levelFrom.setVelForBalls(sepaetorVelocity(level.get(i)));
                indicator = indicator + 1;
            }
            if (level.get(i).contains("row_height")) {
                levelFrom.setRowHeight((sepaetor(level.get(i))));
                indicator = indicator + 1;
            }
            if (level.get(i).contains("block_definitions")) {
                levelFrom.setDefOfBlock((sepaetor(level.get(i))));
                indicator = indicator + 1;
            }
            if (level.get(i).contains("blocks_start_x")) {
                startX = Integer.parseInt(sepaetor(level.get(i)));
                indicator = indicator + 1;
            }
            if (level.get(i).contains("blocks_start_y")) {
                startY = Integer.parseInt(sepaetor(level.get(i)));
                indicator = indicator + 1;
            }
            if (level.get(i).contains("background")) {
                String backGroundPath = sepaetorTheImg(level.get(i));
                if (level.get(i).contains("image")) {
                    levelFrom.setImg(backGroundPath);
                } else {
                    ColorsParser colorsParser = new ColorsParser();
                    levelFrom.setBackByColor(colorsParser.colorFromString(backGroundPath));
                }
                indicator = indicator + 1;
            }
        }
        if (indicator == 9) { //check if we get all what we need to continue the read.
            int indexSblock = 0;
            int indexEblock = 0;
            List<String> blocks = new ArrayList<>();
            for (int j = 0; j < level.size(); j++) {
                if (level.get(j).contains("START_BLOCKS")) {
                    indexSblock = j + 1;
                }
            }
            for (int k = indexSblock; k < level.size(); k++) {
                if (level.get(k).contains("END_BLOCKS")) {
                    indexEblock = k;
                    break;
                }
            }
            for (int c = indexSblock; c < indexEblock; c++) {
                blocks.add(level.get(c));
            }
            BufferedReader b = null;
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(
                        levelFrom.getDefOfBlock());
                InputStreamReader r = new InputStreamReader(is);
                b = new BufferedReader(r);
            } catch (Exception e) {
                System.err.println("Failed found def file of block");
                e.printStackTrace(System.err);
                return null;
            }
            // create the block factory and set the block for this level by it.
            BlocksFromSymbolsFactory factory = BlocksDefinitionReader.fromReader(b);
            levelFrom.setBlocks(bulidBloks(blocks, startX, startY, levelFrom.rowHeighting(), factory));
            return levelFrom;
        }
        return null;
    }

    /**
     * run all the block symbol (list string) and create them by the location.
     * @param blocks - list of string that sign the blocks.
     * @param startX - point to start create block x.
     * @param startY - point to start create block y.
     * @param rowHigh - row high between 2 row of blocks.
     * @param factory - the factory that tell us what is that symbol.
     * @return blocksForLevel - returns list of blocks for this level.
     */
    public List<Block> bulidBloks(List<String> blocks, Integer startX, Integer startY, int rowHigh,
            BlocksFromSymbolsFactory factory) {
        List<Block> blocksForLevel = new ArrayList<>();
        int howManyBlocks = 0;
        int finalX = startX;
        int finalY = startY;
        for (int i = 0; i < blocks.size(); i++) {
            int j = 0;
            int spaceX = 0;
            String line = blocks.get(i);
            for (char symbol : line.toCharArray()) {
                String simboly = String.valueOf(symbol);
                if (factory.isSpaceSymbol(simboly)) { // not block
                    spaceX = spaceX + factory.getSpaceWidth(simboly);
                    finalX = spaceX;
                }
                if (factory.isBlockSymbol(simboly)) { // is block
                    blocksForLevel.add(factory.getBlock(simboly, finalX + j, finalY));
                    j = j + factory.getBlockCteator().get(simboly).getWidth();
                    howManyBlocks = howManyBlocks + 1;
                }
            }
            finalX = startX;
            finalY = finalY + rowHigh;
            spaceX = 0;
        }
        return blocksForLevel;
    }

    /**
     * sepaetor line.
     * @param line - list of string that sign the blocks.
     * @return String - new string.
     */
    public String sepaetor(String line) {
        int index = line.indexOf(":");
        return new String(line.substring(index + 1));
    }
    /**
     * sepaetor line helper.
     * @param line - list of string that sign the blocks.
     * @return String - new string.
     */
    public String sepaetorTheImg(String line) {
        int indexStart = line.indexOf("(");
        int indexEnd = line.indexOf(")");
        return new String(line.substring(indexStart + 1, indexEnd));
    }

    /**
     * sepaetor line helper for velocity line.
     * @param line - list of string that sign the blocks.
     * @return velocities - list of velocity.
     */
    public List<Velocity> sepaetorVelocity(String line) {
        String fixLine = sepaetor(line);
        String[] separated = fixLine.split(" ");
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < separated.length; i++) {
            velocities.add(
                    Velocity.fromAngleAndSpeed(Double.valueOf(
                            separated[i].substring(0, separated[i].indexOf(","))),
                            Double.valueOf(separated[i].substring(
                                    separated[i].indexOf(",") + 1))));
        }
        return velocities;
    }
}
