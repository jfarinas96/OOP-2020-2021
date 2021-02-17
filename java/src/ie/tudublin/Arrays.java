package ie.tudublin;

import processing.core.PApplet;

public class Arrays extends PApplet {

    // This is how the map function works!
    public float map1(float from, float start1, float stop1, float start2, float stop2) {
        float range1 = stop1 - start1;
        float range2 = stop2 - start2;
        float howFar = from - start1;

        return start2 + (howFar / range1) * range2;
    }

    // This is a demo of the map function
    public void drawGrid() {
        stroke(0, 255, 0);
        float border = width * 0.1f;
        textAlign(CENTER, CENTER);
        for (int i = -5; i <= 5; i++) {
            float x = map(i, -5, 5, border, width - border);
            line(x, border, x, height - border);
            line(border, x, width - border, x);
            fill(255);
            text(i, x, border * 0.5f);
            text(i, border * 0.5f, x);
        }
    }
    
    // Return the sum of all the elements in an array
    float sum(float[] array) {
        float sum = 0;
        for (float r : array) {
            sum += r;
        }
        return sum;
    }

    public void settings() {
        size(500, 500);
        cx = width / 2;
        cy = height / 2; 

        // Testing the map function
        float f = map1(2, 0, 10, 0, width);
        println(f); // Should print 100

        f = map1(9, 0, 1, 0, 10);
        println(f); // Should print 90

        f = map1(250, 200, 300, 400, 500);
        println(f); // Should print 450

        f = map1(5, 0, 10, 1000, 2000);
        println(f); // Should print 1500

    }

    int mode = 0;
    float cx, cy;

    float[] rainfall = { 45, 37, 55, 27, 38, 50, 79, 48, 104, 31, 100, 58 };
    String[] months = { "Jan", "Feb", "March", "April", "May", "June", "July", "August", "Sept", "Oct", "Nov", "Dec" };
    float[] arr = new float[100]; // 100 float array
    float sum = 0;

    public void keyPressed() {
        // the value of mode will be the number of the
        // number key pressed
        if (keyCode >= '0' && keyCode <= '9')
            mode = keyCode - '0';
    }

    public void setup() {
        colorMode(RGB);

        // Iterating over an array in Java
        for (int i = 0; i < rainfall.length; i++) {
            println(months[i] + "\t" + rainfall[i]);
        }

        // Enhanced for loop
        for (float f : rainfall) {
            println(f);
        }

        // What month had the most and least rainfall??
        // What is the total rainfall?
        // What is the average rainfall??

        int minIndex = 0;
        int maxIndex = 0;
        sum = 0;
        float average = 0;
        for (int i = 0; i < rainfall.length; i++) {
            if (rainfall[i] < rainfall[minIndex]) {
                minIndex = i;
            }
            if (rainfall[i] > rainfall[maxIndex]) {
                maxIndex = i;
            }
            sum += rainfall[i];
        }

        average = sum / (float) rainfall.length;
        println("Least rainfall was in " + months[minIndex] + " with " + rainfall[minIndex]);
        println("Most rainfall was in " + months[maxIndex] + " with " + rainfall[maxIndex]);
        println("Average rainfall: " + average);

        // rect(x, y, w, -h);

        // Draw a bar chart of the rainfall!!
        // Use the map function

        /*
        colorMode(HSB);
        float w = width / (float) rainfall.length;
        for (int i = 0; i < rainfall.length; i++) {
            noStroke();
            fill(random(255), 255, 255);
            float x = map(i, 0, rainfall.length, 0, width);
            rect(x, height, w, -rainfall[i]);
        }
        */
    }


