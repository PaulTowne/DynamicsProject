
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class OG_Pendulum 
{
    Point p;
    int l;
    int x;
    int y;
    double initialAngle;
    double initialVelocity;
    double g = 9.81;
    public OG_Pendulum(int l,int xpos, int ypos, double initialAngle, double initialVelocity)
    {
        this.l = l;
        this.initialAngle = initialAngle;
        x = xpos;
        y = ypos; 
        

    }
    public Point calculate(double time)
    {
        double theta = Math.cos(Math.sqrt(g/l)*time);
        p = new Point(x+l*Math.sin(theta), y+l*Math.cos(theta));
        return p;
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getL()
    {
        return l;
    }
}
