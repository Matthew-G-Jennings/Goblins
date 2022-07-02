package Goblins;

import java.awt.*;
import java.util.*;

/**
 * Main app for generating quilt patterns
 *
 * @author Matthew Jennings
 */
public class App {

    /**
     * Reads 3 lines from stdin and generates a quilt based on their values.
     *
     * @param args Not used
     */
    public static void main(String[] args) {
        Quilt quilt = new Quilt();
        Scanner scanner = new Scanner(System.in);
        quilt.add(quilt.getCanvas());

        ArrayList<Double> scales = new ArrayList<Double>();
        ArrayList<Color> colours = new ArrayList<Color>();
        int depth = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScan = new Scanner(line);
            int[] colorcode = new int[3];
            try {
                scales.add(lineScan.nextDouble());
                for (int j = 0; j < 3 && lineScan.hasNext(); j++) {
                    colorcode[j] = lineScan.nextInt();
                }
                colours.add(new Color(colorcode[0], colorcode[1], colorcode[2]));
            } catch (Exception e) {
                System.err.println("Bad line: " + line);
            }
            depth++;
        }

        scales = rescale(scales);

        // first square, always 500,500 (center of canvas), variable scaling.
        Color color = colours.get(0);
        GoblinSqr square = new GoblinSqr(scales.get(0), 500, 500);
        Rectangle r = square.convToRec();
        quilt.getCanvas().addRect(r, color);
        ArrayList<GoblinSqr> currLayer = new ArrayList<GoblinSqr>();
        currLayer.add(square);

        for (int i = 1; i < depth; i++) {
            color = colours.get(i);
            ArrayList<GoblinSqr> nextLayer = new ArrayList<GoblinSqr>();
            for (GoblinSqr g : currLayer) {
                for (GoblinSqr.Corner c : g.corners) {
                    GoblinSqr gsquare = new GoblinSqr(scales.get(i), c.x, c.y);
                    nextLayer.add(gsquare);
                    Rectangle rec = gsquare.convToRec();
                    quilt.getCanvas().addRect(rec, color);
                }
            }
            currLayer = nextLayer;
        }
        quilt.revalidate();
        quilt.repaint();
    }

    /**
     * Given an array of scales, attempts to normalize to create a consistent
     * sizing.
     *
     * @param scales double[] of scales
     * @return double[] of normalized scales
     */
    public static ArrayList<Double> rescale(ArrayList<Double> scales) {
        double size = 0;
        double scalefactor = 1.0;
        ArrayList<Double> result = new ArrayList<Double>();
        for (double d : scales) {
            size += d * scalefactor * 200;
        }
        System.out.println(size);
        scalefactor = 400 / size;
        System.out.println(size * scalefactor);
        for (int i = 0; i < scales.size(); i++) {
            result.add(scales.get(i) * scalefactor);
        }
        return result;
    }
}
