package de.floriware.chatsimple.databundles;

public class Whisper extends Tell
{
	protected void init()
	{
		type = Type.TELL;
	}
	
	public Whisper(String data)
	{
		super(data);
	}
	
	public Whisper(String sender, String [] receiver, String chatmessage)
	{
		super(sender, receiver, chatmessage);
	}
}
