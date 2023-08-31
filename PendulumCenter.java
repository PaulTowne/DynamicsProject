import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.security.Timestamp;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
import java.text.DecimalFormat;
public class PendulumCenter extends JPanel
{
    double time = 0;
    int index = 0;
    boolean pause= false;

    ArrayList<ArrayList<Point3p>> triplePoint = new ArrayList<ArrayList<Point3p>>();
    ArrayList<ArrayList<Point2p>> doublePoint = new ArrayList<ArrayList<Point2p>>();
    ArrayList<ArrayList<Point>> singlePoint = new ArrayList<ArrayList<Point>>();

    ArrayList<Double_Pendulum> doublePen = new ArrayList<Double_Pendulum>();
    ArrayList<Triple_Pendulum> triplePen = new ArrayList<Triple_Pendulum>();
    ArrayList<Array_Pendulum> singlePen = new ArrayList<Array_Pendulum>();

    JButton button1 = new JButton("Single");
    JButton button2 = new JButton("Double");
    JButton button3 = new JButton("Triple");
    JButton button4 = new JButton("Pause");
    JComboBox<String> comboBox;
    JTextField textField = new JTextField(5);

    DecimalFormat df = new DecimalFormat("0.00");
    //variables determined by the buttons 
    int pendulumChoice =0;
    int colorChoice=0; // control color 
    int numOfPen = 1; // set to how many to make on run
    int indeX = 0;// the index of which combobox thing is clicked
    double timeStamp=0;
    // what if we pre - create the pendulums and only draw depending on the number of pendulums selected. Like we make 200 of each type and only loop thru the # of pens...


