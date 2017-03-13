
public class PseudoArray {
	LinkedList list;
	public PseudoArray(int size){
		list = new LinkedList();
		for(int a = 0; a < size; a++){
			list.addElement();
		}
	}
	
	public void set(int index, char element){
		list.setSpecificElement(index, element);
	}
	
	public char get(int index){
		return list.getElement(index);
	}
}
