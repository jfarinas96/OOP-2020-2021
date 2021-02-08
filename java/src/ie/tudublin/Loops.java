package ie.tudublin;

import processing.core.PApplet;

public class Loops extends PApplet {

    public void settings() {
        size(500, 500);
        cx = width / 2;
        cy = height / 2;        
    }

    int mode = 0;

    float cx;
    float cy;

    public void keyPressed() {
        // the value of mode will be the number of the 
        // number key pressed
        if (keyCode >= '0' && keyCode <= '9')
            mode = keyCode - '0';
    }

    public void setup() {
        colorMode(HSB);
    }

    public void draw() {
        background(0);
        switch (mode)
        {
            case 0:
                fill(50, 255, 255);
                if (mouseX < cx)
                {
                    rect(0, 0, cx, height);
                }
                else
                {
                    rect(cx, 0, cx, height);
                }
                break;

            case 1:
                fill(100, 255, 255);
                if (mouseX < cx && mouseY < cy)
                {
                    rect(0, 0, cx, cy);
                }
                else if (mouseX > cx && mouseY < cy)
                {
                    rect(cx, 0, cx, cy);
                }
                else if (mouseX < cx && mouseY > cy)
                {
                    rect(0, cy, cx, cy);
                }
                else
                {
                    rect(cx, cy, cx, cy);
                }
                break;
            
            case 2:
                if (mouseX < cx + 100 && mouseX > cx - 100 && mouseY < cy + 50 && mouseY > cy - 50)
                {
                    fill(150, 255, 255);
                }
                else
                {
                    fill(200, 255, 255);
                }
                rect(cx - 100, cy - 50, 200, 100);
                break;
            
            case 3:
                int numRects = 10;
                float w = width / (float) numRects;
                float cgap = 255 / (float) numRects;
                for (int i = 0; i < numRects; i++)
                {
                    fill(i * cgap, 255, 255);
                    rect(i * w, 0, w, height);
                }
                break;

            case 4:
                int numCircles = 10;
                float diameter = width / (float) numCircles;
                float circlegap = 255 / (float) numCircles;
                for (int i = 0; i < numCircles; i++)
                {
                    fill(i * circlegap, 255, 255);
                    ellipse((i * diameter) + (diameter / 2), height / 2, diameter, diameter);
                }
                break;
        }
    }
}
