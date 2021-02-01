package ie.tudublin;

import processing.core.PApplet;

public class HelloProcessing2 extends PApplet
{

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		
	}
	
	public void draw()
	{	
		/*
		background(255, 0, 0);
        stroke(0, 255, 0); // sets pen colour
        line(10, 10, 200, 200); // x1, y1, x2, y2

        noStroke();
        ellipse(200, 200, 100, 50); // cx, cy, w, h
        fill(0, 0, 255);
        rect(20, 100, 70, 90); // tlx, tly, w, h
        point(200, 60);
        fill(0, 255, 255);
        triangle(200, 90, 300, 200, 10, 60);

        fill(0);
        text("Hello World", 300, 300);
		*/

		//Illuminati eye
		
		background(255, 0, 0);
		noStroke();
		// yellow circle
		fill(255, 255, 0);
		ellipse(250, 300, 400, 400);
		// blue triangle
		fill(0, 255, 255);
		triangle(250, 50, 50, 450, 450, 450);
		// outer eye
		fill(211, 211, 211);
		ellipse(250, 250, 190, mouseY);
		// inner eye
		fill(0);
		ellipse(250, 250, 70, mouseY);

	}
}
