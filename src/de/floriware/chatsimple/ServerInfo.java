package de.floriware.chatsimple;

public class ServerInfo
{
	private final int DEFAULT_PORT = 5060; 
	public String address;
	public String username;
	public String password = "";
	public String delimiter = "::";
	
	public ServerInfo()
	{
		
	}
	
	public ServerInfo(String address, String username, String password)
	{
		this.address = address;
		this.username = username;
		this.password = password;
	}
	
	public String getHost()
	{
		return address.split(":")[0];
	}
	
	public int getPort()
	{
		try
		{
			return Integer.parseInt(address.split(":")[1]);
		}
		catch (Exception e)
		{
			return DEFAULT_PORT;
		}
	}
}