    // lets add colors, trails, changing lengths, mass, offset
    // maybe change menus (buttons) based on which pendulum is clicked.
    // set index to 0 in order to loop
    double offset=.0000001;
    double timestep=.0001;
    int choice=0;
    public PendulumCenter()
    {
        String[] items = {"White","RGB","Mixed"};
        comboBox = new JComboBox<>(items);
        //super(new FlowLayout());
        setLayout(new BorderLayout());
        // chat gpt stuff
                JPanel panel = new JPanel();
                panel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                c.insets = new Insets(10, 0, 0, 5); // top, left, bottom, right
                panel.add(button1, c);

                c.gridx = 1;
                c.gridy = 0;
                c.insets = new Insets(10, 0, 0, 5);
                panel.add(button2, c);

                c.gridx = 2;
                c.gridy = 0;
                c.insets = new Insets(10, 0, 0, 5);
                panel.add(button3, c);

                c.gridx = 3;
                c.gridy = 0;
                c.insets = new Insets(10, 0, 0, 5); // top, left, bottom, right
                panel.add(button4, c);

                c.gridx = 4;
                c.gridy = 0;
                c.insets = new Insets(10, 0, 0, 5); // top, left, bottom, right
                panel.add(comboBox, c);

                c.gridx = 5;
                c.gridy = 0;
                c.insets = new Insets(10, 0, 0, 10); // top, left, bottom, right
                panel.add(textField, c);

                panel.setBackground(Color.black);
                add(panel,BorderLayout.NORTH);
                button1.addActionListener(new ButtonListener1());
                button2.addActionListener(new ButtonListener1());
                button3.addActionListener(new ButtonListener1());
                button4.addActionListener(new ButtonListener1());
                comboBox.addActionListener(new ButtonListener2());
                textField.addActionListener(new ButtonListener3());

                button1.setBackground(Color.gray);
                button2.setBackground(Color.gray);
                button3.setBackground(Color.gray);
                button4.setBackground(Color.gray);
                comboBox.setBackground(Color.gray);
                textField.setBackground(Color.gray);


        addKeyListener(new KeyEventDemo());
        addMouseListener(new MouseClass());
        

        Timer t = new Timer(1, new TimeListener());
        Timer t2 = new Timer(5000,new TimeListener2());
        Timer t3 = new Timer(200,new TimeListener3());
        t.start();
        setFocusable(true);

        for(int i=0;i<200;i++)
            {
                singlePoint.add(new Array_Pendulum(.3, 400, 400, (Math.PI/2)+offset*i,0).calculate(timestep));
                singlePen.add(new Array_Pendulum(.3, 400, 400, (Math.PI/2)+offset*i, 0));
            }
        for(int i=0;i<200;i++)
            {
                Double_Pendulum ob = new Double_Pendulum(.3, .3, 2, 2, 400, 400, (170*Math.PI)/180, ((Math.PI*170)/180)+offset*i,0, 0);
                doublePen.add(ob);
                doublePoint.add(ob.calculate(timestep));
            }
        for(int i=0;i<200;i++)
            {
                Triple_Pendulum obj = new Triple_Pendulum(.3, .3,.3, 2, 2, 2, 400, 400, 9*(Math.PI/10),  ((5*Math.PI)/6), (Math.PI/2)+offset*i,0,0, 0);
                triplePoint.add(obj.calculate(timestep));
                triplePen.add(obj);
            }
        setColors();
        // creates buttons and sets some variable to control what is painted. 
        
        

    }
    public class ButtonListener3 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String text = textField.getText();
            numOfPen = Integer.parseInt(text);
            if(numOfPen>200)
                numOfPen = 200;
            // need a way to limit hte number based on which pendulum is chosen. Check on laptop so we dont blow it up. 
            //createPendulums(e);
        }
    }
    void setColors()
    {
        indeX = comboBox.getSelectedIndex();
            Color first=Color.white;
            Color second=Color.white;
            Color third=Color.white;
            Random rand = new Random();
            if(indeX == 0)
            {
                first = second = third = Color.white;
                for(int i=0;i<singlePen.size();i++)
                    {
                        singlePen.get(i).setColor(first);
                    }
                    for(int i=0;i<doublePen.size();i++)
                    {
                        doublePen.get(i).setColor(second);
                        doublePen.get(i).setColor2(second);
                    }
                    for(int i=0;i<triplePen.size();i++)
                    {
                        triplePen.get(i).setColor(third);
                        triplePen.get(i).setColor2(third);
                        triplePen.get(i).setColor3(third);
                    }
            }
            else if(indeX ==1)
            {
                first = Color.red;
                second = Color.green;
                third=  Color.blue;
                for(int i=0;i<singlePen.size();i++)
                    {
                        singlePen.get(i).setColor(first);
                    }
                    for(int i=0;i<doublePen.size();i++)
                    {
                        doublePen.get(i).setColor(first);
                        doublePen.get(i).setColor2(third);
                    }
                    for(int i=0;i<triplePen.size();i++)
                    {
                        triplePen.get(i).setColor(first);
                        triplePen.get(i).setColor2(second);
                        triplePen.get(i).setColor3(third);
                    }
            }
            else if(indeX ==2)
            {
                    for(int i=0;i<singlePen.size();i++)
                    {
                        first = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
                        second = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
                        third = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
                        singlePen.get(i).setColor(first);
                    }
                    for(int i=0;i<doublePen.size();i++)
                    {
                        first = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
                        doublePen.get(i).setColor(first);
                        doublePen.get(i).setColor2(first);
                    }
                    for(int i=0;i<triplePen.size();i++)
                    {
                        first = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
                        triplePen.get(i).setColor(first);
                        triplePen.get(i).setColor2(first);
                        triplePen.get(i).setColor3(first);
                    }
            }
    }
    public class ButtonListener2 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            setColors();
        }
    }
    void createPendulums(ActionEvent e)
    {
        button1.setBackground(Color.gray);
        button2.setBackground(Color.gray);
        button3.setBackground(Color.gray);
        button4.setBackground(Color.gray);
        //triplePoint.clear();
        //doublePoint.clear();
        //singlePoint.clear();
        //triplePen.clear();
        //doublePen.clear();
        //singlePen.clear();
        
         if(e.getSource() == button1)
         {
            System.out.println("Single");
            button1.setBackground(Color.RED);
            pendulumChoice = 0;
            index =0;
         }
         if(e.getSource() == button2)
         {
            System.out.println("Double");
            button2.setBackground(Color.RED);
            pendulumChoice = 1;
            index =0;
            
        }
         if(e.getSource() == button3)
         {
            System.out.println("Triple");
            button3.setBackground(Color.RED);
            pendulumChoice = 2;
            index =0;
        }
        if(e.getSource() == button4)
        {
            button4.setBackground(Color.red);
            //index =0;
            if(pause)
                pause = false;
            else
                pause = true;
        }
         setColors();
    }
    public class ButtonListener1 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
        createPendulums(e);
      }
   }
   
    public void paintComponent(Graphics g)
    {
            super.paintComponent(g);
            g.setColor(Color.black);
            g.fillRect(0,0,800,800);
            g.setColor(Color.red);
            if(pendulumChoice == 0){
                for(int i=0;i<numOfPen;i++)
                {
                    g.setColor(singlePen.get(i).getColor());
                    g.drawLine(singlePen.get(i).getX(),singlePen.get(i).getY(),(int)singlePoint.get(i).get(index).getX(),(int)singlePoint.get(i).get(index).getY());
                }
            }
            if(pendulumChoice ==1){
                for(int i=0;i<numOfPen;i++)
                {
                    g.setColor(doublePen.get(i).getColor());
                    g.drawLine(doublePen.get(i).getX(),doublePen.get(i).getY(),(int)doublePoint.get(i).get(index).getX1(),(int)doublePoint.get(i).get(index).getY1());
                    g.setColor(doublePen.get(i).getColor2());
                    g.drawLine((int)doublePoint.get(i).get(index).getX1(),(int)doublePoint.get(i).get(index).getY1(),(int)doublePoint.get(i).get(index).getX2(),(int)doublePoint.get(i).get(index).getY2());
                    
                }
            }
            if(pendulumChoice ==2){
                for(int i=0;i<numOfPen;i++)
                {
                    g.setColor(triplePen.get(i).getColor());
                    //g.drawOval(400-5, 400-5, 10, 10);
                    g.drawLine(triplePen.get(i).getX(),triplePen.get(i).getY(),(int)triplePoint.get(i).get(index).getX1(),(int)triplePoint.get(i).get(index).getY1());
                    g.setColor(triplePen.get(i).getColor2());
                    g.drawLine((int)triplePoint.get(i).get(index).getX1(),(int)triplePoint.get(i).get(index).getY1(),(int)triplePoint.get(i).get(index).getX2(),(int)triplePoint.get(i).get(index).getY2());
                    g.setColor(triplePen.get(i).getColor3());
                    g.drawLine((int)triplePoint.get(i).get(index).getX2(),(int)triplePoint.get(i).get(index).getY2(),(int)triplePoint.get(i).get(index).getX3(),(int)triplePoint.get(i).get(index).getY3());
                    //System.out.println("loop");
                }
            }
            g.setColor(Color.red);
            timeStamp = index*30*timestep;
            g.drawString("Timestamp = "+df.format(timeStamp), 400, 50);
            g.drawString("Max # = 200",540,50);
            index = index + 1;
            if(index>8000)
                index =0;
            // currently dont have a way to not go over index. 
            
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
           //System.out.println("triple: "+triplePoint.get(0).size());
           //System.out.println("index ="+index);
           //System.out.println("double: "+doublePoint.get(0).size());
           //System.out.println("single: "+singlePoint.get(0).size());
           //System.out.println("index ="+index);
            
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
            if(!pause){
                repaint();
            }
        }
    }
    
}

