package dataStructures;

import runtimeException.QueueOutOfBoundsException;

public class Queue<E> {
	
	private PseudoArray<E> array;
	private int front = -1, rear = -1;
	private int limit;
	
	public Queue(int numberOfElements)
	{
		limit = numberOfElements;
		array = new PseudoArray<E>(numberOfElements );
		
	}
	
	public void enqueue(E newElement)
	{

		if(isFull()){
            throw new QueueOutOfBoundsException("QueueOutOfBounds: Overflow");
        }else{
        	rear = (rear + 1) % limit;
            array.set(rear, newElement);
            if(front == -1)
            {
            	front = 0;
            }
            
        }
	}
	
	public E dequeue()
	{
		if(isEmpty())
		{
			 throw new QueueOutOfBoundsException("QueueOutOfBounds: Underflow");
		}			
		E deletedElement = array.get(front);
		if(front == rear)
		{
			front = rear = -1;
		}
		else
		{
			front = (front + 1) & limit;
		}
		return deletedElement;
		
	}
	
	public boolean isEmpty()
	{
		if(front == -1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isFull()
	{
		if((rear + 1) % limit == front)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
