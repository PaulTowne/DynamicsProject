
import java.util.*;
import java.io.*;
import java.nio.channels.AcceptPendingException;
import java.nio.file.attribute.AclEntry;
import java.security.Timestamp;
import java.awt.*;
import javax.swing.*;

public class Double_Pendulum 
{
    Point p;
    double l1;
    double l2;
    int x;
    int y;
    double angle1;
    double angle2;
    double vel1;
    double vel2;
    double mass1;
    double mass2;
   double g = 9.81;
   int speed = 0; // 80 speed
    ArrayList<Point2p> array = new ArrayList<Point2p>();
    Random rand = new Random();
    Color c;
    Color c2;
    // angles should be in radians
    public Double_Pendulum(double l1, double l2, double mass1, double mass2, int xpos, int ypos, double inititalA, double initialA2, double initialV, double initialV2)
    {
        this.l1 = l1;
        this.l2 = l2;
        this.mass1 = mass1;
        this.mass2 = mass2;
        x = xpos;
        y = ypos;
        angle1 = inititalA;
        angle2 = initialA2;
        vel1 = initialV;
        vel2 = initialV2;
        c = new Color(255, rand.nextInt(255), rand.nextInt(255));


    }
    public ArrayList<Point2p> calculate(double timeStep)
    {
        // filling arraylist with 1 numerical loop
        // calculate period then figure out how many points to take
        double numerator;
        double denominator; 
        double acceleration1; 

        double numerator2;
        double denominator2;
        double acceleration2; 

        // letters attempt
        /* 
        double A = (mass1+mass2)*Math.pow(l1,2);
        double B = mass2*l1*l2*Math.cos(angle2-angle1);
        double C = -(mass2*l1*l2*Math.pow(vel2,2)*Math.sin(angle2-angle1));
        double D = (mass1+mass2)*l1*g*Math.sin(angle1);
        double E = mass2*Math.pow(l2,2);
        double F = mass2*l1*l2*Math.cos(angle2-angle1);
        double G = -(mass2*l1*l2*Math.pow(vel1,2)*Math.cos(angle2-angle1));
        double H = mass2*l2*g*Math.sin(angle2);
        */
        for(int i=0;i<250000;i++)
        {
            // acceleration 1 
            //numerator = mass2*l2*Math.pow(vel2,2)*Math.sin(angle2-angle1)-(mass1+mass2)*9.81*Math.sin(angle1)+mass2*Math.cos(angle2-angle1)*(l1*Math.pow(vel1,2)*Math.sin(angle2-angle1)+9.81*Math.sin(angle1));
            //denominator = ((mass1+mass2)*l1-mass2*Math.cos(angle2-angle1)*l1*Math.cos(angle2-angle1));
            //acceleration1 = numerator/denominator;
            // attempt with letters
            //acceleration1 = (G-H-E*((A*(G-H)+F*(D+C))/((E*A)-(F*B))))/F;
            numerator = -g*((2*mass1)+mass2)*Math.sin(angle1)-
                        mass2*g*Math.sin(angle1-(2*angle2))-
                        2*Math.sin(angle1-angle2)*mass2*(Math.pow(vel2,2)*l2+Math.pow(vel1,2)*l1*Math.cos(angle1-angle2));
            denominator = l1*((2*mass1)+mass2-mass2*Math.cos((2*angle1)-(2*angle2)));
            acceleration1 = numerator/denominator;   

            vel1 = vel1 + acceleration1*timeStep;
            angle1 = angle1+ (vel1*timeStep) + (.5*acceleration1*Math.pow(timeStep,2));
            // testing 
            // acceleration 2
             //numerator2 = (mass2*l2*Math.pow(vel2,2)*Math.sin(angle2-angle1)-(mass1+mass2)*(g*Math.sin(angle1))) - ((mass1+mass2)*l1*(-l1*Math.pow(vel1,2)*Math.sin(angle2-angle1))+(mass1+mass2)*l1*g*Math.sin(angle2))/(l2*Math.cos(angle2-angle1));
             //denominator2 = (-(mass1+mass2)*l1*l2)/(l2*Math.cos(angle2-angle1)) + mass2*l2*Math.cos(angle1-angle2);

             //acceleration2 = numerator2/denominator2;
             //acceleration2 = ((A*(G-H))+(F*D)*(F*C))/((E*A)-(F*B));
             numerator2 = 2*Math.sin(angle1-angle2)*(Math.pow(vel1,2)*l1*(mass1+mass2)+
                          g*(mass1+mass2)*Math.cos(angle1)+(Math.pow(vel2,2)*l2*mass2*Math.cos(angle1-angle2)));  
             denominator2 = l2*((2*mass1)+mass2-(mass2*Math.cos((2*angle1)-(2*angle2))));
             acceleration2 = numerator2/denominator2;                    

             vel2 = vel2 + acceleration2*timeStep;
             angle2 = angle2 + (vel2*timeStep) + (.5*acceleration2*Math.pow(timeStep,2));
             if(speed==30)
             {
                 speed=0;
                Point2p p = new Point2p(x+(400*l1*Math.sin(angle1)),y+(400*l1*Math.cos(angle1)),x+(400*l1*Math.sin(angle1)) + 400*l2*Math.sin(angle2),x+(400*l1*Math.cos(angle1)) + 400*l2*Math.cos(angle2));
                array.add(p);
            }
            else{ 
               speed++;
            }
            /* 
             A = (mass1+mass2)*Math.pow(l1,2);
             B = mass2*l1*l2*Math.cos(angle2-angle1);
             C = -(mass2*l1*l2*Math.pow(vel2,2)*Math.sin(angle2-angle1));
             D = (mass1+mass2)*l1*g*Math.sin(angle1);
             E = mass2*Math.pow(l2,2);
             F = mass2*l1*l2*Math.cos(angle2-angle1);
             G = -(mass2*l1*l2*Math.pow(vel1,2)*Math.cos(angle2-angle1));
             H = mass2*l2*g*Math.sin(angle2);
            */
        }

        return array;
    }
    public double getAngle()
    {
        //double degree = Math.toDegrees(position);
        return 0;
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
    public Color getColor2()
    {
        return c2;
    }
    public void setColor2(Color c2)
    {
        this.c2 =c2;
    }
}
