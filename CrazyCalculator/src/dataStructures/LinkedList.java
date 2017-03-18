package dataStructures;


public class LinkedList {
	private Link first;
	private Link current;
	
	public LinkedList(){
		first = null;
	}
	
	public void addElement(){
		Link link = new Link();
		link.next = first;
		first = link;
	}
	
	public char getElement(int index){
		current = first;
		for(int a = 0; a < index; a++){
			current = current.next;
		}
		return current.getElement();
	}
	
	public void setSpecificElement(int index, char element){
		current = first;
		for(int a = 0; a < index; a++){
			current = current.next;
		}
		current.setElement(element);
	}
}
