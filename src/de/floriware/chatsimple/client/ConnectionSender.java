package de.floriware.chatsimple.client;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

import de.floriware.chatsimple.ServerInfo;
import de.floriware.chatsimple.databundles.DataBundle;

public class ConnectionSender extends Thread
{
	protected ServerInfo server;
	protected PrintWriter writer;
	protected IConnectionHandler handler;
	protected Queue<DataBundle> queue;
	
	public ConnectionSender(ServerInfo server, PrintWriter writer, IConnectionHandler handler)
	{
		this.server = server;
		this.writer = writer;
		this.handler = handler;
		queue = new LinkedList<DataBundle>();
	}
	
	public synchronized void send(DataBundle data)
	{
		queue.add(data);
	}
	
	public Queue<DataBundle> getQueue()
	{
		return queue;
	}
	
	public void run()
	{
		boolean run = true;
		DataBundle to_send;
		while(run)
		{
			try
			{
				while((to_send = getNextBundle()) != null)
				{
					writer.println(to_send.getData());
					writer.flush();
				}
			}
			catch(Exception e)
			{
				run = false;
				handler.handleException(e);
			}
			if(run == false)
			{
				handler.gotDisconnected();
			}
		}
	}
	
	protected synchronized DataBundle getNextBundle()
	{
		return queue.poll();
	}
}
