package de.floriware.chatsimple.client;

import java.net.*;
import java.io.*;

import de.floriware.chatsimple.ServerInfo;
import de.floriware.chatsimple.databundles.DataBundle;

public class ChatsimpleClient
{
	protected ServerInfo server;
	protected IConnectionHandler handler;
	protected Socket socket;
	protected PrintWriter writer;
	protected BufferedReader reader;
	protected ConnectionListener listener;
	protected ConnectionSender sender;
	
	public static final String VERSION = "alpha 0.99";
	
	public ChatsimpleClient(IConnectionHandler handler)
	{
		this.handler = handler;
	}
	
	public ChatsimpleClient(ServerInfo server, IConnectionHandler handler)
	{
		this.server = server;
		this.handler = handler;
	}
	
	public void setServerInfo(ServerInfo server)
	{
		this.server = server;
	}
	
	public ServerInfo getServerInfo()
	{
		return this.server;
	}
	
	public boolean connect()
	{
		try
		{
			socket = new Socket(server.getHost(),server.getPort());
			writer = new PrintWriter(socket.getOutputStream(),false);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch (ConnectException e)
		{
			return false;
		}
		catch (Exception e)
		{
			handler.handleException(e);
			return false;
		}
		
		listener = new ConnectionListener(server,reader,handler);
		listener.start();
		
		sender = new ConnectionSender(server,writer,handler);
		sender.start();
		
		return socket.isConnected();
	}
	
	public void disconnect()
	{
		if(!isConnected())
		{
			return;
		}
		if(listener != null)
		{
			listener.disable();
			listener = null;
		}
		if(sender != null)
		{
			sender.disable();
			sender = null;
		}
		try
		{
			if(writer != null)
			{
				writer = null;
			}
			if(reader != null)
			{
				reader = null;
			}
			if(socket != null)
			{
				socket.close();
				socket = null;
			}
		}
		catch (Exception e)
		{
			handler.handleException(e);
		}
	}
	
	public boolean isConnected()
	{
		if(socket != null)
		{
			return socket.isConnected();
		}
		return false;
	}
	
	public void send(DataBundle data)
	{
		sender.send(data);
	}
	
	public void send(String data)
	{
		send(new DataBundle(data));
	}
	
	public ConnectionListener getListener()
	{
		return listener;
	}
}
