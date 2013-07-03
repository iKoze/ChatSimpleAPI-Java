package de.floriware.chatsimple.client.simplified;

public interface ISimplifiedConnectionHandler
{
	public void incomingOK();
	public void incomingChatMessage(String sender, String chatmessage);
	public void incomingWhisperMessage(String sender, String [] receiver, String chatmessage);
	public void incomingNofification(String sender, String message);
	public void incomingError(String sender, String message);
	public void incomingUserList(String [] userlist);
	public void gotDisconnected();
	public void handleException(Exception e);
}
