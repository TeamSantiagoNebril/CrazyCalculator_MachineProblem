package core;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JTextField;

import dataStructures.Stack;
import gui.SnapShots;

public class CalculatorThread extends Thread{

	
	private Stack<String> stack;
	private String output;
	private Boolean header = true;
	private SnapShots snapShotsPanel;
	private String input;
	public double answer;
	public Thread t;
	private boolean isCalculate = true;
	private JTextField textField;
	private final int THREADSPEED = 1000;
	private final int THREADSPEEDFORLONGSTRING = 2000;
	
	public CalculatorThread(SnapShots snap, String input, JTextField textField)
	{
		snapShotsPanel = snap;
		this.input = input;
		this.textField = textField;
	}
	
	public boolean isCalculating()
	{
		return isCalculate;
	}
	public void run()
	{
		try {
			answer = evaluatePostfix(translateInfixToPostfix(input));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(Double.isNaN(answer)){
			textField.setText("SYNTAX ERROR");
			//clear = true;
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
	public String translateInfixToPostfix(String input) throws InterruptedException
	{
		snapShotsPanel.getTextFieldOfProcess(0).setText("Translation from infix to postfix");
		System.out.println("\n\nExpression: " + input);
		System.out.println("Translation:");
		int i, num;
		stack = new Stack<String>(input.length());
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
				snapShotsPanel.getTextFieldOfProcess(1).setText("Reading " + element);
				Thread.sleep(THREADSPEED);
				snapShotsPanel.getTextFieldOfProcess(2).setText("Adding " + element + " to output");
				Thread.sleep(THREADSPEED);
				i--;
			}
			else if(input.charAt(i) == '(' ) //character is open parenthesis
			{
				snapShotsPanel.getTextFieldOfProcess(1).setText("Reading (");
				Thread.sleep(THREADSPEED);
				snapShotsPanel.getTextFieldOfProcess(2).setText("Pushing ( to stack");
				Thread.sleep(THREADSPEED);
				stack.push("" + input.charAt(i));
			}
			else if(input.charAt(i) == ')') //character is closed parenthesis
			{
				snapShotsPanel.getTextFieldOfProcess(1).setText("Reading " + input.charAt(i));
				Thread.sleep(THREADSPEED);
				while(!stack.isEmpty())
				{
					element = stack.pop();
					snapShotsPanel.getTextFieldOfProcess(2).setText("Popping " + element);
					Thread.sleep(THREADSPEED);
					if(!element.equals("("))
					{
						output += element + " ";
					}
					else
					{
						break;
					}
				}
			}
			else if(isOperator(input.charAt(i) + "")) //character is operator
			{
				snapShotsPanel.getTextFieldOfProcess(1).setText("Reading " + input.charAt(i));
				Thread.sleep(THREADSPEED);
				if(stack.isEmpty())
				{
					snapShotsPanel.getTextFieldOfProcess(2).setText("Pushing " + input.charAt(i) + " to stack");
					Thread.sleep(THREADSPEED);
					stack.push("" + input.charAt(i));
				}
				else
				{
					while(!stack.isEmpty())
					{
						element = stack.pop();
						snapShotsPanel.getTextFieldOfProcess(2).setText("Popping " + element + " to compare with " + input.charAt(i));
						Thread.sleep(THREADSPEEDFORLONGSTRING);
						if(element.equals("("))
						{
							snapShotsPanel.getTextFieldOfProcess(2).setText("Pushing " + element + " to stack");
							Thread.sleep(THREADSPEED);
							stack.push(element);
							break;
						}
						else if(isOperator(element))
						{
							if(analyzePrecedence(element) < analyzePrecedence(input.charAt(i) + "") )
							{
								snapShotsPanel.getTextFieldOfProcess(2).setText("Pushing " + element + " to stack");
								Thread.sleep(THREADSPEED);
								stack.push(element);
								break;
							}
							else if(analyzePrecedence(element) >= analyzePrecedence(input.charAt(i) + ""))
							{
								snapShotsPanel.getTextFieldOfProcess(2).setText("Adding popped " + element + " to output");
								Thread.sleep(THREADSPEED);
								output += element + " ";
							}
						}
						
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
/***********************************Under Construction***********************/
			snapShotsPanel.getTextFieldOfProcess(3).setText(output);
			Thread.sleep(500);
/***********************************Under Construction***********************/
			printTranslationInCMD( read, parse, input);
			/************************************************************/
			element = "";
			
		}
		if(!stack.isEmpty())
		{

			while(!stack.isEmpty())
			{
				String temporalElement = stack.pop();
				snapShotsPanel.getTextFieldOfProcess(2).setText("Popping " + temporalElement + " and adding to output");
				Thread.sleep(500);
				output += temporalElement +" ";
				if(i == input.length() && endReadFlag)
				{
					read = "END";
				}
				else
				{
				
				}
				printTranslationInCMD( read, parse, input);
		/***********************************Under Construction***********************/
				snapShotsPanel.getTextFieldOfProcess(3).setText(output);
				Thread.sleep(500);
		/***********************************Under Construction***********************/
			}
		}
		else
		{
			printTranslationInCMD( "END", parse, input);
		}
		
		return output;
	}
	
	private boolean flag2 = true;
	public void printTranslationInCMD(String read , String parse, String input)
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
		
		Stack<String> temp = new Stack<String>(input.length());
		String stackElements = "";
		while(!stack.isEmpty())
		{
			String poppedElement = stack.pop();
			temp.push(poppedElement);
			stackElements += poppedElement;
		}
		while(!temp.isEmpty())
		{
			stack.push(temp.pop());
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
	
	public double evaluatePostfix(String postfix) throws InterruptedException
	{
		snapShotsPanel.getTextFieldOfProcess(0).setText("Evaluation of Postfix Expression");
		clearTextFields();
		Thread.sleep(THREADSPEED);
		System.out.println("Postfix: " + postfix);
		
		header = true;
		System.out.println("\n\nEvaluation: ");
		double answer = 0;
		String characters = "";
		String parsed = "";
		stack = new Stack<String>(postfix.length());
		for(int a = 0; a < postfix.length(); a++){
			answer = 0;
			if(isNumeric(postfix.charAt(a) + "")){
				characters += postfix.charAt(a);
				continue;
			}
			
			
			if(isNumeric(characters)){
				snapShotsPanel.getTextFieldOfProcess(1).setText("Reading "+ characters);
				Thread.sleep(THREADSPEED);
				snapShotsPanel.getTextFieldOfProcess(2).setText("Pushing " + characters + " to stack");
				Thread.sleep(THREADSPEED);
				stack.push(characters);
				parsed += characters + " ";
				printEvaluation(characters, parsed, postfix);
				characters = "";
				
			}else if(isOperator(postfix.charAt(a) + "")){
				char sign = ' ';
				double temp = 0;
				String operandOnStack = "";
				parsed += postfix.charAt(a) + " ";
				printEvaluation(postfix.charAt(a) + "", parsed, postfix);
				if(postfix.charAt(a) == '+'){
					sign = '+';
					temp = Double.parseDouble(stack.pop());
					operandOnStack = stack.pop();
					answer = Double.parseDouble(operandOnStack) + temp; 
					if(answer - (int) answer == 0){
						stack.push(String.valueOf((int)answer));
					}else{
						stack.push(String.valueOf(answer));
					}
					
				}else if(postfix.charAt(a) == '-'){
					sign = '-';
					temp = Double.parseDouble(stack.pop());
					operandOnStack = stack.pop();
					answer = Double.parseDouble(operandOnStack) - temp; 
					if(answer - (int) answer == 0){
						stack.push(String.valueOf((int)answer));
					}else{
						stack.push(String.valueOf(answer));
					}
					
				}else if(postfix.charAt(a) == '*'){
					sign = '*';
					temp = Double.parseDouble(stack.pop());
					operandOnStack = stack.pop();
					answer = Double.parseDouble(operandOnStack) * temp;  
					if(answer - (int) answer == 0){
						stack.push(String.valueOf((int)answer));
					}else{
						stack.push(String.valueOf(answer));
					}
					
				}else if(postfix.charAt(a) == '/'){
					sign = '/';
					temp = Double.parseDouble(stack.pop());
					if(temp == 0){
						System.out.println("SYNTAX ERROR");
						return Double.NaN;
					}else{
						operandOnStack = stack.pop();
						//answer = Double.parseDouble(operandOnStack + temp); 
						answer = Double.parseDouble(operandOnStack)/temp;
						if(answer - (int) answer == 0){
							stack.push(String.valueOf((int)answer));
						}else{
							stack.push(String.valueOf(answer));
						}
					}
				}
				snapShotsPanel.getTextFieldOfProcess(1).setText("Reading "+ sign);
				Thread.sleep(THREADSPEED);
				snapShotsPanel.getTextFieldOfProcess(2).setText("Evaluating " + operandOnStack + sign + temp + "");
				Thread.sleep(THREADSPEED);
				snapShotsPanel.getTextFieldOfProcess(3).setText( answer +"");
				Thread.sleep(THREADSPEED);
				snapShotsPanel.getTextFieldOfProcess(2).setText("Pushing " + answer + " to stack");
				Thread.sleep(THREADSPEED);
			}
		}
		answer = Double.parseDouble(stack.pop());
		printEvaluation("END", parsed, postfix);
		snapShotsPanel.getTextFieldOfProcess(0).setText("Completed");
		clearTextFields();
		return answer;
	}
	
	public void clearTextFields() throws InterruptedException
	{
		snapShotsPanel.getTextFieldOfProcess(1).setText("");
		snapShotsPanel.getTextFieldOfProcess(2).setText("");
		snapShotsPanel.getTextFieldOfProcess(3).setText("");
		Thread.sleep(THREADSPEEDFORLONGSTRING);
	}
	
	public void printEvaluation(String read, String parse, String postfix){
		
		int length = postfix.length() + 4;
		String stackContent = "";
		if(length < 10){
			length = 10;
		}
		
		String column = "%-" + length + "s";
		if(header){
			System.out.printf(column + "|" + column + "|" + column + "\n", "Read", "Parsed", "Stack" );
			header = false;
		}
		Stack<String> tempStack = new Stack<> (length);
		while(!stack.isEmpty()){
			
			tempStack.push(stack.pop());
			
		}
		while(!tempStack.isEmpty()){
			String temp = tempStack.pop();
			stack.push(temp);
			stackContent += temp + " ";
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
