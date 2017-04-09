package dataStructures;

import runtimeException.StackOutOfBoundsException;

public class Stack<E> implements Runnable{

	private Queue<E> queue;
	private Queue<E> temporaryQueue;
	private String elements = "";
	private String elements1 = "";
	private String elements2 = "";
	private Boolean isThread = false;
	public Thread thread;
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
			elements2 += input + " ";
		
			if(isThread){
				
				run();
			}
			
		}
		else
		{
			throw new StackOutOfBoundsException("StackOutOfBounds: Overflow");
		}
	}
	
	public E pop()
	{
		E element = null;
		if(isEmpty())
		{
			throw new StackOutOfBoundsException("StackOutOfBounds: Underflow");
		}
		else
		{
			while(true)
			{
				element = queue.dequeue();
			
				
				if(queue.isEmpty())
				{
					break;
				}
				else
				{
					temporaryQueue.enqueue(element);
					elements1 += element + " ";
					
					elements2 = elements2.substring(elements2.indexOf(" ") + 1, elements2.length());
					
					if(isThread){
						run();
					}
				}
			}
			elements2 = elements2.substring(elements2.indexOf(" ") + 1, elements2.length());
			elements = elements.trim();
			elements = elements.substring(0, elements.lastIndexOf(" ") + 1);
			if(isThread){
				run();
			}
			while(!temporaryQueue.isEmpty())
			{
				E temp = temporaryQueue.dequeue();
				queue.enqueue(temp);
				
				elements1 = elements1.substring(elements1.indexOf(" ") + 1, elements1.length());
				elements2 += temp + " ";
				if(isThread){
					
					run();
				}
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
	
	public void run(){
		gui.SnapShots.textFieldOfStructure[0].setText(elements);
		gui.SnapShots.textFieldOfStructure[1].setText(elements2);
		gui.SnapShots.textFieldOfStructure[2].setText(elements1);
		queue.print();
		try {
		  Thread.sleep(1000);
		} catch (InterruptedException e) {
		e.printStackTrace();
		System.exit(-1);
		}
		
	}
	
	public void initiate(Thread predecessor){
		thread = new Thread(this);
		isThread = true;
		thread.start();
	}
	
	public String getStackElements(){
		return elements;
	}
	
	public E peek(){
		E element = null;
		if(isEmpty())
		{
			throw new StackOutOfBoundsException("StackOutOfBounds: Underflow");
		}
		else
		{
			while(true)
			{
				element = queue.dequeue();
			
				
				if(queue.isEmpty())
				{
					break;
				}
				else
				{
					temporaryQueue.enqueue(element);
				}
			}
			temporaryQueue.enqueue(element);
			while(!temporaryQueue.isEmpty())
			{
				E temp = temporaryQueue.dequeue();
				queue.enqueue(temp);
				
			}
			
		}
		
		return element;
	}
}
