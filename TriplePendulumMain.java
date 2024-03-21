import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
public class TriplePendulumMain extends JPanel
{
    double time = 0;
    int index = 0;
    Single_Pendulum obj;
    ArrayList<ArrayList<Point3p>> array = new ArrayList<ArrayList<Point3p>>();
    ArrayList<Double_Pendulum> pen = new ArrayList<Double_Pendulum>();
    ArrayList<Point2p> wave = new ArrayList<Point2p>();
    double offset=0;
    double timestep=.0001;
    Random rand = new Random();
    int choice=0;
    public TriplePendulumMain()
    {
        addKeyListener(new KeyEventDemo());
        addMouseListener(new MouseClass());
        Timer t = new Timer(1, new TimeListener());
        Timer t2 = new Timer(5000,new TimeListener2());
        Timer t3 = new Timer(200,new TimeListener3());

        t.start();
        setFocusable(true);
        Scanner scan = new Scanner(System.in);
        System.out.print("Number of Pendulums: ");
        choice = scan.nextInt();
        System.out.print("Offset: ");
        double offset = scan.nextDouble();
        for(int i=0;i<choice;i++)
        {
         Triple_Pendulum obj = new Triple_Pendulum(.3, .3,.3, 2, 2, 2, 400, 300, (170*Math.PI)/180, (179*Math.PI)/180, ((150*Math.PI)/180)+(i*offset),0,0, 0);
         array.add(obj.calculate(.0001));
        }
        

    }
    public void paintComponent(Graphics g)
    {
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 800);

            for(int i=0;i<choice;i++){
                //g.setColor(Color.white);
                g.setColor(Color.blue);
                //g.drawOval(400-5, 400-5, 10, 10);
                g.drawLine(400,300,(int)array.get(i).get(index).getX1(),(int)array.get(i).get(index).getY1());
                g.setColor(Color.red);
                g.drawLine((int)array.get(i).get(index).getX1(),(int)array.get(i).get(index).getY1(),(int)array.get(i).get(index).getX2(),(int)array.get(i).get(index).getY2());
                g.setColor(Color.green);
                g.drawLine((int)array.get(i).get(index).getX2(),(int)array.get(i).get(index).getY2(),(int)array.get(i).get(index).getX3(),(int)array.get(i).get(index).getY3());
                //System.out.println("loop");
                g.setColor(Color.red);
                g.drawString("Num of Pen: "+choice, 100, 100);
                //g.drawString("Offset: "+offset,100,130);

            }
            
            index ++;
            //System.out.println(wave.get(index).getX1()+" "+wave.get(index).getY1()+" "+wave.get(index).getX2()+" "+wave.get(index).getY2());
            //System.out.println("check");
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

