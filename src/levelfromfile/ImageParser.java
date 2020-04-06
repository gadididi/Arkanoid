package levelfromfile;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class ImageParser {
    /**
     * parse image definition and return the specified color.
     * @param s - gets a line from the file.
     * @return - string which will convert to image.
     */
    public String imageFromString(String s) {
        return s.split("\\(")[1].split("\\)")[0];
    }
}
