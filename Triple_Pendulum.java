
import java.util.*;
import java.io.*;
import java.nio.channels.AcceptPendingException;
import java.nio.file.attribute.AclEntry;
import java.security.Timestamp;
import java.awt.*;
import javax.swing.*;

public class Triple_Pendulum 
{
    Point p;
    double l1;
    double l2;
    double l3;
    int x;
    int y;
    double a1;
    double a2;
    double a3;
    double v1;
    double v2;
    double v3;
    double m1;
    double m2;
    double m3;
   double g = 9.81;
   int speed = 0; // 80 speed
    ArrayList<Point3p> array = new ArrayList<Point3p>();
    Random rand = new Random();
    Color c;
    Color c2;
    Color c3;
    int iterate = 0;
    // angles should be in radians
    public Triple_Pendulum(double l1, double l2, double l3, double mass1, double mass2, double mass3, int xpos, int ypos, double inititalA, double initialA2, double initialA3, double initialV, double initialV2, double initialV3)
    {
        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;
        this.m1 = mass1;
        this.m2 = mass2;
        this.m3 = mass3;
        x = xpos;
        y = ypos;
        a1 = inititalA;
        a2 = initialA2;
        a3 = initialA3;
        v1 = initialV;
        v2 = initialV2;
        v3 = initialV3;

    }
    public ArrayList<Point3p> calculate(double timeStep)
    {
        for(int i=0;i<250000;i++){
            double acc1 = 
                        g*l1*m1*Math.sin(a1)+ g*l1*m2*Math.sin(a1)+g*l1*m3*
                        Math.sin(a1)+m2*l1*l2*Math.sin(a1-a2)*v1*v2 + 
                        m3*l1*l3*Math.sin(a1-a3)*v1*v3 + m3*l1*l2*Math.sin(a1-a2)*v1*v2 + 
                        m2*l1*l2*Math.sin(a2-a1)*(v1-v2)*v2 + 
                        m3*l1*l2*Math.sin(a2-a1)*(v1-v2)*v2 + 
                        m3*l1*l3*Math.sin(a3-a1)*(v1-v3)*v3;
            double acc11 = l1*l1*(m1+m2+m3);
            double acc12 = m2*l1*l2*Math.cos(a1-a2) + 
                        m3*l1*l2*Math.cos(a1-a2);
            double acc13 = m3*l1*l3*Math.cos(a1-a3);   

            double acc2 = 
                        g*l2*m2*Math.sin(a2) + g*l2*m3*Math.sin(a2) +
                        v1*v2*l1*l2*Math.sin(a2-a1)*(m2+m3) +
                        m3*l2*l3*Math.sin(a2-a3)*v2*v3 +
                        (m2+m3)*l1*l2*Math.sin(a2-a1)*(v1-v2)*v1 +
                        m3*l2*l3*Math.sin(a3-a2)*(v2-v3)*v3;
            double acc21 = (m2+m3)*l1*l2*Math.cos(a2-a1);
            double acc22 = l2*l2*(m2+m3);
            double acc23 = m3*l2*l3*Math.cos(a2-a3);

            double acc3 = m3*g*l3*Math.sin(a3) - m3*l2*l3*Math.sin(a2-a3)*v2*v3 - 
                        m3*l1*l3*Math.sin(a1-a3)*v1*v3 +  
                        m3*l1*l3*Math.sin(a3-a1)*(v1-v3)*v1 + 
                        m3*l2*l3*Math.sin(a3-a2)*(v2-v3)*v2;
            double acc31 = m3*l1*l3*Math.cos(a1-a3);
            double acc32 = m3*l2*l3*Math.cos(a2-a3);
            double acc33 = m3*l3*l3;

            acc1 = -acc1;
            acc2 = -acc2;
            acc3 = -acc3;

            double det = acc11 *(acc22*acc33 - acc23*acc32) + 
                        acc21*(acc32*acc13 - acc33*acc12) + 
                        acc31*(acc12*acc23 - acc13*acc22);
            
            double final1 = acc1*(acc22*acc33 - acc23*acc32) + 
                            acc2*(acc32*acc13 - acc33*acc12) + 
                            acc3*(acc12*acc23 - acc13*acc22);
            double final2 = acc1*(acc23*acc31 - acc21*acc33) + 
                            acc2*(acc33*acc11 - acc31*acc13) + 
                            acc3*(acc13*acc21 - acc11*acc23);
            double final3 = acc1*(acc21*acc32 - acc22*acc31) + 
                            acc2*(acc31*acc12 - acc32*acc11) + 
                            acc3*(acc11*acc22 - acc12*acc21);
            
            final1 = final1/det;
            final2 = final2/det;
            final3 = final3/det;

            v1 = v1+ final1*timeStep;
            a1 = a1 +(v1*timeStep);

            v2 = v2+ final2*timeStep;
            a2 = a2 +(v2*timeStep);

            v3 = v3+ final3*timeStep;
            a3 = a3 +(v3*timeStep);


            // x and y values
            double x1 = (400*l1*Math.sin(a1))+x;
            double y1 = (400*l1*Math.cos(a1))+y;

            double x2 = (400*l2*Math.sin(a2))+x1;
            double y2 = (400*l2*Math.cos(a2))+y1;

            double x3 = (400*l3*Math.sin(a3))+x2;
            double y3 = (400*l3*Math.cos(a3))+y2;

            // System.out.println(x1+" "+y1+" "+x2+" "+y2+" "+x3+" "+y3);
            if(iterate==30){
                array.add(new Point3p(x1,y1,x2,y2,x3,y3));
                iterate = 0;
            }
            else{
                iterate++;
            }

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
    public Color getColor3()
    {
        return c3;
    }
    public void setColor3(Color c3)
    {
        this.c3 =c3;
    }
}

