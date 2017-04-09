package core;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JTextField;

import dataStructures.Stack;

public class CalculatorThread extends Thread{

	
	private Stack<String> stack;
	private String output;
	private Boolean header = true;
	private String input;
	public double answer;
	public Thread t;
	private boolean isCalculate = true;
	private JTextField textField;
	
	public CalculatorThread(String input, JTextField textField)
	{
		this.input = input;
		this.textField = textField;
		
	}
	
	public boolean isCalculating()
	{
		return isCalculate;
	}
	public void run()
	{
		answer = evaluatePostfix(translateInfixToPostfix(input));
		if(Double.isNaN(answer)){
			textField.setText("SYNTAX ERROR");
		}else{
			if(answer - (int) answer == 0){
				textField.setText(String.valueOf((int) answer));
			}else{
				if(String.valueOf(answer).length() > 20){
					BigDecimal d = new BigDecimal(answer);
					int integralDigits = d.toBigInteger().toString().length();
					d = d.setScale(20-integralDigits, RoundingMode.HALF_EVEN);
					textField.setText(String.valueOf(d));
				}else{
					textField.setText(String.valueOf(answer));
				}
			}
		}
			
	}

	
	boolean endReadFlag = true;
	public String translateInfixToPostfix(String input)
	{
		gui.SnapShots.textFieldOfProcess[0].setText("Translating Infix to Postfix");
		gui.SnapShots.textFieldOfProcess[1].setText("");
		gui.SnapShots.textFieldOfProcess[2].setText("");
		printProcess(3, "");
		String stackContent;
		System.out.println("\n\nExpression: " + input);
		System.out.println("Translation:");
		int i, num;
		stack = new Stack<String>(input.length());
		stack.initiate(this);
		output = "";
		String element = "";
		String read = "", parse = "";
		for( i = 0; i < input.length(); i++)
		{
			num = i;
			if(Character.isDigit(input.charAt(i))) //character is number
			{
				num = i;
				while( (i < input.length()) && (Character.isDigit(input.charAt(i))))
				{
					element += input.charAt(i);
					i++;
				}
				output += (element + " ");
				gui.SnapShots.textFieldOfProcess[2].setText("");
				gui.SnapShots.textFieldOfProcess[1].setText("Processing " + element);
				try {
					  Thread.sleep(1000);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
				gui.SnapShots.textFieldOfProcess[2].setText("Adding " + element + " to postfix");
				try {
					  Thread.sleep(1000);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
				gui.SnapShots.textFieldOfProcess[3].setText(output);
				try {
					  Thread.sleep(1000);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
				i--;
			}
			else if(input.charAt(i) == '(' ) //character is open parenthesis
			{
				gui.SnapShots.textFieldOfProcess[2].setText("");
				gui.SnapShots.textFieldOfProcess[1].setText("Processing (");
				try {
					  Thread.sleep(1000);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
				gui.SnapShots.textFieldOfProcess[2].setText("Pushing ( to stack");
				try {
					  Thread.sleep(1000);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
				stack.push("" + input.charAt(i));
				
				try {
					stack.thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(input.charAt(i) == ')') //character is closed parenthesis
			{
				gui.SnapShots.textFieldOfProcess[2].setText("");
				gui.SnapShots.textFieldOfProcess[1].setText("Processing " + input.charAt(i));
				try {
					  Thread.sleep(1000);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
				while(!stack.isEmpty())
				{
					element = stack.peek();
					gui.SnapShots.textFieldOfProcess[2].setText("Popping " + element);
					try {
						  Thread.sleep(1000);
					} catch (InterruptedException e) {
							e.printStackTrace();
					}
					element = stack.pop();
					
					try {
						stack.thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(!element.equals("("))
					{
						gui.SnapShots.textFieldOfProcess[2].setText("Adding " + element + "to postfix");
						try {
							  Thread.sleep(1000);
						} catch (InterruptedException e) {
								e.printStackTrace();
						}
						output += element + " ";
						gui.SnapShots.textFieldOfProcess[3].setText(output);
						try {
							  Thread.sleep(1000);
						} catch (InterruptedException e) {
								e.printStackTrace();
						}
					}
					else
					{
						break;
					}
				}
			}
			else if(isOperator(input.charAt(i) + "")) //character is operator
			{
				gui.SnapShots.textFieldOfProcess[2].setText("");
				gui.SnapShots.textFieldOfProcess[1].setText("Processing " + input.charAt(i));
				try {
					  Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				if(stack.isEmpty())
				{
					gui.SnapShots.textFieldOfProcess[2].setText("Pushing " + input.charAt(i) + " to stack");
					try {
						  Thread.sleep(1000);
					} catch (InterruptedException e) {
							e.printStackTrace();
					}
					stack.push("" + input.charAt(i));
					try {
						stack.thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					while(!stack.isEmpty())
					{
						element = stack.peek();
						gui.SnapShots.textFieldOfProcess[2].setText("Comparing " + element + " with " + input.charAt(i));
						try {
							  Thread.sleep(1000);
						} catch (InterruptedException e) {
								e.printStackTrace();
						}
						if(element.equals("("))
						{
							break;
						}
						else if(isOperator(element))
						{
							if(analyzePrecedence(element) < analyzePrecedence(input.charAt(i) + "") )
							{
								break;
							}
							else if(analyzePrecedence(element) >= analyzePrecedence(input.charAt(i) + ""))
							{
								element = stack.peek();
								gui.SnapShots.textFieldOfProcess[2].setText("Popping " + element);
								try {
									  Thread.sleep(1000);
								} catch (InterruptedException e) {
										e.printStackTrace();
								}
								element = stack.pop();
								try {
									stack.thread.join();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								gui.SnapShots.textFieldOfProcess[2].setText("Adding " + element + " to postfix");
								try {
									  Thread.sleep(1000);
								} catch (InterruptedException e) {
										e.printStackTrace();
								}
								output += element + " ";
								gui.SnapShots.textFieldOfProcess[3].setText(output);
								try {
									  Thread.sleep(1000);
								} catch (InterruptedException e) {
										e.printStackTrace();
								}
							}
						}
						
					}
					
					gui.SnapShots.textFieldOfProcess[2].setText("Pushing " + input.charAt(i) + " to stack");
					try {
						  Thread.sleep(1000);
					} catch (InterruptedException e) {
							e.printStackTrace();
					}
					stack.push(input.charAt(i) + "");
				}
			}
			/************************************************************/
			if(num  == i)
			{
				read = input.charAt(i) + "";
			}
			else
			{
				read = input.substring(num, i+1);
			}
			parse = input.substring(0, i+1);
			stackContent = stack.getStackElements();
			printTranslationInCMD( read, parse, input, stackContent);
			/************************************************************/
			element = "";
		}
		if(!stack.isEmpty())
		{

			while(!stack.isEmpty())
			{
				element = stack.peek();
				gui.SnapShots.textFieldOfProcess[2].setText("Popping " + element);
				try {
					  Thread.sleep(1000);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
				output += stack.pop() + " ";
				gui.SnapShots.textFieldOfProcess[2].setText("Adding " + element + " to postfix");
				try {
					  Thread.sleep(1000);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
				gui.SnapShots.textFieldOfProcess[3].setText(output);
				try {
					  Thread.sleep(1000);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
				try {
					stack.thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(i == input.length() && endReadFlag)
				{
					read = "END";
				}
				else
				{
				
				}
				stackContent = stack.getStackElements();
				printTranslationInCMD( read, parse, input, stackContent);
			}
		}
		else
		{
			stackContent = stack.getStackElements();
			printTranslationInCMD( "END", parse, input, stackContent);
		}
		
		gui.SnapShots.textFieldOfProcess[0].setText("");
		gui.SnapShots.textFieldOfProcess[1].setText("");
		printProcess(2, "");
		return output;
	}
	
	private boolean flag2 = true;
	public void printTranslationInCMD(String read , String parse, String input, String stackElements)
	{
		int inputLengthWithSpaces = input.length() + (input.length()/2);
		if(flag2)
		{
			
			System.out.println("|" + generateColumn("Read", inputLengthWithSpaces) + "|" +
									 generateColumn("Parsed", inputLengthWithSpaces) + "|" +
									 generateColumn("Written", inputLengthWithSpaces) + "|" +
									 generateColumn("Stack", inputLengthWithSpaces) + "|") ;
			flag2 = false;
		}
		
		System.out.println("|" + generateColumn(read, inputLengthWithSpaces) + "|" +
				 				 generateColumn(parse, inputLengthWithSpaces) + "|" +
				 				 generateColumn(output, inputLengthWithSpaces) + "|" +
				 				 generateColumn(stackElements, inputLengthWithSpaces) + "|") ;
		
	}
	
	public String generateColumn(String input, int length)
	{
		String space = "";
		int size = length;
		
		if(length < 7)
		{
			size = 7;
		}
		
		for(int i = input.length(); i < size; i++)
		{
			space += " ";
		}
		space = input + space;
		return space;
	}
	
	public double evaluatePostfix(String postfix)
	{
		gui.SnapShots.textFieldOfProcess[0].setText("Evaluating Postfix");
		gui.SnapShots.textFieldOfProcess[1].setText("");
		printProcess(2, "");
		System.out.println("Postfix: " + postfix);
		String stackContent;
		header = true;
		System.out.println("\n\nEvaluation: ");
		double answer = 0;
		String characters = "";
		String parsed = "";
		String operand1 = "";
		String operand2 = "";
		stack = new Stack<String>(postfix.length());
		stack.initiate(this);
		for(int a = 0; a < postfix.length(); a++){
			answer = 0;
			if(isNumeric(postfix.charAt(a) + "")){
				characters += postfix.charAt(a);
				continue;
			}
			
			
			if(isNumeric(characters)){
				gui.SnapShots.textFieldOfProcess[2].setText("");
				printProcess(1, "Processing " + characters);
				printProcess(2, "Pushing " + characters + " to stack");
				stack.push(characters);
				
				try {
					stack.thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				parsed += characters + " ";
				stackContent = stack.getStackElements();
				printEvaluation(characters, parsed, postfix, stackContent);
				characters = "";
				
			}else if(isOperator(postfix.charAt(a) + "")){
				parsed += postfix.charAt(a) + " ";
				gui.SnapShots.textFieldOfProcess[2].setText("");
				printProcess(1, "Processing " + postfix.charAt(a));
				if(postfix.charAt(a) == '+'){
					operand1 = stack.peek();
					printProcess(2, "Popping " + operand1);
					double temp = Double.parseDouble(stack.pop());
					try {
						stack.thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					operand2 = stack.peek();
					printProcess(2, "Popping " + operand2 );
					answer = Double.parseDouble(stack.pop()) + temp;
					try {
						stack.thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					printProcess(2, "Adding " + operand1 + " and " + operand2);
					if(answer - (int) answer == 0){
						printProcess(2, "Pushing answer = " + (int)answer + " to stack");
						stack.push(String.valueOf((int)answer));
						try {
							stack.thread.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else{
						printProcess(2, "Pushing answer = " + (int)answer + " to stack");
						stack.push(String.valueOf(answer));
						try {
							stack.thread.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}else if(postfix.charAt(a) == '-'){
					operand1 = stack.peek();
					printProcess(2, "Popping " + operand1);
					double temp = Double.parseDouble(stack.pop());
					try {
						stack.thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					operand2 = stack.peek();
					printProcess(2, "Popping " + operand2);
					answer = Double.parseDouble(stack.pop()) - temp;  
					try {
						stack.thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					printProcess(2, "Subtracting " + operand1 + " from " + operand2);
					if(answer - (int) answer == 0){
						printProcess(2, "Pushing answer = " + (int)answer + " to stack");
						stack.push(String.valueOf((int)answer));
						
						try {
							stack.thread.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else{
						printProcess(2, "Pushing answer = " + answer + " to stack");
						stack.push(String.valueOf(answer));
						try {
							stack.thread.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}else if(postfix.charAt(a) == '*'){
					operand1 = stack.peek();
					printProcess(2, "Popping " + operand1);
					double temp = Double.parseDouble(stack.pop());
					try {
						stack.thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					operand2 = stack.peek();
					printProcess(2, "Popping " + operand2);
					answer = Double.parseDouble(stack.pop()) * temp;  
					try {
						stack.thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					printProcess(2, "Multiplying " + operand1 + " by " + operand2);
					if(answer - (int) answer == 0){
						printProcess(2, "Pushing answer = " + (int)answer + " to stack");
						stack.push(String.valueOf((int)answer));
						try {
							stack.thread.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else{
						printProcess(2, "Pushing answer = " + answer + " to stack");
						stack.push(String.valueOf(answer));
						try {
							stack.thread.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}else if(postfix.charAt(a) == '/'){
					double temp2;
					operand1 = stack.peek();
					printProcess(2, "Popping " + operand1);
					double temp = Double.parseDouble(stack.pop());
					try{
						stack.thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					operand2 = stack.peek();
					printProcess(2, "Popping " + operand2);
					temp2 = Double.parseDouble(stack.pop());
					try {
						stack.thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(temp == 0){
						printProcess(2, "Dividing " + operand2 + " by " + operand1);
						printProcess(2, "SYNTAX ERROR");
						System.out.println("SYNTAX ERROR");
						gui.SnapShots.textFieldOfProcess[0].setText("Show Answer");
						gui.SnapShots.textFieldOfProcess[1].setText("");
						gui.SnapShots.textFieldOfProcess[2].setText("");
						return Double.NaN;
					}else{
						answer = temp2/temp;
						printProcess(2, "Dividing " + operand2 + " by " + operand1);
						if(answer - (int) answer == 0){
							printProcess(2, "Pushing answer = " + (int)answer + " to stack");
							stack.push(String.valueOf((int)answer));
							try {
								stack.thread.join();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}else{
							printProcess(2, "Pushing answer = " + answer + " to stack");
							stack.push(String.valueOf(answer));
							try {
								stack.thread.join();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
				stackContent = stack.getStackElements();
				printEvaluation(postfix.charAt(a) + "", parsed, postfix, stackContent);
			}
			
		}
		printProcess(2, "Popping " + stack.peek());
		answer = Double.parseDouble(stack.pop());
		try {
			stack.thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stackContent = stack.getStackElements();
		printEvaluation("END", parsed, postfix, stackContent);
		gui.SnapShots.textFieldOfProcess[0].setText("Show answer");
		gui.SnapShots.textFieldOfProcess[1].setText("");
		gui.SnapShots.textFieldOfProcess[2].setText("");
		return answer;
	}
	
	public void printEvaluation(String read, String parse, String postfix, String stackContent){
		
		int length = postfix.length() + 4;
		if(length < 10){
			length = 10;
		}
		
		String column = "%-" + length + "s";
		if(header){
			System.out.printf(column + "|" + column + "|" + column + "\n", "Read", "Parsed", "Stack" );
			header = false;
		}
		System.out.printf(column + "|" + column + "|" + column + "\n", read, parse, stackContent);
		
		
	}
	
	public Boolean isNumeric(String string){
		try{
			Double.parseDouble(string);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
	
	public boolean isOperator(String input)
	{
		if (input.equals("+") || input.equals("-") || input.equals("/") || input.equals("*"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void printProcess(int index, String string){
		gui.SnapShots.textFieldOfProcess[index].setText(string);
		try {
			  Thread.sleep(1000);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}
	
	public int analyzePrecedence(String operator)
	{
		
		if(operator.equals("+") || operator.equals("-"))
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
}
