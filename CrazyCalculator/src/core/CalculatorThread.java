package core;

import dataStructures.Stack;
import gui.SnapShots;

public class CalculatorThread extends Thread{

	
	private Stack<String> stack;
	private String output;
	private Boolean header = true;
	private SnapShots panel;
	private String input;
	public double answer;
	
	public CalculatorThread(SnapShots snap, String input)
	{
		panel = snap;
		this.input = input;
		answer = evaluatePostfix(translateInfixToPostfix(input));
		this.start();
		
	}
	
	
	public void run()
	{
		answer = evaluatePostfix(translateInfixToPostfix(input));
	}
	
	//public double evaluate(String input)
	//{
		//double answer;
	//	answer = evaluatePostfix(translateInfixToPostfix(input));
	//	return answer;
	//}
	
	boolean endReadFlag = true;
	public String translateInfixToPostfix(String input)
	{
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
				
				i--;
			}
			else if(input.charAt(i) == '(' ) //character is open parenthesis
			{
				stack.push("" + input.charAt(i));
			}
			else if(input.charAt(i) == ')') //character is closed parenthesis
			{
				while(!stack.isEmpty())
				{
					element = stack.pop();
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
				if(stack.isEmpty())
				{
					stack.push("" + input.charAt(i));
				}
				else
				{
					while(!stack.isEmpty())
					{
						element = stack.pop();
						if(element.equals("("))
						{
							stack.push(element);
							break;
						}
						else if(isOperator(element))
						{
							if(analyzePrecedence(element) < analyzePrecedence(input.charAt(i) + "") )
							{
								stack.push(element);
								break;
							}
							else if(analyzePrecedence(element) >= analyzePrecedence(input.charAt(i) + ""))
							{
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
			
			printTranslationInCMD( read, parse, input);
			/************************************************************/
			element = "";
			
		}
		if(!stack.isEmpty())
		{

			while(!stack.isEmpty())
			{
				output += stack.pop();
				if(i == input.length() && endReadFlag)
				{
					read = "END";
				}
				else
				{
				
				}
				printTranslationInCMD( read, parse, input);
			}
		}
		else
		{
			printTranslationInCMD( "END", parse, input);
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public double evaluatePostfix(String postfix)
	{
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
				stack.push(characters);
				parsed += characters + " ";
				printEvaluation(characters, parsed, postfix);
				characters = "";
				
			}else if(isOperator(postfix.charAt(a) + "")){
				parsed += postfix.charAt(a) + " ";
				printEvaluation(postfix.charAt(a) + "", parsed, postfix);
				if(postfix.charAt(a) == '+'){
					double temp = Double.parseDouble(stack.pop());
					answer = Double.parseDouble(stack.pop()) + temp; 
					if(answer - (int) answer == 0){
						stack.push(String.valueOf((int)answer));
					}else{
						stack.push(String.valueOf(answer));
					}
					
				}else if(postfix.charAt(a) == '-'){
					double temp = Double.parseDouble(stack.pop());
					answer = Double.parseDouble(stack.pop()) - temp;  
					if(answer - (int) answer == 0){
						stack.push(String.valueOf((int)answer));
					}else{
						stack.push(String.valueOf(answer));
					}
					
				}else if(postfix.charAt(a) == '*'){
					double temp = Double.parseDouble(stack.pop());
					answer = Double.parseDouble(stack.pop()) * temp;  
					if(answer - (int) answer == 0){
						stack.push(String.valueOf((int)answer));
					}else{
						stack.push(String.valueOf(answer));
					}
					
				}else if(postfix.charAt(a) == '/'){
					double temp = Double.parseDouble(stack.pop());
					if(temp == 0){
						System.out.println("SYNTAX ERROR");
						return Double.NaN;
					}else{
						answer = Double.parseDouble(stack.pop())/temp;
						if(answer - (int) answer == 0){
							stack.push(String.valueOf((int)answer));
						}else{
							stack.push(String.valueOf(answer));
						}
					}
				}
			}	
		}
		answer = Double.parseDouble(stack.pop());
		printEvaluation("END", parsed, postfix);
		return answer;
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