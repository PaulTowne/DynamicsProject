import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
public class WavePendulumMain extends JPanel
{
    double time = 0;
    int index = 0;
    Single_Pendulum obj;
    ArrayList<ArrayList<Point>> wave = new ArrayList<ArrayList<Point>>();
    Random rand = new Random();

    public WavePendulumMain()
    {
        addKeyListener(new KeyEventDemo());
        addMouseListener(new MouseClass());
        Timer t = new Timer(1, new TimeListener());
        Timer t2 = new Timer(5000,new TimeListener2());
        Timer t3 = new Timer(200,new TimeListener3());

        t.start();
        setFocusable(true);
        
        // Using a Lmax = .5m, Tmax = 20sec 
        double k = 12.09936744;
        
        for( int i=1;i<20;i++)
        {
            double l = 9.81*Math.pow((20/((2*Math.PI)*(k+i+1))),2);
            Wave_Pendulum pen = new Wave_Pendulum(l, 400, 200-(40-(i*2)), Math.PI/3, 0);
            wave.add(pen.calculate(.001));
        }
        

    }
    public void paintComponent(Graphics g)
    {
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 800);
            

            for(int i=wave.size()-1;i>0;i--)
            {

                //g.setColor(Color.BLACK);
                g.setColor(Color.blue);
                //Point p = wave.get(i).calculate(.01);
                int radius = 25-i;
                g.fillOval((int)wave.get(i).get(index).getX()-(radius/2), (int)wave.get(i).get(index).getY()-(radius/2), radius, radius);
                
                g.setColor(Color.red);
                g.drawOval((int)wave.get(i).get(index).getX()-(radius/2), (int)wave.get(i).get(index).getY()-(radius/2), radius, radius);        
                //g.drawOval((int)wave.get(i).get(index).getX(), (int)wave.get(i).get(index).getY(), 5, 5);
                

                g.drawOval(400-3,200-3,5,5);
                //System.out.println("loop");
                //g.drawLine(wave.get(i).getX(), wave.get(i).getY(), (int)p.getX(), (int)p.getY());
            }
            index = index +1;
            if(index>wave.get(0).size()-1)
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
