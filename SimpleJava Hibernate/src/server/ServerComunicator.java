package server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tre.Message;
import tre.User;

public interface ServerComunicator {

	public User login(String userName, String password);

	public List<Message> getAllMesseges();

	public void sendMultyCastMessage(User user, Message message);

	public Message addNewMessege(User sender, String content);

	public boolean Register(User newUser);

	public Message getPreviouseMessage(Message lastMessage);
	
	public User getUser(int id);

	public void disconnect(int indexInHash);

}
