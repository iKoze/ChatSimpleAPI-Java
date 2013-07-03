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
	
	public void addDataBundle(DataBundle databundle)
	{
		if(getnextbundle)
		{
			dataqueue.add(databundle);
			getnextbundle = false;
		}
	}
	
	public synchronized void requestNextBundle()
	{
		getnextbundle = true;
	}
	
	public synchronized boolean waitForOk()
	{
		return getNextBundle().getType() == Type.OK;
	}
	
	public synchronized DataBundle getNextBundle()
	{
		DataBundle data;
		//int i = 0;
		while(dataqueue.isEmpty())
		{
		//	i++;
			try
			{
				Thread.sleep(100);
			}
			catch (Exception e)
			{}
			//System.out.println(""+i+ " " + dataqueue.isEmpty());
		}
		data = dataqueue.poll();
		return data;
	}
}
