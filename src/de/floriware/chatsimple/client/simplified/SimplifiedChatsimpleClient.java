package de.floriware.chatsimple.client.simplified;

import de.floriware.chatsimple.ServerInfo;
import de.floriware.chatsimple.client.BundleQueue;
import de.floriware.chatsimple.client.ChatsimpleClient;
import de.floriware.chatsimple.client.IConnectionHandler;
import de.floriware.chatsimple.databundles.*;
import de.floriware.chatsimple.databundles.DataBundle.Type;

public class SimplifiedChatsimpleClient implements IConnectionHandler 
{
	protected ChatsimpleClient client;
	protected ISimplifiedConnectionHandler s_connhandler;
	protected BundleQueue queue;
	
	public static final String VERSION = "alpha 0.99";
	
	public SimplifiedChatsimpleClient(ISimplifiedConnectionHandler s_connhandler)
	{
		queue = new BundleQueue();
		this.s_connhandler = s_connhandler;
		client = new ChatsimpleClient(this);
	}
	
	public SimplifiedChatsimpleClient(ServerInfo server, ISimplifiedConnectionHandler s_connhandler)
	{
		queue = new BundleQueue();
		this.s_connhandler = s_connhandler;
		client = new ChatsimpleClient(server, this);
	}
	
	public SimplifiedChatsimpleClient(String address, String username, String password, ISimplifiedConnectionHandler s_connhandler)
	{
		ServerInfo info = new ServerInfo(address, username, password);
		queue = new BundleQueue();
		this.s_connhandler = s_connhandler;
		client = new ChatsimpleClient(info, this);
	}
	
	public void setServerInfo(String address, String username, String password)
	{
		ServerInfo info = new ServerInfo(address, username, password);
		client.setServerInfo(info);
	}
	
	public void setServerInfo(ServerInfo server)
	{
		client.setServerInfo(server);
	}
	
	public ServerInfo getServerInfo()
	{
		return client.getServerInfo();
	}
	
	public boolean connect()
	{
		boolean success = client.connect();
		if(success)
		{
			queue = client.getListener().getQueue();
		}
		return success;
	}
	
	public void disconnect()
	{
		client.disconnect();
	}
	
	public boolean isConnected()
	{
		return client.isConnected();
	}
	
	public boolean login()
	{
		ServerInfo sinfo = client.getServerInfo();
		Login login = new Login(sinfo.username, sinfo.password);
		queue.requestNextBundle();
		client.send(login);
		DataBundle result = queue.getNextBundle();
		if(result.getType() == Type.OK)
		{
			return true;
		}
		return false;
	}
	
	public void sendChatMessage(String message)
	{
		Say say = new Say(client.getServerInfo().username, message);
		client.send(say);
	}
	
	public void sendTellMessage(String [] receiver, String message)
	{
		Tell tell = new Tell(client.getServerInfo().username, receiver, message);
		client.send(tell);
	}
	
	public void requestUserList()
	{
		client.send(new List());
	}
	
	public boolean changeUsername(String new_username)
	{
		Rename rename = new Rename(client.getServerInfo().username, new_username);
		queue.requestNextBundle();
		client.send(rename);
		if(queue.waitForOk())
		{
			client.getServerInfo().username = new_username;
			return true;
		}
		return false;
	}
	
	public void logout()
	{
		logout("");
	}
	
	public void logout(String reason)
	{
		Logout logout = new Logout(client.getServerInfo().username, reason);
		client.send(logout);
	}
	
	public ChatsimpleClient getChatsimpleClient()
	{
		return client;
	}
	
	public void incomingData(DataBundle data)
	{
		DataBundle parsed = BundleDetector.detect(data);
		Type type = parsed.getType();
		if (type == Type.OK)
		{
			s_connhandler.incomingOK();
		}
		else if (type == Type.MSG)
		{
			Msg chatmessage = (Msg) parsed;
			s_connhandler.incomingChatMessage(chatmessage.getSender(), chatmessage.getChatMessage());
		}
		else if (type == Type.WHISPER)
		{
			Whisper whisper = (Whisper) parsed;
			s_connhandler.incomingWhisperMessage(whisper.getSender(), whisper.getReceiver(), whisper.getChatMessage());
		}
		else if (type == Type.NOTIFY)
		{
			Notify notify = (Notify) parsed;
			s_connhandler.incomingNofification(notify.getSender(), notify.getMessage());
		}
		else if (type == Type.ERR)
		{
			Err error = (Err) parsed;
			s_connhandler.incomingError(error.getSender(), error.getMessage());
		}
		else if (type == Type.CLIENTS)
		{
			Clients clients = (Clients) parsed;
			s_connhandler.incomingUserList(clients.getClients());
		}
	}

	public void gotDisconnected()
	{
		s_connhandler.gotDisconnected();
	}
	
	public void handleException(Exception e)
	{
		s_connhandler.handleException(e);
	}
}
