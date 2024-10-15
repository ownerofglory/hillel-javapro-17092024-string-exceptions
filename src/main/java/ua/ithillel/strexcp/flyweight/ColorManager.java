package ua.ithillel.strexcp.flyweight;

import java.util.Map;
import java.util.HashMap;

// ColorPool
public class ColorManager {
    private static Map<String, Color> colors = new HashMap<>();

    public Color createColor(int red, int green, int blue) {
        String colorKey = String.format("%d-%d-%d", red, green, blue);

        Color color = colors.get(colorKey);
        if (color == null) {
            color = new Color(red, green, blue);
            colors.put(colorKey, color);
        }

        return color;
    }
}
