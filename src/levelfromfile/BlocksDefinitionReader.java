package levelfromfile;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class BlocksDefinitionReader {

    /**
     * create factory for create block in the future.
     * @param reader -- reader for file.
     * @return BlocksFromSymbolsFactory -- factory for blocks.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Map<String, Integer> spacerWidths = new TreeMap<>();
        Map<String, BlockCreator> blockCreators = null;
        Map<String, String> defultThings = null;
        String line;
        BufferedReader bufferReader = new BufferedReader(reader);
        try {
            while ((line = bufferReader.readLine()) != null) {
                String defult = null;
                if (line.contains("default values for blocks")) {
                    line = bufferReader.readLine();
                    defult = new String(line);
                    defultThings = separeteTheDefValue(defult);

                }
                if (line.contains("# block definitions")) {
                    blockCreators = createCreator(bufferReader, line, defultThings);
                }
                if (line.contains("sdef")) {
                    spacerWidths = createSpace(bufferReader, line);
                }
            }
        } catch (FileNotFoundException ex) {
            // handle the FileNotFoundException
            System.err.println("Unable to find file");
        } catch (IOException e) {
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }

    /**
     * create map for kind of space .
     * @param bufferReader -- reader .
     * @param line -- the line of dictionary of spaces.
     * @return space -- maps of spaces.
     */
    private static Map<String, Integer> createSpace(BufferedReader bufferReader, String line) {
        Map<String, Integer> space = new TreeMap<>();
        try {
            while (line != null) {
                if (line.startsWith("sdef")) {
                    String value = line.substring(line.indexOf(" ") + 1);
                    String symbol = value.substring(7, 8);
                    Integer val = Integer.parseInt(value.substring(15));
                    space.put(symbol, val);
                }
                line = bufferReader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
        }
        return space;
    }

    /**
     * add the missing things for the creators of blocks .
     * @param defultThings -- maps of default things .
     * @param blockCreators -- blockCreators that maybe miss things.
     */
    private static void chekIfSomeThingMiss(Map<String, String> defultThings, CreatingOfBlock blockCreators) {
        if (defultThings.containsKey("height")) {
            blockCreators.setHeight(Integer.parseInt(defultThings.get("height")));
        }
        if (defultThings.containsKey("width")) {
            blockCreators.setWidth(Integer.parseInt(defultThings.get("width")));
        }
        if (defultThings.containsKey("hit_points")) {
            if (blockCreators.getHitPoints() == null) {
                blockCreators.setHitPoints((Integer.parseInt(defultThings.get("hit_points"))));
            }
        }
        if (defultThings.containsKey("stroke")) {
            String toFix = defultThings.get("stroke");
            String fix = toFix.substring(toFix.indexOf("(") + 1, toFix.indexOf(")"));
            blockCreators.setStroke(new ColorsParser().colorFromString(fix));
        }
    }

    /**
     * createCreator- run all the lines and create step by step and create the creator for block.
     * @param bufferReader -- reader .
     * @param line -- the line we read.
     * @param defultThings -- maps of default things.
     * @return blockCreators -- map of creators.
     */
    private static Map<String, BlockCreator> createCreator(BufferedReader bufferReader, String line,
            Map<String, String> defultThings) {
        Map<String, BlockCreator> blockCreators = new TreeMap<>();
            try {
                line = bufferReader.readLine();
                while (!(line.contains("# spacers definitions"))) {
                    if (line.startsWith("bdef")) {
                        CreatingOfBlock creator = new CreatingOfBlock();
                        String value = line.substring(line.indexOf(" ") + 1);
                        String[] parameters = value.split(" ");
                        List<java.awt.Color> colorList = new ArrayList<>();
                        List<Image> imageList = new ArrayList<>();
                        for (String parameter : parameters) {

                            if (parameter.contains("hit_points")) {
                                creator.setHitPoints(Integer.parseInt(
                                        parameter.substring(parameter.indexOf(":") + 1)));
                            }
                            //check the fill for blocks
                            if (parameter.substring(0, 4).equals("fill")) {
                                if (colorGetter((parameter.substring(parameter.indexOf("(") + 1,
                                        parameter.indexOf(")")))) != null) {
                                    colorList.add(colorGetter(
                                            (parameter.substring(
                                                    parameter.indexOf("(") + 1, parameter.indexOf(")")))));
                                }
                                if (parameter.contains("RGB")) {
                                    String fixed = parameter.substring(parameter.indexOf("("));
                                    colorList.add(new ColorsParser().colorFromString(fixed));
                                }
                                if (parameter.contains("jpg")) {
                                    BufferedImage img;
                                    try {
                                        String path = new ImageParser().imageFromString(parameter);
                                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
                                        img = ImageIO.read(is);
                                        imageList.add(img);
                                    } catch (IOException e) {
                                        System.err.println("Failed reading image");
                                        e.printStackTrace(System.err);
                                    }
                                }
                            }
                            if (parameter.contains("width")) {
                                creator.setWidth(Integer.parseInt(
                                        parameter.substring(parameter.indexOf(":") + 1)));
                            }
                            if (parameter.contains("height")) {
                                creator.setHeight((Integer.parseInt(
                                        parameter.substring(parameter.indexOf(":") + 1))));
                            }
                            if (parameter.contains("stroke")) {
                                if (colorGetter(
                                        (parameter.substring(
                                                parameter.indexOf("("), parameter.indexOf(")")))) != null) {
                                    creator.setStroke(colorGetter(
                                            (parameter.substring(
                                                    parameter.indexOf("("), parameter.indexOf(")")))));
                                } else {
                                    creator.setStroke(new ColorsParser().colorFromString(parameter));
                                }
                            }
                        }
                        creator.setColor(colorList); //set the list of color in creator
                        creator.setImageList(imageList);
                        if (defultThings != null) {
                            chekIfSomeThingMiss(defultThings, creator);
                        }
                        blockCreators.put(line.substring(line.indexOf(":") + 1, 13), creator);
                    }
                    line = bufferReader.readLine();
                }
            } catch (NumberFormatException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        return blockCreators;
    }

    /**
     * parse the line of defualt thing that block need to get.
     * @param defultValue -- string of  defualt Value of blocks.
     * @return defVal -- map of defVal for blocks.
     */
    public static Map<String, String> separeteTheDefValue(String defultValue) {
        Map<String, String> defVal = new HashMap<>();
        String fixed = defultValue.substring(defultValue.indexOf(" "));
        String[] def = fixed.split(" ");
        for (String stringOfDefult : def) {
            if (stringOfDefult.contains(":")) {
                defVal.put(stringOfDefult.substring(0, stringOfDefult.indexOf(":")),
                        stringOfDefult.substring(stringOfDefult.indexOf(":") + 1));
            }
        }
        return defVal;
    }

    /**
     * Getter.
     * @param stroke - the function gets the outline of the block.
     * @return - returns the specific color from map.
     */
    public static Color colorGetter(String stroke) {
        Map<String, Color> map = new TreeMap<String, Color>();
        map.put("yellow", Color.yellow);
        map.put("red", Color.red);
        map.put("black", Color.black);
        map.put("blue", Color.blue);
        map.put("cyan", Color.cyan);
        map.put("gray", Color.gray);
        map.put("lightGray", Color.lightGray);
        map.put("green", Color.green);
        map.put("orange", Color.orange);
        map.put("pink", Color.pink);
        map.put("white", Color.white);
        if (map.containsKey(stroke)) {
            return map.get(stroke);
        }
        return null;
    }
}
