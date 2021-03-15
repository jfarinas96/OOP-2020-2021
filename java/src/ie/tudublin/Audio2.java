package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class Audio2 extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;
    AudioInput ai;
    FFT fft;

    float y = 200;
    float lerpedY = y;

    int which = 0;

    float[] bands;
    float[] smoothedBands;

    void calculateFrequencyBands() {
        for (int i = 0; i < bands.length; i++) {
            int start = (int) pow(2, i) - 1;
            int w = (int) pow(2, i);
            int end = start + w;
            float average = 0;
            for (int j = start; j < end; j++) {
                average += fft.getBand(j) * (j + 1);
            }
            average /= (float) w;
            bands[i] = average * 5.0f;
            smoothedBands[i] = lerp(smoothedBands[i], bands[i], 0.05f);
        }
    }

    float log2(float f) {
        return log(f) / log(2.0f);
    }

    public void settings() {
        size(512, 512, P3D);
        //fullScreen(P3D, SPAN); // Try this for full screen multiple monitor support :-) Be careful of exceptions!
    }

    public void setup() {         
        colorMode(HSB);
        
        minim = new Minim(this);
        ap = minim.loadFile("heroplanet.mp3", width);
        ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        ab = ap.mix;

        fft = new FFT(width, 44100);

        bands = new float[(int) log2(width)];
        smoothedBands = new float[bands.length];
    }

    public void keyPressed() {
        if (keyCode >= '0' && keyCode <= '5') {
            which = keyCode - '0';
        }

        if (keyCode == ' ')
        {
            if (ap.isPlaying()) {
                ap.pause();
            }
            else {
                ap.rewind();
                ap.play();
            }
        }
    }

    float lerpedAverage = 0;
    int highestBand = 0;

    public void draw() {
        background(0);
        stroke(255);

        float halfHeight = height / 2;

        for (int i = 0; i < ab.size(); i++) {
            stroke(map(i, 0, ab.size(), 0, 255), 255, 255);
            // line(i, halfHeight - (ab.get(i) * halfHeight), i, halfHeight + (ab.get(i) * halfHeight));
        }

        fft.window(FFT.HAMMING);
        fft.forward(ab);
        
        for (int i = 0; i < fft.specSize(); i++) {
            stroke(map(i, 0, ab.size(), 0, 255), 255, 255);
            // line(i, 0, i, fft.getBand(i) * halfHeight);

            if (fft.getBand(i) > fft.getBand(highestBand)) {
                highestBand = i;
            }
        }

        float freq = fft.indexToFreq(highestBand);
        stroke(255);
        text("Frequency: " + freq, width - 120, height - 15);
        // text("Note: " + spell(freq), width - 120, height - 65);

        calculateFrequencyBands();

        float w = width / (float) bands.length;
        for(int i = 0 ; i < bands.length ; i ++)
        {
            float x = map(i, 0, bands.length, 0, width);
            float c = map(i, 0, bands.length, 0, 255);
            noStroke();
            fill(c, 255, 255);
            rect(x, height, w, -smoothedBands[i]);
        }
    }
}