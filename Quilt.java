package Goblins;

import java.awt.*;
import javax.swing.JFrame;
import java.util.*;

/**
 * Handles the creating of a JFrame and a Canvas for drawing on.
 *
 * @author Matthew Jennings
 */

public class Quilt extends JFrame {

    public static MyCanvas canvas;

    public Quilt() {
        canvas = new MyCanvas();
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public MyCanvas getCanvas() {
        return canvas;
    }

    public class MyCanvas extends Canvas {
        public ArrayList<ColouredRectangle> rects;

        public MyCanvas() {
            rects = new ArrayList<ColouredRectangle>();
        }

        public void addRect(Rectangle r, Color color) {
            this.rects.add(new ColouredRectangle(r, color));
        }

        public void paint(Graphics g) {
            for (ColouredRectangle r : rects) {
                g.setColor(r.color);
                g.drawRect(r.rec.x, r.rec.y, r.rec.width, r.rec.height);
                g.fillRect(r.rec.x, r.rec.y, r.rec.width, r.rec.height);
            }
        }

        /**
         * Defines a coloured Rectangle, just an object that possesses both a rectangle
         * and a colour.
         */
        public class ColouredRectangle {
            Rectangle rec;
            Color color;

            public ColouredRectangle(Rectangle r, Color c) {
                this.rec = r;
                this.color = c;
            }
        }
    }
}
