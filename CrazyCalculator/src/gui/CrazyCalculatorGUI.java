package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.border.Border;

import core.Calculator;
import core.CalculatorThread;

public class CrazyCalculatorGUI extends JFrame{
	/**
	 * 
	 */
	
	private boolean flagOperator = false;
	private boolean closeParenthesis = false;
	private int openParenthesis = 1;
	
	
	
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JPanel mainCenter;
	private SnapShots subPanel;
	private JMenuBar customMenu;
	private JButton closeButton;
	private int pX;
	private int pY;
	private JTextField textField;
	private JPanel keyPad;
	private JPanel keys[] = new JPanel[20];
	private CalculatorThread calculator;
	private JLabel keysLabel[] = new JLabel[20];
	//private Boolean showSubPanel = true;
	private Boolean clear = false;
 	private String characters[] = {"", "AC", "Del", "/", "7", "8", "9", "*" ,"4", "5", "6", "-", "1", "2",
									"3", "+","0", "(", ")", "="};
	public CrazyCalculatorGUI(){
		this.setLayout(null);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color((float)0,(float) 0, (float)0,(float) 0.70));
		mainPanel.setLocation(0,0);
		mainPanel.setSize(300, 450);
		subPanel = new SnapShots();
		subPanel.setLocation(300, 0);
		subPanel.setSize(500, 450);
		subPanel.setBackground(new Color((float)0,(float) 0, (float)0,(float) 0.80));
		subPanel.setVisible(true);
		
		customMenu = new JMenuBar();
		customMenu.setLayout(new BorderLayout());
		
