package dataStructures;

import runtimeException.QueueOutOfBoundsException;

public class Queue<E> {
	
	private int limit;
	private PseudoArray<E> array;
	private int front;
	private int rear;
	private int nItems;
	
	
	public Queue(int numberOfElements) // constructor
	{
		limit = numberOfElements;
		array = new PseudoArray<E>(numberOfElements);
		front = 0;
		rear = -1;
		nItems = 0;
	}
	
	public void enqueue(E newElement) // put item at rear of queue
	{
		if(!isFull())
		{
			if(rear == limit-1) // deal with wraparound
				rear = -1;
			array.set(++rear, newElement); // increment rear and insert
			nItems++; // one more item
		}
		else
		{
			throw new QueueOutOfBoundsException("QueueOutOfBounds: Overflow");
		}
	}
	
	public E dequeue() // take item from front of queue
	{
		if(!isEmpty())
		{
			if(front == limit) // deal with wraparound
				front = 0;
			nItems--; // one less item
			return array.get(front++);
		}
		else
		{
			 throw new QueueOutOfBoundsException("QueueOutOfBounds: Underflow");
		}
			
		
	}
	
	public E peekFront() // peek at front of queue
	{
		return array.get(front);
	}
	
	public boolean isEmpty() // true if queue is empty
	{
		return (nItems == 0);
	}
	
	public boolean isFull() // true if queue is full
	{
		return (nItems==limit);
	}
	
	public int size() // number of items in queue
	{
		return nItems;
	}
	
	
}
