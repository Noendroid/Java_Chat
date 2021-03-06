package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Authenticator.RequestorType;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import tre.Message;
import tre.RequestType;
import tre.User;

public class ServerReader extends Thread {
	ServerComunicator serverComunicator;
	DataInputStream myInputStream;
	boolean stop;
	private ServerWorkingThread serverWorkingThred;
	private DataOutputStream myOutPutStream;

	public ServerReader(DataOutputStream print, DataInputStream buff, ServerComunicator a, ServerWorkingThread t) {
		this.serverWorkingThred = t;
		this.myOutPutStream = print;
		this.serverComunicator = a;
		this.myInputStream = buff;
		stop = false;
		start();
	}

	// this thread will
	public void run() {
		while (!stop) {

			try {
				if (myInputStream != null) {
					String ans = myInputStream.readUTF();
					process(ans);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// here we are proccessing the message from the client
	private void process(String ans) {
		/*
		 * 1-login 2-register 3-send messege
		 */
		System.out.println("reader ans=\t" + ans);
		String[] arr = ans.split(Pattern.quote("|"));
		Gson gson = new Gson();
		boolean response;
		int answer = Integer.parseInt(arr[0]);

		switch (answer) {
		case RequestType.LOGIN:// login
			String name = arr[1];
			String pass = arr[2];
			System.out.println("username:\t" + name);
			System.out.println("password:\t" + pass);
			User connectedUser = serverComunicator.login(name, pass);

			if (connectedUser != null) {
				String send = RequestType.LOGIN + "|" + RequestType.SUCCESS + "|" + gson.toJson(connectedUser);
				// System.out.println(send);
				serverWorkingThred
						.write(RequestType.LOGIN + "|" + RequestType.SUCCESS + "|" + gson.toJson(connectedUser));
			} else {
				serverWorkingThred.write(RequestType.LOGIN + "|" + RequestType.FAILURE);
			}
			break;
		case RequestType.REGISTER:// register
			System.out.println("new registration request");
			String userInString = arr[1];
			User newUser = gson.fromJson(userInString, User.class);
			response = serverComunicator.Register(newUser);
			if (response) {
				System.out.println("registration commited");
				serverWorkingThred.write(RequestType.REGISTER + "|" + RequestType.SUCCESS);
			} else {
				System.out.println("registration was not commited");
				serverWorkingThred.write(RequestType.REGISTER + "|" + RequestType.FAILURE);
			}
			break;
		case RequestType.MESSEGE_SEND:
			String userInJson = arr[1];
			String content = arr[2];
			User sender = gson.fromJson(userInJson, User.class);
			Message newMessage = serverComunicator.addNewMessege(sender, content);
			String messageInJson = gson.toJson(newMessage);
			if (newMessage != null) {
				serverWorkingThred.write(RequestType.MESSEGE_SEND + "|" + RequestType.SUCCESS + "|" + messageInJson);
				serverComunicator.sendMultyCastMessage(sender, newMessage);
			} else {
				serverWorkingThred.write(RequestType.MESSEGE_SEND + "|" + RequestType.FAILURE);
			}
			break;
		case RequestType.MESSEGE_GET:
			String messageJson = arr[1];
			Message lastMessage = gson.fromJson(messageJson, Message.class);
			Message previous = serverComunicator.getPreviouseMessage(lastMessage);
			if (previous == null) {
				String data = RequestType.MESSEGE_GET + "|" + RequestType.NONE;
				serverWorkingThred.write(data);
			} else {
				User senderUser = serverComunicator.getUser(previous.getSenderId());
				messageJson = gson.toJson(previous);
				String userJson = gson.toJson(senderUser);
				String data = RequestType.MESSEGE_GET + "|" + RequestType.SUCCESS + "|" + messageJson + "|" + userJson;
				serverWorkingThred.write(data);
			}
			break;
		case RequestType.GET_USER:
			int id = Integer.parseInt(arr[1]);
			User user = serverComunicator.getUser(id);
			String data = RequestType.GET_USER + "|" + gson.toJson(user);
			serverWorkingThred.write(data);
		case RequestType.DISCONNECT:

			try {
				serverWorkingThred.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

	}

	public void setStop(boolean state) {
		this.stop = true;
	}

	public void disconnect() throws IOException {
		myInputStream.close();
		myOutPutStream.close();
		try {
			this.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
