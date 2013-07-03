package de.floriware.chatsimple.databundles;

public class Say extends DataBundle 
{
	protected String sender;
	protected String chatmessage;
	
	protected void init()
	{
		type = Type.SAY;
		sender = "";
		chatmessage = "";
	}
	
	public Say()
	{
		super();
	}
	
	public Say(String data)
	{
		super(data);
	}
	
	public Say(String sender, String chatmessage)
	{
		init();
		setSender(sender);
		setChatMessage(chatmessage);
	}
	
	public String getData()
	{
		return type.toString() + delimiter + sender + delimiter + chatmessage;
	}
	
	public void setSender(String sender)
	{
		this.sender = sender;
	}
	
	public String getSender()
	{
		return sender;
	}
	
	public void setChatMessage(String chatmessage)
	{
		this.chatmessage = chatmessage;
	}
	
	public String getChatMessage()
	{
		return chatmessage;
	}
	
	public boolean parse(String data)
	{
		String [] values = data.split(delimiter,3);
		try
		{
			sender = values[1];
			chatmessage = values[2];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
		return true;
	}
}
