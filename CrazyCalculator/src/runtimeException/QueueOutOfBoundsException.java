package runtimeException;

public class QueueOutOfBoundsException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QueueOutOfBoundsException(String msg)
	{
		super(msg);
	}
	
	public QueueOutOfBoundsException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
	
	
	
	
}
