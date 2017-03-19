package runtimeException;

public class PseudoArrayIndexOutOfBoundsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PseudoArrayIndexOutOfBoundsException(String msg)
	{
		super(msg);
	}
	
	public PseudoArrayIndexOutOfBoundsException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
}
