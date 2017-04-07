package dataStructures;

import runtimeException.StackOutOfBoundsException;

public class Stack<E>{

	private Queue<E> queue;
	private Queue<E> temporaryQueue;
	private String elements = "";
	private String elements1 = "";
	private int count = 0;
	public Stack(int size)
	{
		queue = new Queue<E>(size);
		temporaryQueue = new Queue<>(size);
	}
	
	
	public void push(E input)
	{
		if(!queue.isFull())
		{
			queue.enqueue(input);
			elements += input + " ";
			elements1 = null;
			count = 0;
		}
		else
		{
			throw new StackOutOfBoundsException("StackOutOfBounds: Overflow");
		}
	}
	
	public E pop()
	{
		E element = null;
		//elements1 = null;
		if(isEmpty())
		{
			throw new StackOutOfBoundsException("StackOutOfBounds: Underflow");
		}
		else
		{
			while(true)
			{
				
				element = queue.dequeue();
				elements = elements.trim();
				elements = elements.substring(0, elements.lastIndexOf(" ") + 1);
				if(queue.isEmpty())
				{
					break;
				}
				else
				{
					temporaryQueue.enqueue(element);
					elements1 += element + " ";
				}
			}
			while(!temporaryQueue.isEmpty())
			{
				E temp = temporaryQueue.dequeue();
				queue.enqueue(temp);
				elements1 += element + " ";
				elements1 = elements1.trim();
				elements1 = elements1.substring(elements1.indexOf(" "), elements1.length());
				
			}
		}
		return element;
	}
	
	public Boolean isNumeric(String string){
		try{
			Double.parseDouble(string);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
	
	public boolean isEmpty()
	{
		if(queue.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void string(String stack[], String queue[][]){
		stack[0] = elements;
		queue[0][count] = elements;
		queue[1][count] = elements1;
		count++;
		System.out.println("Elements1: " + queue[1]);
		
	}
}
