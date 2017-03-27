package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import staticHelper.Helper;

public class SnapShots extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea[] column = new JTextArea[4];
	private JPanel area, centerPanel, northPanel;
	private JTextField[] columnTitle = new JTextField[4];
	private BorderLayout lineBorder;
	private int padding;
	
	public SnapShots()
	{
		Color blackOpaque = new Color(0.0f, 0.0f, 0.0f, .70f);
		
		Font font = Helper.createFromExternalFont("external\\Fonts\\pdark.ttf", 14f);
		setLocation(300, 0);
		setSize(501, 450);
		setBackground(blackOpaque); // Whole Subpanel BackgroundColor
		setVisible(false);
		setLayout(new BorderLayout());
		
		
		padding = 3;
		lineBorder = new BorderLayout();
	    lineBorder.setVgap(padding);
		
		area = new JPanel();
		area.setLayout(lineBorder);
		area.setOpaque(false);
		//area.setBackground(Color.GREEN); // Lower Between Title BackgroundColor
		
		centerPanel = new JPanel();
		centerPanel.setLayout( new GridLayout(1, 4, padding, 30));
		centerPanel.setOpaque(false);
		//centerPanel.setBackground(Color.GREEN); //Between Column BColor
		for(int i = 0; i < 4; i++)
		{
			column[i] = new JTextArea();
			column[i].setEditable(false);
			column[i].setLineWrap(true);
			//column[i].setOpaque(false);
			//column[i].setBackground(blackOpaque); //BColor of Columns
			centerPanel.add(column[i]);
		}
		column[0].setBackground(new Color(1.0f, 0.7f, 0.f, 0.5f));
		column[1].setBackground(new Color(1.0f, 0.8f, 0.f, 0.5f));
		column[2].setBackground(new Color(1.0f, 0.9f, 0f, 0.5f));
		column[3].setBackground(new Color(1.0f, 1.0f, .0f, 0.5f));
		
		
		area.add(centerPanel, BorderLayout.CENTER);
		
		
		
		columnTitle[0] = new JTextField("Read", SwingConstants.CENTER);
		columnTitle[1] = new JTextField("Parsed", SwingConstants.CENTER);
		columnTitle[2] = new JTextField("Written", SwingConstants.CENTER);
		columnTitle[3] = new JTextField("Stack", SwingConstants.CENTER);
		
		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1, 4, padding, 20));
		northPanel.setOpaque(false);
		//northPanel.setBackground(new Color((float)0,(float)0.9, (float)0.9,(float) .0));//Between Title BColor
		
		for(JTextField e : columnTitle)
		{
			e.setBorder(null);
			e.setEditable(false);
			e.setOpaque(false);
			//e.setBackground(blackOpaque);
			e.setForeground(Color.ORANGE);
			e.setFont(font);
			e.setHorizontalAlignment(JTextField.CENTER);
			northPanel.add(e);
		}
		area.add(northPanel, BorderLayout.NORTH);
		add(area, BorderLayout.CENTER);
		
	}
}
