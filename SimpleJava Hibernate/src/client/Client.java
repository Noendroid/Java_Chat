package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import tre.RequestType;
import tre.User;

public class Client implements ClientComunicator {
	private String ip;
	private int port;
	private Socket connection;
	private ClientWorkingThread work;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
		connect();

	}

	public void connect() {
		try {
			connection = new Socket(ip, port);
			work = new ClientWorkingThread(connection, this);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void write(String what) {
		work.write(what);
	}

	public void write(User a) {
		Gson gs = new Gson();
		String ans = gs.toJson(a);
		write(ans);

	}

	public User login(String username, String password) {
		connect();
		String messege = RequestType.LOGIN + "|" + username + "|" + password;
		write(messege);
		return null;
	}

	public void register(User newUser) {
		Gson gson = new Gson();

		String userInJson = gson.toJson(newUser);
		String dataToTransfer = RequestType.REGISTER + "|" + userInJson;
		write(dataToTransfer);
	}

	@Override
	public void sendMessage(User sender, String content) {
		Gson gson = new Gson();
		String userInJson = gson.toJson(sender);
		String dataToTransfer = RequestType.MESSEGE_SEND + "|" + userInJson + "|" + content;
		write(dataToTransfer);
	}

}
