package ie.tudublin;

import org.graalvm.compiler.lir.aarch64.AArch64Compare.FloatCompareOp;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet {

    Minim minim; // Connect to minim
    AudioInput ai; // How to connect to mic
    AudioPlayer ap;
    AudioBuffer ab; // Samples

    float[] lerpedBuffer;

    public void settings() {
        size(512, 512);
        // fullScreen(P3D, SPAN); // Try this for full screen multiple monitor support :-) Be careful of exceptions!
    }

    float y = 200;
    float lerpedY = y;

    int which = 0;

    public void setup() {
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        ap = minim.loadFile("heroplanet.mp3", width);
        //ab = ai.mix; // Connect the buffer to the mic
        ab = ap.mix; // Connect the buffer to the mp3 file
        colorMode(HSB);
        lerpedBuffer = new float[width];

    }

    public void keyPressed() {
        if (keyCode >= '0' && keyCode <= '5') {
            which = keyCode - '0';
        }
        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
    }

    float lerpedAverage = 0;

    public void draw() {
        background(0);
        stroke(255);
        float halfHeight = height / 2;
        float average = 0;
        float sum = 0;

        // Calculate the average of the buffer
        for (int i = 0; i < ab.size(); i ++)
        {
            sum += abs(ab.get(i));
        }
        average = sum / ab.size();
        // Move lerpedAverage 10% closer to average every frame
        lerpedAverage = lerp(lerpedAverage, average, 0.1f);

        switch (which)
        {
            case 0:
            {
                // Iterate over all the elements in the audio buffer
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, halfHeight + lerpedBuffer[i] * halfHeight * 4, i);
                }

                // See the difference lerping makes? It smooths out the jitteryness of average, so the visual looks smoother
                // ellipse(width / 4, 100, average * 500, average * 500);
                // ellipse(width / 2, 100, 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));
        
                // This is another example of how lerping works
                /*
                ellipse(200, y, 30, 30);
                ellipse(300, lerpedY, 30, 30);
                y += random(-10, 10);
                lerpedY = lerp(lerpedY, y, 0.1f);
                */

                break;
            }   
            case 1:
            {
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 5, i, halfHeight + lerpedBuffer[i] * halfHeight * 5);
                }

                break;
            }
            // waveform down the 4 sides of the screen
            case 2:
            {
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        
                    line(i, lerpedBuffer[i] * halfHeight * 5, i, -lerpedBuffer[i] * halfHeight * 5);
                    line(lerpedBuffer[i] * halfHeight * 5, i, -lerpedBuffer[i] * halfHeight * 5, i);
                    line(i, height + (lerpedBuffer[i] * halfHeight * 5), i, height + (-lerpedBuffer[i] * halfHeight * 5));
                    line(width + (lerpedBuffer[i] * halfHeight * 4), i, width + (-lerpedBuffer[i] * halfHeight * 4), i);
                }

                break;
            }
            // circle
            case 3:
            {
                float diameter = 30 + (lerpedAverage * 700);
                float c = map(diameter, 0, ab.size(), 0, 255);

                noFill();
                stroke(c, 255, 255);

                ellipse(width / 2, height / 2, diameter, diameter);

                break;
            }
            // square
            case 4:
            {
                float length = 30 + (lerpedAverage * 700);
                float c = map(length, 0, ab.size(), 0, 255);

                noFill();
                stroke(c, 255, 255);
                rectMode(CENTER);

                rect(width / 2, height / 2, length, length);

                break;
            }
            // spiral
            case 5:
            {
                float r = 0.1f;
                int numPoints = 20; // number of points around the circle
                float thetaInc = TWO_PI / (float) numPoints;
                float lastX = width / 2, lastY = height / 2;

                strokeWeight(2);
                stroke(255);

                for (int i = 0 ; i < 1000 ; i++) {

                    float theta = i * thetaInc;
                    float x = width / 2 + sin(theta) * r;
                    float y = height / 2 + cos(theta) * r;
                    r += 0.5f;
                    
                    line(lastX, lastY, x, y);

                    lastX = x;
                    lastY = y;
                }

                break;
            }
        }        
    }
}