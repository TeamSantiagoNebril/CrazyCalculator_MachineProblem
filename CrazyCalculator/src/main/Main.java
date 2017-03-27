package main; 

 
import java.awt.Color;

import javax.swing.JFrame;

import gui.CrazyCalculatorGUI;
 
public class Main
 
{  
 
  public static void main(String[] args)
 
  {
 
    CrazyCalculatorGUI gui = new CrazyCalculatorGUI();
    gui.setSize(800, 450);
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gui.setUndecorated(true);
    gui.setLocationRelativeTo(null);
    gui.setBackground(new Color((float)0, (float)0, (float)0, (float)0.00));
    gui.setVisible(true);
  }
 
}
