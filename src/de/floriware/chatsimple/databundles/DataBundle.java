package de.floriware.chatsimple.databundles;

public class DataBundle
{
	public enum Type
	{
		LOGIN, LOGOUT,
		OK, NOTIFY, ERR,
		SAY, MSG, TELL, WHISPER,
		LIST, CLIENTS,
		RENAME,
		UNKNOWN;
		
		public String toString()
		{
			return this.name().toLowerCase();
		}
	}
	
	protected Type type = Type.UNKNOWN;
	public String delimiter = "::";
	public String field_separator = ",";
	protected String data = "";
	
	protected void init()
	{
		
	}
	
	public DataBundle()
	{
		init();
	}
	
	public DataBundle(String data)
	{
		init();
		this.data = data;
		parse();
	}
	
	/**
	 * Sets the data of this DataBundle
	 * @param String data
	 * @throws InvalidTypeException, InvalidUsernameException
	 */
	public void setData(String data)
	{
		parse(data);
		this.data = data;
	}
	
	public String getData()
	{
		return data;
	}
	
	public Type getType()
	{
		String type = data.split(delimiter,2)[0];
		type = type.toUpperCase();
		try
		{
			return Type.valueOf(type);
		}
		catch (IllegalArgumentException e)
		{
			return Type.UNKNOWN;
		}
	}
	
	public boolean parse()
	{
		return parse(data);
	}
	
	public boolean parse(String data)
	{
		return true;
	}
}
