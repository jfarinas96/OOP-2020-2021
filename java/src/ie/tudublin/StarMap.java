package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet {
    
    ArrayList<Star> stars = new ArrayList<Star>();

    int startStar = -1;
    int endStar = -1;

    void loadStars() {
        // import processing.data
        Table table = loadTable("HabHYG15ly.csv", "header");

        // instantiate
        for (TableRow row:table.rows()) {
            Star s = new Star(row);
            stars.add(s);
        }
    }

    void printStars() {
        for (Star s:stars) {
            println(s);
        }
    }

    void drawGrid() {

        float border = 0.1f * width;
        textAlign(CENTER, CENTER);

        for (int i = -5 ; i <= 5 ; i++) {
            float x = map(i, -5, 5, border, width - border);
            float y = map(i, -5, 5, border, height - border);
            stroke(0, 0, 255);
            line(x, border, x, height - border);
            line(border, y, width - border, y);
            fill(255);
            text(i, x, border / 2);
            text(i, border / 2, y);
        }
    }

    public void drawStars() {
        for (Star s:stars) {
            s.render(this);
        }
    }

    public void mouseClicked()
    {
        float border = 0.1f * width;

        for (int i = 0 ; i < stars.size() ; i++) {
            Star s = stars.get(i);
            float x = map(s.getxG(), -5, 5, border, width - border);
            float y = map(s.getyG(), -5, 5, border, height - border);
            if (dist(mouseX, mouseY, x, y) < s.getAbsMag()) {
                println(s.getDisplayName());
                break;
            }
        }
    }

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        colorMode(RGB);
        loadStars();
        printStars();
    }

    public void draw() {
        background(0);
        drawGrid();
        drawStars();
    }
}