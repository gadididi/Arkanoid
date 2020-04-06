package levelfromfile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-06-13
 */
public class ColorsParser {


    /**
     * get string and return color by RBG.
     * @param s -- string that reflect the specific color.
     * @return Color -- color after the parse.
     */
    public java.awt.Color colorFromString(String s) {
        List<Integer> colorList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            colorList.add(Integer.valueOf(s.substring(matcher.start(), matcher.end())));
        }
        if (colorList.size() == 0) {
            Pattern patternColor = Pattern.compile("\\w+");
            Matcher matcherColor = patternColor.matcher(s);
            while (matcherColor.find()) {
                String str = s.substring(matcherColor.start(), matcherColor.end());
                if (!str.equals("color")) {
                    Color color = BlocksDefinitionReader.colorGetter(str);
                    return color;
                }
            }
        }
        return new Color(colorList.get(0), colorList.get(1), colorList.get(2));

    }
}
