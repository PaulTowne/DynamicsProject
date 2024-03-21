import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
public class SinglePendulumMain extends JPanel
{
    double time = .008;
    int t = 1;
    OG_Pendulum obj2;
    Single_Pendulum obj;
    Wave_Pendulum obj3; 
    boolean mouse = true;
    Point p;
    ArrayList<Point> wave = new ArrayList<Point>();
    public SinglePendulumMain(double initialx, double initialV)
    {
        addKeyListener(new KeyEventDemo());
        addMouseListener(new MouseClass());
        Timer t = new Timer(1, new TimeListener());
        Timer t2 = new Timer(10000,new TimeListener2());
        Timer t3 = new Timer(200,new TimeListener3());

        t.start();
        t2.start();
        setFocusable(true);
        
         obj2 = new OG_Pendulum(200, 400, 200, 1,0);
         // make it .5 m long, 100 pixels = 1m. 
        obj = new Single_Pendulum(.5, 400, 200, Math.PI/3, 0);
        obj3 = new Wave_Pendulum(.5,400,200,Math.PI/3,0);
        p=obj.calculate(time);
        wave = obj3.calculate(.001);
    }
    public void paintComponent(Graphics g)
    {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 800);
            g.setColor(Color.BLUE);
            g.drawOval(obj3.getX()-2, obj3.getY()-2, 5, 5);

                g.setColor(Color.BLUE);
                // the  numerical solve 
                if(mouse == true){
                     p = wave.get(t);
                     t = t+1;
                     if(t>wave.size()-1)
                     {
                         t =0;
                     }
                }
                g.fillOval((int)p.getX()-7,(int)p.getY()-7, 15, 15);
                g.setColor(Color.RED);
                g.drawOval((int)p.getX()-7,(int)p.getY()-7, 15, 15);
                g.drawLine(obj3.getX(), obj3.getY(), (int)p.getX(), (int)p.getY()); 
                //g.drawString("Angle: "+obj3.getAngle()+" ",500,200);
                // checking time to python
                //g.drawString("Time: "+t+"",500,300);
                // aut shutoff at certain time
                
                // for angle approx one 
                /* 
                g.setColor(Color.BLUE);
                Point p2 = obj2.calculate(time);
                g.fillOval((int)p2.getX()-7,(int)p2.getY()-7, 15, 15);
                g.setColor(Color.GREEN);
                g.drawOval((int)p2.getX()-7,(int)p2.getY()-7, 15, 15);
                g.drawLine(obj2.getX(), obj2.getY(), (int)p2.getX(), (int)p2.getY()); 
                */

    }
    public class KeyEventDemo implements KeyListener
    {
        public void keyTyped(KeyEvent e){}
        public void keyReleased(KeyEvent e){}
        public void keyPressed(KeyEvent e){}
        
    }
    public class MouseClass extends MouseInputAdapter
    {
        public void mousePressed(MouseEvent me)
        {
            if(mouse == true)
            mouse = false;
            else
            mouse = true;
        }
        public void mouseMoved(MouseEvent me)
        {

        }
        public void mouseDragged(MouseEvent me)
        {

        }
    }
    public class TimeListener3 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        }
    }
    public class TimeListener2 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           
            //obj = new PendulumObj_Numerical(.89, 400, 200, Math.PI/3, 0);
        }
    }
    public class TimeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            repaint();
            //time= time+.09; 
           
        }
    }
    
}
