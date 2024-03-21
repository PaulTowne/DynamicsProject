import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;


public class Main
{
    public static void main(String [] args)        
    {
        JFrame frame = new JFrame("Pendulum");
        frame.setSize(800,800);

        System.out.println("Choose which simulation you want to see \n1.Wave_Pendulum \n2.Single \n3.Double \n4.Triple \n5.All in One");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        if(choice ==1)
        {
            frame.setContentPane(new WavePendulumMain());
        }
        else if(choice ==2)
        {
            ///System.out.print("Input the inital theta and velocity: ");
            //double theta = scan.nextDouble();
            //double velocity = scan.nextDouble();
            frame.setContentPane(new SinglePendulumMain(0,0));
        }
        else if(choice ==3)
        {
            frame.setContentPane(new DoublePendulumMain());
        }
        else if(choice ==4)
        {
            frame.setContentPane(new TriplePendulumMain());
        }
        else if(choice ==5)
        {
            frame.setContentPane(new PendulumCenter());
        }



        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    }   
}

// Wave_Pendulum uses the small angle approximation, but it actually goes pretty high drastically increasing error
// hardcoded Single initial conditions due to difficulty in coding solver.
