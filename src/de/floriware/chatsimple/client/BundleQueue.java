package de.floriware.chatsimple.client;

import java.util.LinkedList;
import java.util.Queue;
import de.floriware.chatsimple.databundles.DataBundle;
import de.floriware.chatsimple.databundles.DataBundle.Type;

public class BundleQueue
{
	protected boolean getnextbundle = false;
	protected Queue<DataBundle> dataqueue;
	
	public BundleQueue()
	{
		dataqueue = new LinkedList<DataBundle>();
	}
	
	public synchronized void addDataBundle(DataBundle databundle)
	{
		if(getnextbundle)
		{
			dataqueue.add(databundle);
			getnextbundle = false;
			this.notify();
		}
	}
	
	public void requestNextBundle()
	{
		getnextbundle = true;
	}
	
	public boolean waitForOk()
	{
		return getNextBundle().getType() == Type.OK;
	}
	
	public synchronized DataBundle getNextBundle()
	{
		DataBundle data;
		while(dataqueue.isEmpty())
		{
			try
			{
				this.wait();
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		data = dataqueue.poll();
		return data;
	}
}
