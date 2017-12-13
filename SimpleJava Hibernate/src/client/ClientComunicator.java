package client;

import tre.Message;
import tre.User;

public interface ClientComunicator {
	public User login(String username, String password);

	public void register(User newUser);

	public void sendMessage(User sender, String content);

	public void getPreviouseMessage(Message lastMessage);
	
	public void requestUserById(int id);

	public void disconnect();
}
