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
	protected boolean run = false;
	
	public ConnectionSender(ServerInfo server, PrintWriter writer, IConnectionHandler handler)
	{
		this.server = server;
		this.writer = writer;
		this.handler = handler;
		queue = new LinkedList<DataBundle>();
	}
	
	public synchronized void send(DataBundle data)
	{
		data.delimiter = server.delimiter;
		queue.add(data);
		this.notify();
	}
	
	public Queue<DataBundle> getQueue()
	{
		return queue;
	}
	
	public void disable()
	{
		writer.close();
		queue.clear();
		writer = null;
		queue = null;
		run = false;
	}
	
	public void run()
	{
		run = true;
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
		DataBundle data;
		while(queue.isEmpty())
		{
			try
			{
				this.wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		data = queue.poll();
		return data;
	}
}
