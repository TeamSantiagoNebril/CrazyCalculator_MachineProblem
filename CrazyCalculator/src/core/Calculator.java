package core;

import dataStructures.Stack;

public class Calculator{

	
	private Stack<String> stack;
	
	public Calculator()
	{
		//stack = new Stack<String>(length);
	}
	
	public double evaluate(String input)
	{
		double answer;
		answer = evaluatePostfix(translateInfixToPostfix(input));
		return answer;
	}
	
	public String translateInfixToPostfix(String input)
	{
		stack = new Stack<String>(input.length());
		String output = "";
		String element = "";
		for(int i = 0; i < input.length(); i++)
		{
			if(Character.isDigit(input.charAt(i))) //character is number
			{
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
						output += element;
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
								output += element;
							}
						}
						
					}
					stack.push(input.charAt(i) + "");
				}
			}
			element = "";
		}
		while(!stack.isEmpty())
		{
			output += stack.pop();
		}
		return output;
	}
	
	public double evaluatePostfix(String postfix)
	{
		double answer = 0;
		String characters = "";
		stack = new Stack<String>(postfix.length());
		for(int a = 0; a < postfix.length(); a++){
			answer = 0;
			if(isNumeric(postfix.charAt(a) + "")){
				characters += postfix.charAt(a);
				continue;
			}
			if(isNumeric(characters)){
				stack.push(characters);
				characters = "";
			}else if(isOperator(postfix.charAt(a) + "")){
				if(postfix.charAt(a) == '+'){
					double temp = Double.parseDouble(stack.pop());
					answer = Double.parseDouble(stack.pop()) + temp;  
					if(a != postfix.length() - 1){
						stack.push(String.valueOf(answer));
					}
				}else if(postfix.charAt(a) == '-'){
					double temp = Double.parseDouble(stack.pop());
					answer = Double.parseDouble(stack.pop()) - temp;  
					if(a != postfix.length() - 1){
						stack.push(String.valueOf(answer));
					}
				}else if(postfix.charAt(a) == '*'){
					double temp = Double.parseDouble(stack.pop());
					answer = Double.parseDouble(stack.pop()) * temp;  
					if(a != postfix.length() - 1){
						stack.push(String.valueOf(answer));
					}
				}else if(postfix.charAt(a) == '/'){
					double temp = Double.parseDouble(stack.pop());
					answer = Double.parseDouble(stack.pop())/temp; 
					if(a != postfix.length() - 1){
						stack.push(String.valueOf(answer));
					}
				}
			}
		}
		return answer;
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
