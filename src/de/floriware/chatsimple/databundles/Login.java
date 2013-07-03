package de.floriware.chatsimple.databundles;

/**
 * Login Data Bundle
 * @author Florian Schießl
 * @version 0.1
 */
public class Login extends DataBundle
{
	protected String username;
	protected String serverpass;
	
	protected void init()
	{
		type = Type.LOGIN;
		username = "";
		serverpass = "";
	}
	
	public Login()
	{
		super();
	}
	
	public Login(String data)
	{
		super(data);
	}
	
	public Login(String username, String serverpass)
	{
		init();
		setUsername(username);
		setServerPassword(serverpass);
	}
	
	public String getData()
	{
		String data = type.toString() + delimiter + username;
		data += !serverpass.equals("") ? (delimiter + serverpass) : "";
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
	
	public void setServerPassword(String password)
	{
		serverpass = password;
	}
	
	public String getServerPassword()
	{
		return serverpass;
	}
	
	public boolean parse(String data)
	{
		String [] values = data.split(delimiter,3);
		try
		{
			username = values[1];
			serverpass = values[2];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
		return true;
		
	}
}
