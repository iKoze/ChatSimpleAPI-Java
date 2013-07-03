package de.floriware.chatsimple.databundles;

public class Notify extends DataBundle
{
	protected String sender;
	protected String message;
	
	protected void init()
	{
		type = Type.NOTIFY;
		sender = "";
		message = "";
	}
	
	public Notify()
	{
		super();
	}
	
	public Notify(String data)
	{
		super(data);
	}
	
	public Notify(String sender, String message)
	{
		init();
		setSender(sender);
		setMessage(message);
	}
	
	public String getData()
	{
		return type.toString() + delimiter + sender + delimiter + message; 
	}
	
	public void setSender(String sender)
	{
		this.sender = sender;
	}
	
	public String getSender()
	{
		return sender;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public boolean parse()
	{
		String [] values = data.split(delimiter,3);
		try
		{
			sender = values[1];
			message = values[2];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
		return true;
	}
}
