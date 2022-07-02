package Goblins;

import java.awt.*;

/**
 * Defines a Goblin square by it's center and scale.
 *
 */
public class GoblinSqr {

    double scale;
    int x;
    int y;
    Corner[] corners;

    // create a new Goblin Square, centered at x,y with a scale.
    public GoblinSqr(double scale, int x, int y) {
        this.scale = scale;
        this.x = x;
        this.y = y;
        this.corners = new Corner[4];
        this.corners[0] = new Corner((int) (x - 200 * scale), (int) (y - 200 * scale));
        this.corners[1] = new Corner((int) (x + 200 * scale), (int) (y - 200 * scale));
        this.corners[2] = new Corner((int) (x - 200 * scale), (int) (y + 200 * scale));
        this.corners[3] = new Corner((int) (x + 200 * scale), (int) (y + 200 * scale));
    }

    /**
     * Converts this goblin square into a usable rectangle.
     *
     * @return
     */
    public Rectangle convToRec() {
        return new Rectangle(corners[0].x, corners[0].y, corners[1].x - corners[0].x, corners[2].y - corners[0].y);
    }

    /**
     * Defines the corner position of a square.
     */
    public class Corner {
        int x;
        int y;

        public Corner(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return this.x + ", " + this.y;
        }
    }
}