		closeButton = new JButton("X");
		closeButton.setFont(new Font("Arial", Font.BOLD, 17));
		closeButton.setForeground(Color.WHITE);
		closeButton.setFocusPainted(false);
		closeButton.setBackground(new Color(0, 0, 0, 0));
		closeButton.setBorderPainted(false);
		closeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		closeButton.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				closeButton.setOpaque(true);
				closeButton.setBackground(new Color(232, 17, 35));
			}
			public void mouseExited(MouseEvent e){
				closeButton.setOpaque(false);
				closeButton.setBackground(new Color(0, 0, 0, 0));
				repaint();
			}
		});
		
		JLabel title = new JLabel("                                   Crazy Calculator");
		title.setFont(new Font("Arial", Font.BOLD, 12));
		title.setForeground(Color.WHITE);
		customMenu.add(title);//try
		customMenu.add(closeButton, BorderLayout.EAST);
		customMenu.setBorderPainted(false);
		customMenu.setOpaque(false);
		customMenu.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent me) {
	            pX = me.getX();
	            pY = me.getY();

	        }

	         public void mouseDragged(MouseEvent me) {

	            setLocation(getLocation().x + me.getX() - pX,
	                   getLocation().y + me.getY() - pY);
	        }
	    });
		customMenu.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {

                setLocation(getLocation().x + me.getX() - pX,
                        getLocation().y + me.getY() - pY);
            }
        });
		
		mainCenter = new JPanel();
		mainPanel.add(mainCenter, BorderLayout.CENTER);
		mainCenter.setBackground(new Color(0, 0, 0, 0));
		mainCenter.setLayout(null);
		
		Border border;
		border = BorderFactory.createLoweredBevelBorder();
		textField = new JTextField();
		textField.setText("");
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		//textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Arial", Font.PLAIN, 20));
		//textField.setBackground(Color.WHITE, 0.03f);
		textField.setBackground(new Color(1.0f, 1, 1, (float)1.0));
		//textField.setBackground(new Color(0, 0, 0, (float)0.03));
		//textField.setBorder(border);
		textField.setSize(280, 50);
		textField.setLocation(10, 20);
		
		keyPad = new JPanel();
		keyPad.setLayout(new GridLayout(5, 4));
		keyPad.setSize(280, 305);
		keyPad.setLocation(10, 100);
		keyPad.setBackground(new Color(0, 0, 0, (float)0.01));
		keyPad.setBorder(border);
		keyPad.add(new JLabel(""));
		MouseHandler handler = new MouseHandler();
		for(int a = 1; a < 20; a++){
			keys[a] = new JPanel();
			keys[a].setLayout(new GridBagLayout());
			
			keys[a].addMouseListener(handler);
			
			keysLabel[a] = new JLabel(characters[a]);
			if(a == 0){
				//keys[a].setEnabled(false);
				//keysLabel[a].setFont(new Font("Arial", Font.PLAIN, 15));
			}else{
				keysLabel[a].setFont(new Font("Arial", Font.PLAIN, 20));
			}
			keysLabel[a].setForeground(Color.WHITE);
			keys[a].add(keysLabel[a], SwingConstants.CENTER);
			keyPad.add(keys[a]);
			if(a == 19){
				keys[a].setBackground(new Color(255, 0, 0, 100));
			}else{
				keys[a].setBackground(new Color(0, 0, 0, 0));
			}
		}
		
		mainCenter.add(textField);
		mainCenter.add(keyPad);
		
		mainPanel.add(customMenu, BorderLayout.NORTH);
		add(mainPanel);
		add(subPanel);
	}

	private class MouseHandler extends MouseAdapter{
		public void mouseEntered(MouseEvent e){
			for(int a = 0; a < 20; a++){
				if(e.getSource() == keys[a]){
					if(a == 19){
						keys[a].setBackground(new Color(255, 0, 0, 175));
					}else
					{
						keys[a].setBackground(new Color(0, 0, 0, (float)0.5));
					}
					repaint();
				}
					
			}
		}
		public void mouseExited(MouseEvent e){
			for(int a = 0; a < 20; a++){
				if(e.getSource() == keys[a]){
					if(a == 19){
						keys[a].setBackground(new Color(255, 0, 0, 100));
					}else{
						keys[a].setBackground(new Color(255, 255, 255, 0));
					}
					repaint();
				}
			}
		}
		public void mouseClicked(MouseEvent e){
			if(clear && e.getSource() != keys[0]){
				textField.setText("");
				flagOperator = false;
				closeParenthesis = false;
				openParenthesis = 1;
				clear = false;
			}
			for(int a = 0; a < 20; a++){
				
				if(e.getSource() == keys[a]){
					if(a != 0 && a != 1 && a != 2 && a != 19 /*&& textField.getText().length() < 25*/){ //for character input
						if( Character.isDigit(characters[a].charAt(0)))
						{
							if((!textField.getText().equals("") && textField.getText().charAt(textField.getText().length() - 1) != ')')
							    || textField.getText().equals(""))
							{
								
							
							textField.setText(textField.getText() + characters[a]);
							if((!textField.getText().equals("")) && Character.isDigit(textField.getText().charAt(textField.getText().length() - 1)))
							{
								flagOperator = true;
							}
							}
							
						}
						else if(a == 17) //open parenthesis
						{
							if(((textField.getText().equals("")) || !Character.isDigit(textField.getText().charAt(textField.getText().length() - 1)) && textField.getText().charAt(textField.getText().length() - 1) != ')'))
							{
								textField.setText(textField.getText() + characters[a]);
								openParenthesis++;
								closeParenthesis = true;
							}
							
							
						}
						else if(a == 18)
						{
							if(!textField.getText().equals("") && openParenthesis > 1 && closeParenthesis == true && (Character.isDigit(textField.getText().charAt(textField.getText().length() - 1)) || textField.getText().charAt(textField.getText().length() - 1) == ')'))
							{
								textField.setText(textField.getText() + characters[a]);
								openParenthesis--;
								flagOperator = true;
								if(openParenthesis == 1)
								{
									closeParenthesis = false;
								}
							}
						}
						else
						{
							if(flagOperator)
							{
								textField.setText(textField.getText() + characters[a]);
								flagOperator = false;
							}
						}
						
						
					}else if(a == 1){ //clear all
						textField.setText("");
						flagOperator = false;
						closeParenthesis = false;
						openParenthesis = 1;
						clear = false;

					}else if(a == 2){ //delete
						if((!textField.getText().equals("")) && Character.isDigit(textField.getText().charAt(textField.getText().length() - 1)))
						{
							flagOperator = true;
						}
						if((!textField.getText().equals("")) && textField.getText().charAt(textField.getText().length() - 1) == ')')
						{
							openParenthesis++;
							closeParenthesis = true;
						}
						String temp = "";
						for(int b = 0; b < textField.getText().length() - 1; b++){
							temp += textField.getText().charAt(b);
						}
						textField.setText(temp);
					}else if(a == 19){ //equals button
						if( (!textField.getText().equals("")  && !Character.isDigit(textField.getText().charAt(textField.getText().length() - 1)) 
								&& (textField.getText().charAt(textField.getText().length() - 1) != ')' )
								|| openParenthesis > 1))
						{
							JOptionPane.showMessageDialog(null,
							        "Invalid Input!",
							        "Invalid Input",
							        JOptionPane.ERROR_MESSAGE);
							//System.out.println(openParenthesis);
						}else if(textField.getText().equals(""))
						{
							
						}else
						{
							calculator = new CalculatorThread(subPanel, textField.getText());
							double temp = calculator.answer;
							if(Double.isNaN(temp)){
								textField.setText("SYNTAX ERROR");
								clear = true;
							}else{
								if(temp - (int) temp == 0){
									textField.setText(String.valueOf((int) temp));
								}else{
									if(String.valueOf(temp).length() > 20){
										BigDecimal d = new BigDecimal(temp);
										int integralDigits = d.toBigInteger().toString().length();
										d = d.setScale(20-integralDigits, RoundingMode.HALF_EVEN);
										textField.setText(String.valueOf(d));
									}else{
										textField.setText(String.valueOf(temp));
									}
								}
								clear = true;
							}
						}
					}else if(a == 0){
						
						/*if(showSubPanel){
							subPanel.setVisible(true);
							showSubPanel = false;
						}else{
							subPanel.setVisible(false);
							showSubPanel = true;
						}*/
					}
				}
			}
		}
	}
}
