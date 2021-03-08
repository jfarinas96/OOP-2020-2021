package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet {
    
    Minim minim; // connect to minim library
    AudioInput ai; // how we connect to the microphone
    AudioBuffer ab; // samples
    AudioPlayer ap; // connect to an mp3 file

    float y = 300;
    float lerpedY = y;

    float lerpedAverage = 0;
    float[] lerpedBuffer;
    
    public void settings()
    {
        size(512, 512);
    }

    public void setup()
    {
        minim = new Minim(this);

        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; // connect to mic

        ap = minim.loadFile("heroplanet.mp3", width);
        ab = ap.mix; // connect to mp3 file
        ap.play(); // play the mp3 file

        colorMode(HSB);

        lerpedBuffer = new float[width];
    }

    public void draw()
    {
        background(0);
        stroke(255);

        float halfHeight = height / 2;
        float sum = 0;
        float average;

        for (int i = 0 ; i < ab.size() ; i++) {

            float c = map(i, 0, ab.size(), 0, 255);
            stroke(c, 255, 255);

            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);

            line(i, halfHeight - (lerpedBuffer[i] * halfHeight * 4), halfHeight + (lerpedBuffer[i] * halfHeight * 4), i);
            
            sum += abs(ab.get(i));
        }

        average = sum / (float) ab.size();

        // reduces the jitteriness
        lerpedAverage = lerp(lerpedAverage, average, 0.1f);

        ellipse(width / 2, 100, 50 + (lerpedAverage * 200), 50 + (lerpedAverage * 200));

        /*
        ellipse(200, y, 30, 30);
        ellipse(300, lerpedY, 30, 30);

        y += random(-10, 10);
        lerpedY = lerp(lerpedY, y, 0.1f); 
        */
    }   
}