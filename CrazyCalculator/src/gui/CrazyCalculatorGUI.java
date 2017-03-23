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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.border.Border;

import core.Calculator;

public class CrazyCalculatorGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JMenuBar customMenu;
	private JButton closeButton;
	private int pX;
	private int pY;
	private JTextField textField;
	private JPanel keyPad;
	private JPanel keys[] = new JPanel[20];
	private JLabel keysLabel[] = new JLabel[20];
 	private String characters[] = {"", "AC", "Del", "/", "7", "8", "9", "*" ,"4", "5", "6", "-", "1", "2",
									"3", "+","0", "(", ")", "="};
	public CrazyCalculatorGUI(){
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color((float)0,(float) 0, (float)0,(float) 0.0));
		
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
		MouseHandler handler = new MouseHandler();
		for(int a = 0; a < 20; a++){
			keys[a] = new JPanel();
			keys[a].setLayout(new GridBagLayout());
			if(a != 0){
				keys[a].addMouseListener(handler);
			}
			keysLabel[a] = new JLabel(characters[a]);
			keysLabel[a].setFont(new Font("Arial", Font.PLAIN, 20));
			keysLabel[a].setForeground(Color.WHITE);
			keys[a].add(keysLabel[a], SwingConstants.CENTER);
			keyPad.add(keys[a]);
			if(a == 19){
				keys[a].setBackground(new Color(255, 0, 0, 100));
			}else{
				keys[a].setBackground(new Color(0, 0, 0, 0));
			}
		}
		
		mainPanel.add(textField);
		mainPanel.add(keyPad);
		
		setJMenuBar(customMenu);
		add(mainPanel);
	}

	public class MouseHandler extends MouseAdapter{
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
			for(int a = 0; a < 20; a++){
				if(e.getSource() == keys[a]){
					if(a != 1 && a != 2 && a != 19 && textField.getText().length() < 25){ //for character input
						textField.setText(textField.getText() + characters[a]);
					}else if(a == 1){ //clear all
						textField.setText("");
					}else if(a == 2){ //delete
						String temp = "";
						for(int b = 0; b < textField.getText().length() - 1; b++){
							temp += textField.getText().charAt(b);
						}
						textField.setText(temp);
					}else if(a == 19){
						Calculator calc = new Calculator();
							
						//aerol, equals na adi hihi
					}
					repaint();
				}
			}
		}
	}
}
