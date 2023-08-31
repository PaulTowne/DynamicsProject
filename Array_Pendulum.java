
import java.util.*;
import java.io.*;
import java.nio.file.attribute.AclEntry;
import java.awt.*;
import javax.swing.*;

public class Array_Pendulum 
{
    Point p;
    double l;
    int x;
    int y;
    double initialAngle;
    double initialVelocity;
    double g = 9.81;
    double acceleration;
    double velocity;
    double position;
    ArrayList<Point> array = new ArrayList<Point>();
    Color c;
    int iterate=0;

    public Array_Pendulum(double l,int xpos, int ypos, double initialAngle, double initialVelocity)
    {
        this.l = l;
        this.initialAngle = initialAngle;
        this.initialVelocity = initialVelocity;
        x = xpos;
        y = ypos; 
        position = initialAngle;
        velocity = initialVelocity;


    }
    public ArrayList<Point> calculate(double timeStep)
    {
        // filling arraylist with 1 numerical loop
        // calculate period then figure out how many points to take
        for(int i=0;i<250000;i++)
        {
            acceleration = (-9.81/(l))*Math.sin(position);
            velocity =  velocity+(acceleration*timeStep);
            position = position+ (velocity*timeStep) + (.5*acceleration*Math.pow(timeStep,2));
            // System.out.println(Math.toDegrees(position)+" "+velocity+" "+acceleration);
            //System.out.println("A ="+acceleration+"    V ="+velocity);
            if(iterate ==30)
            {
                p = new Point(x+(l*400*Math.sin(position)), y+(l*400*Math.cos(position)));
                array.add(p);
                iterate=0;
            }
            else 
            {
                iterate ++;
            }
        }

        return array;
    }
    public double getAngle()
    {
        double degree = Math.toDegrees(position);
        return degree;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public double getL()
    {
        return l;
    }
    public Color getColor()
    {
        return c;
    }
    public void setColor(Color c)
    {
        this.c = c;
    }
}
