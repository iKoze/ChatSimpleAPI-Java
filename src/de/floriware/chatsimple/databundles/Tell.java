package de.floriware.chatsimple.databundles;

import de.floriware.utils.string.StringJoin;

public class Tell extends Say 
{
	protected String [] receiver;
	
	protected void init()
	{
		type = Type.TELL;
		sender = "";
		chatmessage = "";
	}
	
	public Tell()
	{
		super();
	}
	
	public Tell(String data)
	{
		super(data);
	}
	
	public Tell(String sender, String [] receiver, String chatmessage)
	{
		init();
		setSender(sender);
		setReceiver(receiver);
		setChatMessage(chatmessage);
	}
	
	public String getData()
	{
		return type.toString() + delimiter + sender + delimiter + StringJoin.join(field_separator, receiver) + delimiter + chatmessage;
	}
	
	public void setReceiver(String [] receiver)
	{
		this.receiver = receiver;
	}
	
	public String [] getReceiver()
	{
		return receiver;
	}
	
	public boolean parse(String data)
	{
		String [] values = data.split(delimiter,4);
		try
		{
			sender = values[1];
			receiver = values[2].split(field_separator);
			chatmessage = values[3];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
		return true;
	}
}
