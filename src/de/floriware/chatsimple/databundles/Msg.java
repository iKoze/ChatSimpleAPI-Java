package de.floriware.chatsimple.databundles;

public class Msg extends Say
{
	protected void init()
	{
		type = Type.MSG;
	}
	
	public Msg()
	{
		super();
	}
	
	public Msg(String data)
	{
		super(data);
	}
	
	public Msg(String sender, String chatmessage)
	{
		super(sender, chatmessage);
	}
}
