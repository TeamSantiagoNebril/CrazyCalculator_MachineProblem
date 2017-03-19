package dataStructures;

import runtimeException.PseudoArrayIndexOutOfBoundsException;

public class PseudoArray<E> {
	LinkedList<E> list;
	
	public PseudoArray(int size){
		list = new LinkedList<E>();
		for(int a = 0; a < size; a++){
			list.addElement();
		}
	}
	
	public void set(int index, E element){
		if(index < length() && index >= 0)
		{
			list.setSpecificElement(index, element);
		}
		else
		{
			if(index >= length())
			{
				throw new PseudoArrayIndexOutOfBoundsException("PseudoArrayOutOfBounds: 1");
			}
			else if(index < 0)
			{
				throw new PseudoArrayIndexOutOfBoundsException("PseudoArrayOutOfBounds: -1");
			}
		}
		
	}
	
	public E get(int index){
		return list.getElement(index);
	}
	
	public int length()
	{
		Link<E> temp = list.first;
		int count = 0;
		while(temp != null)
		{
			count++;
			temp = temp.next;
		}
		return count;
	}
}