    public void draw() {
        background(0);
        stroke(0, 0, 100);
        switch (mode) {
            case 0: {
                // Bar chart
                colorMode(HSB);
                float border = width * 0.1f;
                float w = (width - (2 * border)) / (float) rainfall.length;
                float cgap = 255 / (float) rainfall.length;
                line(border, height - border, width - border, height - border);
                line(border, border, border, height - border);
                textAlign(CENTER, CENTER);
                fill(0, 0, 100);
                text("Rainfall Bar Chart", width / 2, border * 0.5f);

                for (int i = 0; i <= rainfall.length; i++) {
                    float x = map(i, 0, rainfall.length,  height - border, border);
                    line(border, x, border * 0.8f, x);
                    text(i * 10, border * 0.5f, x);
                }
                
                for (int i = 0; i < rainfall.length; i++) {
                    fill(cgap * i, 255, 255);
                    float x = map(i, 0, rainfall.length, border, width - border);
                    float y = map(rainfall[i], 0, 120, border, height - border);
                    rect(x, height - border, w, -(y - border));
                    fill(0, 0, 100);
                    text(months[i], x + (w / 2), height - (border * 0.5f));
                }
                break;
            }
            case 1: {
                // Trend line
                float border = width * 0.1f;
                float w = (width - (2 * border)) / (float) rainfall.length;
                line(border, height - border, width - border, height - border);
                line(border, border, border, height - border);
                textAlign(CENTER, CENTER);
                fill(0, 0, 100);
                text("Rainfall Trend Chart", width / 2, border * 0.5f);

                for (int i = 0; i <= rainfall.length; i++) {
                    float x = map(i, 0, rainfall.length,  height - border, border);
                    line(border, x, border * 0.8f, x);
                    text(i * 10, border * 0.5f, x);
                }
                
                for (int i = 0; i < rainfall.length; i++) {
                    float x = map(i, 0, rainfall.length, border, width - border);
                    text(months[i], x + (w / 2), height - (border * 0.5f));
                }

                for (int i = 0; i < rainfall.length - 1; i++) {
                    float x1 = map(i, 0, rainfall.length, border, width - border);
                    float y1 = map(rainfall[i], 0, 120, height - border, border);
                    float x2 = map(i + 1, 0, rainfall.length, border, width - border);
                    float y2 = map(rainfall[i + 1], 0, 120, height - border, border);
                    line(x1 + (w / 2), y1, x2 + (w / 2), y2);
                }
                break;
            }
            case 2: {
                // Pie chart
                colorMode(HSB);
                float border = width * 0.1f;
                float diameter = width - (4 * border);
                float cgap = 255 / (float) rainfall.length;
                float previous = 0;
                float next = 0;
                textAlign(CENTER, CENTER);
                fill(0, 0, 100);
                text("Rainfall Pie Chart", width / 2, border * 0.5f);
                
                for (int i = 0; i < rainfall.length; i++) {
                    float percent = (float) sum / rainfall[i];
                    float theta = TWO_PI / percent;
                    next += theta;
                    fill(cgap * i, 255, 255);
                    arc(width / 2, height / 2, diameter, diameter, previous, next);
                    previous += theta;

                    /*
                    float x = sin(theta * i) * (diameter / 2);
                    float y = cos(theta * i) * (diameter / 2);
                    fill(0, 0, 100);
                    text(months[i], cx + x, cy + y);
                    */
                }
                /*
                stroke(255);
                for(int i = 1 ; i <= sides ; i ++)
                {
                    float x1 = sin(theta * (i - 1)) * radius;
                    float y1 = cos(theta * (i - 1)) * radius;
                    float x2 = sin(theta * i) * radius;
                    float y2 = cos(theta * i) * radius;
                    line(cx + x1, cy + y1, cx + x2, cy + y2);
                }

                colorMode(HSB);
                float border = width * 0.1f;
                float w = (width - (2 * border)) / (float) rainfall.length;
                float cgap = 255 / (float) rainfall.length;
                line(border, height - border, width - border, height - border);
                line(border, border, border, height - border);
                textAlign(CENTER, CENTER);
                fill(0, 0, 100);
                text("Rainfall Bar Chart", width / 2, border * 0.5f);

                for (int i = 0; i <= rainfall.length; i++) {
                    float x = map(i, 0, rainfall.length,  height - border, border);
                    line(border, x, border * 0.8f, x);
                    text(i * 10, border * 0.5f, x);
                }
                
                for (int i = 0; i < rainfall.length; i++) {
                    fill(cgap * i, 255, 255);
                    float x = map(i, 0, rainfall.length, border, width - border);
                    float y = map(rainfall[i], 0, 120, border, height - border);
                    rect(x, height - border, w, -(y - border));
                    fill(0, 0, 100);
                    text(months[i], x + (w / 2), height - (border * 0.5f));
                }*/
                break;
            }
        }
    }
}
