package runtimeException;

public class StackOutOfBoundsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StackOutOfBoundsException(String msg)
	{
		super(msg);
	}
	
	public StackOutOfBoundsException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
