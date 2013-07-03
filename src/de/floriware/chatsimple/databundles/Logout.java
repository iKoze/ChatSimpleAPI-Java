package de.floriware.chatsimple.databundles;

public class Logout extends DataBundle
{
	protected String username;
	protected String reason;
	
	protected void init()
	{
		type = Type.LOGOUT;
		username = "";
		reason = "";
	}
	
	public Logout()
	{
		super();
	}
	
	public Logout(String data)
	{
		super(data);
	}
	
	public Logout(String username, String reason)
	{
		init();
		setUsername(username);
		setReason(reason);
	}
	
	public String getData()
	{
		String data = type.toString() + delimiter + username;
		data += !reason.equals("") ? (delimiter + reason) : "";
		return data;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	
	public String getReason()
	{
		return reason;
	}
	
	public boolean parse()
	{
		String [] values = data.split(delimiter, 3);
		try
		{
			username = values[1];
			reason = values[2];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
		return true;
	}
}
