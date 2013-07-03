package de.floriware.chatsimple.databundles;

import de.floriware.chatsimple.databundles.DataBundle.Type;

public class BundleDetector
{	
	public static DataBundle detect(String data)
	{
		return detect(new DataBundle(data));
	}
	
	public static DataBundle detect(DataBundle bundle)
	{
		String data = bundle.getData();
		Type type = bundle.getType();
		
		DataBundle result;
		if (type == Type.LOGIN)
		{
			result = new Login(data);
		}
		else if (type == Type.LOGOUT)
		{
			result = new Logout(data);
		}
		else if (type == Type.OK)
		{
			result = new Ok();
		}
		else if (type == Type.NOTIFY)
		{
			result = new Notify(data);
		}
		else if (type == Type.ERR)
		{
			result = new Err(data);
		}
		else if (type == Type.SAY)
		{
			result = new Say(data);
		}
		else if (type == Type.MSG)
		{
			result = new Msg(data);
		}
		else if (type == Type.TELL)
		{
			result = new Tell(data);
		}
		else if (type == Type.WHISPER)
		{
			result = new Whisper(data);
		}
		else if (type == Type.LIST)
		{
			result = new List();
		}
		else if (type == Type.CLIENTS)
		{
			result = new Clients(data);
		}
		else if (type == Type.RENAME)
		{
			result = new Rename(data);
		}
		else
		{
			// Undetected Data Bundle
			result = new DataBundle(data);
		}
		
		return result;
	}
}
