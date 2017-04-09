package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SnapShots extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar titleBar;
	private JLabel title;
	
	private JPanel mainPanel;
	private JPanel processes;
	
	private JPanel[] processPanels = new JPanel[4];
	private JPanel[] dataStructurePanels = new JPanel[10];
	private String[] nameOfStructure = {"Stack:","Queue1:","Queue2:","PseudoArray:","LinkedList:"};
	
	
	
	private String[] nameOfProcess = {"Current Process:     ","Status:                       ",
										"Operation:                 ","Postfix:                      "};
	public static JTextField[] textFieldOfProcess = new JTextField[4];
	public static JTextField[] textFieldOfStructure = new JTextField[5];
	
	public SnapShots()
	{
		setLayout(new BorderLayout());
		titleBar = new JMenuBar();
		titleBar.setLayout(new BorderLayout());
		title = new JLabel("                                                    SnapShots");
		title.setFont(new Font("Arial", Font.BOLD, 16));
		//title.setForeground(Color.WHITE);
		title.setForeground(Color.ORANGE);
		titleBar.add(title);//try
		titleBar.setBorderPainted(false);
		titleBar.setOpaque(false);
		add(titleBar, BorderLayout.NORTH);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 1));
		mainPanel.setOpaque(false);
		processes = new JPanel();
		processes.setOpaque(false);
		processes.setLayout(new GridLayout(16, 1));
		int j = 0;
		int k = 0, t = 0;
		for(int i = 0; i < 6; i++)
		{
			if(i == 1 || i == 5)
			{
				processes.add(new JLabel(""));
				continue;
			}
			
			processPanels[k]  = new JPanel();
			processPanels[k].setLayout(new FlowLayout(FlowLayout.LEFT));
			processPanels[k].setOpaque(false);
			JLabel label = new JLabel(nameOfProcess[t]);
			label.setFont(new Font("Arial", Font.BOLD, 14));
			label.setForeground(Color.CYAN);
			
			processPanels[k].add(label);
			
			textFieldOfProcess[t] = new JTextField(30);
			textFieldOfProcess[t].setBackground(new Color(0.0f,0.0f,0.0f,0.8f));
			textFieldOfProcess[t].setEditable(false);
			textFieldOfProcess[t].setForeground(Color.ORANGE);
			textFieldOfProcess[t].setText("");
			processPanels[k].add(textFieldOfProcess[t]);
			
			
			j += 0.1f;
			
			processes.add(processPanels[k]);
			k++;
			t++;
		}
		mainPanel.add(processes);
		add(mainPanel, BorderLayout.CENTER);
		
		j = 0;
		k = 0;
		t = 0;
		
		for(int i = 6; i < 16; i++)
		{
			if((i % 2) == 0)
			{
				JLabel label = new JLabel(nameOfStructure[t++]);
				label.setFont(new Font("Arial", Font.BOLD, 17));
				label.setForeground(Color.CYAN);
				processes.add(label);
				continue;
			}
			dataStructurePanels[k] = new JPanel();
			dataStructurePanels[k].setLayout(new FlowLayout(FlowLayout.LEFT));
			
			dataStructurePanels[k].setOpaque(false);
			
			textFieldOfStructure[j] = new JTextField(40);
			textFieldOfStructure[j].setBackground(new Color(0.0f,0.0f,0.0f,0.8f));
			textFieldOfStructure[j].setForeground(Color.WHITE);
			textFieldOfStructure[j].setEditable(false);
			dataStructurePanels[k].add(textFieldOfStructure[j]);
			j++;
			
			processes.add(dataStructurePanels[k]);
			
			
		}
		
	}
}
