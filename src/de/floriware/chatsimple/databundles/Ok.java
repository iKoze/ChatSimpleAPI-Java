package de.floriware.chatsimple.databundles;

public class Ok extends DataBundle
{	
	public Ok()
	{
		type = Type.OK;
		super.setData(type.toString());
	}
	
	public String getData()
	{
		return type.toString();
	}
}
