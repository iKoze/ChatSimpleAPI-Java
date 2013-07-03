package de.floriware.chatsimple.databundles;

public class InvalidUsernameException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public InvalidUsernameException(String username)
	{
		super("Invalid username: '" + username + "'");
	}
}
