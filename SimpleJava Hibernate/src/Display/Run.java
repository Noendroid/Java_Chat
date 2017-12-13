package Display;

import client.Client;
import client.ClientComunicator;
import tre.RequestType;
import tre.User;

public class Run {
	static LoginFrame login;
	static ChatFrame chat;
	static Client client;
	static ErrorFrame error;
	static SuccessFrame success;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		client = new Client("192.168.91.1", 81);
		login = new LoginFrame(client);
	}

	public static void openLogin() {
		login = new LoginFrame(client);
		login.setVisible(true);
	}

	public static void openChat(User user, ClientComunicator comunicator) {
		login.setVisible(false);
		chat = new ChatFrame(user, comunicator);
		chat.setVisible(true);
	}

	public static void closeChat() {
		chat.setVisible(false);
		login.setVisible(true);
	}
	
	public static ChatFrame getChat(){
		return chat;
	}

	public static void showError(String errorMessege) {
		error = new ErrorFrame(errorMessege);
		error.setVisible(true);
	}

	public static void showSuccess(String message) {
		success = new SuccessFrame(message);
		success.setVisible(true);
	}

}
