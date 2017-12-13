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
	private int port = 81;
	ServerSocket server;
	Socket recieved;
	HashMap<Integer, ServerWorkingThread> connectedUsers;

	public Server() {
		try {
			connectedUsers = new HashMap<Integer, ServerWorkingThread>();
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
				System.out.println("new connection with ip:\t" + recieved.getLocalSocketAddress().toString());

				int indexInHash = connectedUsers.size() + 1;
				ServerWorkingThread serverWorkingThread = new ServerWorkingThread(recieved, this, indexInHash);
				connectedUsers.put(indexInHash, serverWorkingThread);

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

		for (ServerWorkingThread stream : connectedUsers.values()) {
			stream.write(dataToTransfer);
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

	@Override
	public void disconnect(int indexInHash) {
		// connectedUsers.get(indexInHash).write(RequestType.DISCONNECT + "|" +
		// "1");
		try {
			System.out.println("user disconnected - " + indexInHash);
			connectedUsers.get(indexInHash).disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connectedUsers.remove(indexInHash);
	}

	@Override
	public Message getPreviouseMessage(Message lastMessage) {
		MessageDao mdao = new MessageDao();
		Message result = mdao.getPreviouseMessege(lastMessage.getId());
		return result;
	}

	public static void main(String[] args) {
		Server server = new Server();
	}

	@Override
	public User getUser(int id) {
		UserDao dao = new UserDao();
		return dao.getUserById(id);
	}

}
