package ie.tudublin;

import processing.core.PApplet;

public class BugZap extends PApplet
{
    public void settings()
    {
        size(750, 500);
    }

    public void setup()
    {
        background(0);

        playerX = width / 2;
        playerY = height - 25;
        playerWidth = 15;

        bugX = random(0, width);
        bugY = 25;
        bugWidth = 15;
    }

    float playerX, playerY, playerWidth;
    float bugX, bugY, bugWidth;

    public void drawPlayer(float x, float y, float w)
    {
        stroke(255, 255, 0);
        
        line(x - w, y, x + w, y);
        line(x - w, y, x - w + 5, y - 8);
        line(x + w, y, x + w - 5, y - 8);
        line(x - w + 5, y - 8, x + w - 5, y - 8);
    }

    public void drawBug(float bx, float by, float bw)
    {
        stroke(255, 0, 0);

        if ((frameCount % 60) == 0)
        {
            bx = random(0, width);
            
            by = by + 10;
        }

        line(bx - bw, by, bx + bw, by);
        line(bx - (bw / 2), by, bx - bw + 5, by + 5);
        line(bx + (bw / 2), by, bx + bw - 5, by + 5);
    }

    public void draw()
    {
        drawPlayer(playerX, playerY, playerWidth);
        drawBug(bugX, bugY, bugWidth);
    }

    public void keyPressed()
	{
		if (keyCode == LEFT)
		{
            // System.out.println("Left arrow pressed");
            if (playerX > playerWidth && playerX <= width - playerWidth)
            {
                playerX = playerX - 2;
            }
		}
		if (keyCode == RIGHT)
		{
            // System.out.println("Right arrow pressed");
            if (playerX >= playerWidth && playerX < width - playerWidth)
            {
                playerX = playerX + 2;
            }
		}
		if (key == ' ')
		{
            // System.out.println("SPACE key pressed");
            stroke(255);
            line(playerX, playerY - 8, playerX, 0);
		}
	}	

}
