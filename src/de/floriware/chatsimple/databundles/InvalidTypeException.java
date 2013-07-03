package de.floriware.chatsimple.databundles;

public class InvalidTypeException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public InvalidTypeException (DataBundle.Type type)
	{
		super("Invalid Packet type for this Class: " + type.toString());
	}
}
