package dataStructures;


public class Link<E> {
	private E element;
	public Link<E> next;

	
	public Link()
	{
		
	}
	
	public E getElement(){
		return element;
	}
	
	public void setElement(E element){
		this.element = element;
	}
}
