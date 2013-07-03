package de.floriware.chatsimple.databundles;

public class Rename extends DataBundle
{
	protected String old_username;
	protected String new_username;
	
	protected void init()
	{
		type = Type.RENAME;
		old_username = "";
		new_username = "";
	}
	
	public Rename()
	{
		super();
	}
	
	public Rename(String data)
	{
		super(data);
	}
	
	public Rename(String old_username, String new_username)
	{
		init();
		setOldUsername(old_username);
		setNewUsername(new_username);
	}
	
	public String getData()
	{
		return type.toString() + delimiter + old_username + delimiter + new_username;
	}
	
	public void setOldUsername(String old_username)
	{
		this.old_username = old_username;
	}
	
	public String getOldUsername()
	{
		return old_username;
	}
	
	public void setNewUsername(String new_username)
	{
		this.new_username = new_username;
	}
	
	public String getNewUsername()
	{
		return new_username;
	}
	
	public boolean parse()
	{
		String [] values = data.split(delimiter,3);
		try
		{
			old_username = values[1];
			new_username = values[2];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
		return true;
	}
}
