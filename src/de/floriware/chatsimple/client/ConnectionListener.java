package de.floriware.chatsimple.client;

import java.io.*;
import java.net.SocketException;

import de.floriware.chatsimple.ServerInfo;
import de.floriware.chatsimple.databundles.DataBundle;

public class ConnectionListener extends Thread 
{
	protected ServerInfo server;
	protected BufferedReader reader;
	protected IConnectionHandler handler;
	protected BundleQueue queue;
	
	public ConnectionListener(ServerInfo server, BufferedReader reader, IConnectionHandler handler)
	{
		this.server = server;
		this.reader = reader;
		this.handler = handler;
	}
	
	public BundleQueue getQueue()
	{
		if(queue == null)
		{
			queue = new BundleQueue();
		}
		return queue;
	}
	
	public void run()
	{
		boolean run = true;
		String response;
		while(run)
		{
			try
			{
				while((response = reader.readLine()) != null)
				{
					DataBundle incomingdata = new DataBundle();
					incomingdata.delimiter = server.delimiter;
					incomingdata.setData(response);
					if(queue != null)
					{
						queue.addDataBundle(incomingdata);
					}
					handler.incomingData(incomingdata);
				}
			}
			catch(SocketException e)
			{
				run = false;
			}
			catch(IOException e)
			{
				run = false;
			}
			catch(Exception e)
			{
				handler.handleException(e);
			}
			if(run == false)
			{
				handler.gotDisconnected();
			}
		}
	}
}
