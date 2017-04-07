package dataStructures;

import javax.swing.JTextField;

import runtimeException.StackOutOfBoundsException;

public class Stack<E>{

	private Queue<E> queue;
	private Queue<E> temporaryQueue;
	
	public Stack(int size)
	{
		queue = new Queue<E>(size);
		temporaryQueue = new Queue<E>(size);
	}
	
	
	public void push(E input)
	{
		if(!queue.isFull())
		{
			queue.enqueue(input);
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
				}
			}
			while(!temporaryQueue.isEmpty())
			{
				queue.enqueue(temporaryQueue.dequeue());
			}
			
		}
		
		return element;
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
	
	public String printElements(JTextField[] textField)
	{
		
		return "";
	}
}
