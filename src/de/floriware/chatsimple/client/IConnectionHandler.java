package de.floriware.chatsimple.client;

import de.floriware.chatsimple.databundles.DataBundle;

public interface IConnectionHandler
{
	public void incomingData(DataBundle data);
	public void gotDisconnected();
	public void handleException(Exception e);
}
