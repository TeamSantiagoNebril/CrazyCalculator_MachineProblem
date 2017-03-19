package dataStructures;


public class LinkedList<E>{
	public Link<E> first;
	private Link<E> current;
	
	public LinkedList(){
		first = null;
	}
	
	public void addElement(){
		Link<E> link = new Link<E>();
		link.next = first;
		first = link;
	}
	
	public E getElement(int index){
		current = first;
		for(int a = 0; a < index; a++){
			current = current.next;
		}
		return current.getElement();
	}
	
	public void setSpecificElement(int index, E element){
		current = first;
		for(int a = 0; a < index; a++){
			current = current.next;
		}
		current.setElement(element);
	}
}
