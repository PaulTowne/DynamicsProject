import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
public class DoublePendulumMain extends JPanel
{
    double time = 0;
    int index = 0;
    Single_Pendulum obj;
    ArrayList<ArrayList<Point2p>> array = new ArrayList<ArrayList<Point2p>>();
    ArrayList<Double_Pendulum> pen = new ArrayList<Double_Pendulum>();
    ArrayList<Point2p> wave = new ArrayList<Point2p>();
    double offset=0;
    double timestep=.0001;
    Random rand = new Random();
    int choice=0;
    public DoublePendulumMain()
    {
        addKeyListener(new KeyEventDemo());
        addMouseListener(new MouseClass());
        Timer t = new Timer(1, new TimeListener());
        Timer t2 = new Timer(5000,new TimeListener2());
        Timer t3 = new Timer(200,new TimeListener3());

        t.start();
        setFocusable(true);
        Scanner scan = new Scanner(System.in);
        System.out.print("Desired # of Pen: ");
         choice = scan.nextInt();
         System.out.print("Desired offset: ");
         offset = scan.nextDouble();
        for(int i=0;i<choice;i++)
        {
            Double_Pendulum obj = new Double_Pendulum(.3, .3, 2, 2, 400, 400, (Math.PI+.01), Math.PI+i*offset, 0, 0);
            array.add(obj.calculate(timestep));
            pen.add(obj);
        }
    
        

    }
    public void paintComponent(Graphics g)
    {
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 800);
            for(int i=0;i<choice;i++)
            {
                g.setColor(Color.blue);
                //g.drawOval(400-5, 400-5, 10, 10);
                g.drawLine(400,400,(int)array.get(i).get(index).getX1(),(int)array.get(i).get(index).getY1());
                // can set color of pendulums
                //g.setColor(pen.get(i).getColor());
                g.setColor(Color.red);
                g.drawLine((int)array.get(i).get(index).getX1(),(int)array.get(i).get(index).getY1(),(int)array.get(i).get(index).getX2(),(int)array.get(i).get(index).getY2());
                //System.out.println(i+": "+array.get(i).get(index).getX1()+" "+array.get(i).get(index).getY1()+" "+array.get(i).get(index).getX2()+" "+array.get(i).get(index).getY2());
                //g.drawOval(400, 400, i, i);

                // drawing Text to make it nice
                g.drawString("Num of Pen: " + choice,20,20);
                //g.drawString("Desired Offset: "+offset,20,40);
                g.drawString("Time: "+String.format("%.3f",timestep*index),650,20);
            }
            index = index +2;
            //System.out.println(wave.get(index).getX1()+" "+wave.get(index).getY1()+" "+wave.get(index).getX2()+" "+wave.get(index).getY2());

            if(index>array.get(0).size()-1)
            {
                index = 0;
            }
            
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
           
           
        }
    }
    public class TimeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            repaint(); 
        }
    }
    
}

