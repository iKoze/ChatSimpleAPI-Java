package de.floriware.chatsimple.databundles;

public class List extends DataBundle
{	
	public List()
	{
		type = Type.LIST;
		super.setData(type.toString());
	}
	
	public String getData()
	{
		return type.toString();
	}
}
