
import java.util.*;
import java.io.*;
import java.nio.file.attribute.AclEntry;
import java.awt.*;
import javax.swing.*;

public class Single_Pendulum 
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
    public Single_Pendulum(double l,int xpos, int ypos, double initialAngle, double initialVelocity)
    {
        this.l = l;
        this.initialAngle = initialAngle;
        this.initialVelocity = initialVelocity;
        x = xpos;
        y = ypos; 
        position = initialAngle;
        velocity = initialVelocity;
        

    }
    public Point calculate(double timeStep)
    {
         acceleration = (-9.81/(l))*Math.sin(position);
         velocity =  velocity+(acceleration*timeStep);
         position = position+ (velocity*timeStep) + (.5*acceleration*Math.pow(timeStep,2));
        // System.out.println(Math.toDegrees(position)+" "+velocity+" "+acceleration);
        //System.out.println("A ="+acceleration+"    V ="+velocity);
        p = new Point(x+(l*300*Math.sin(position)), y+(l*300*Math.cos(position)));
        return p;
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
}
