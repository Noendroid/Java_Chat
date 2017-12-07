package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;

import com.google.gson.Gson;

import Display.LoginPanel;
import tre.Message;
import tre.MessageDao;
import tre.RequestType;
import tre.User;
import tre.UserDao;

public class Server implements ServerComunicator, Runnable {
	private int port = 8080;
	ServerSocket server;
	Socket recieved;
	HashMap<String, DataOutputStream> connectedUsers;

	public Server() {
		try {
			connectedUsers = new HashMap<String, DataOutputStream>();
			server = new ServerSocket(port);
			System.out.println("server-ip4:\t" + Inet4Address.getLocalHost());
			Thread a = new Thread(this);
			a.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		while (true) {
			try {
				recieved = server.accept();
				DataOutputStream data = new DataOutputStream(recieved.getOutputStream());
				connectedUsers.put(recieved.getLocalSocketAddress().toString(), data);
				System.out.println("new connection with ip:\t" + recieved.getLocalSocketAddress().toString());
				new ServerWorkingThread(recieved, this);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public User login(String userName, String password) {
		// TODO Auto-generated method stub
		// check if user exists in database
		System.out.println(userName + " is trying to log in");
		UserDao userDao = new UserDao();
		User connectedUser = userDao.loginUser(userName, password);

		if (connectedUser != null) {
			System.out.println("uproved");
			return connectedUser;
		}
		System.out.println("unseccessful log in");
		return null;
	}

	@Override
	public List<Message> getAllMesseges() {
		// TODO Auto-generated method stub
		MessageDao messegeDao = new MessageDao();
		return messegeDao.getAllMesseges();

	}

	@Override
	public void sendMultyCastMessage(User sender, Message message) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		String userInJson = gson.toJson(sender);
		String messageInJson = gson.toJson(message);
		
		String dataToTransfer = RequestType.MESSEGE_RECIVED + "|" + userInJson + "|" + messageInJson;
		
		for (DataOutputStream stream : connectedUsers.values()) {
			try {
				stream.writeUTF(dataToTransfer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean Register(User newUser) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		boolean answer = userDao.addUser(newUser);
		return answer;

	}

	@Override
	public Message addNewMessege(User sender, String content) {
		// TODO Auto-generated method stub
		MessageDao messageDao = new MessageDao();
		Message message = new Message();
		message.setSenderId(sender.getUserid());
		message.setMessage(content);
		message.setDate(new Date());
		messageDao.addMessege(message);
		
		return message;
	}

	public static void main(String[] args) {
		Server server = new Server();
	}

}
