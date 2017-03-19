package dataStructures;

public class dataStructureTest
{
	public static void main(String[] args)
	{
		Stack<Integer> stack = new Stack<Integer>(3);
		stack.push(4);
		stack.push(3);
		stack.push(2);
		//stack.push(1);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		//System.out.println(stack.pop());
		//stack.pop();
	}
}
