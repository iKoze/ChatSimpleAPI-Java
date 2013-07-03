package de.floriware.utils.string;

public class StringJoin
{
	public static String join(String delimiter, String [] values)
	{
		String output = "";
		for(int i = 0; i < values.length; i++)
		{
			output += values[i];
			output += (i == values.length -1) ? "" : delimiter;
		}
		return output;
	}
}
