package main;
 

 

 
import java.awt.Color;

import javax.swing.JFrame;
 
//import dataStructures.Queue;
//import dataStructures.Stack;
import gui.CrazyCalculatorGUI;
 
public class Main
 
{
 
  
 
  public static void main(String[] args)
 
  {
 
    CrazyCalculatorGUI gui = new CrazyCalculatorGUI();
 
    gui.setSize(300, 450);
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gui.setUndecorated(true);
    gui.setLocationRelativeTo(null);
    gui.setBackground(new Color((float)0, (float)0, (float)0, (float)0.70));
    gui.setVisible(true);
 
    
 
   /* Stack<String> s = new Stack<String>(4);
 
    s.push("w");
 
    s.push("AA");
 
    System.out.println(s.pop());
 
    s.pop();*/
 
    
 
    //Queue<Integer> n = new Queue<Integer>(4);
 
    //n.enqueue(2);
 
    //n.enqueue(3);
 
    //System.out.println(n.isEmpty());
 
    //n.dequeue();
 
    //n.dequeue();
 
    //System.out.println(n.isEmpty());
 
    
 
    
 
  }
 
}
