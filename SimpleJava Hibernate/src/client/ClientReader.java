package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import Display.ChatFrame;
import Display.Run;
import tre.Message;
import tre.RequestType;
import tre.User;

public class ClientReader extends Thread {
	ClientComunicator comunicator;
	DataInputStream buff;
	boolean stop;

	public ClientReader(DataInputStream buff, ClientComunicator listen) {
		this.comunicator = listen;
		this.buff = buff;
		stop = false;
		start();
	}

	public void run() {
		while (!stop) {
			try {
				if (buff != null) {
					String ans = buff.readUTF();
					process(ans);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void process(String ans) {
		System.out.println("ClientReader - response from server:\n" + ans);
		String[] arr = ans.split(Pattern.quote("|"));
		int answer = Integer.parseInt(arr[0]);

		switch (answer) {
		case RequestType.LOGIN:// message
			if (arr[1].equals("" + RequestType.SUCCESS)) {
				Gson gson = new Gson();
				User connectedUser = gson.fromJson(arr[2], User.class);
				Run.openChat(connectedUser, comunicator);
			} else {
				Run.showError("Incorrect user name or password!");
			}
			break;

		case RequestType.REGISTER:
			if (arr[1].equals("" + RequestType.SUCCESS)) {
				Run.showSuccess("Successful Registration!");
			} else {
				Run.showError("Error, Could not register this user!\nTry changing your user name...");
			}
			break;

		case RequestType.MESSEGE_SEND:
			if (arr[1].equals("" + RequestType.SUCCESS)) {
				String content = arr[2];
				Gson gson = new Gson();
				Message message = gson.fromJson(content, Message.class);
				ChatFrame chat = Run.getChat();
				chat.writeSelfMessegeToDialog(message);
			} else {
				Run.showError("Error, Could not send the messege..");
			}
			break;

		case RequestType.MESSEGE_RECIVED:
			Gson gson = new Gson();
			ChatFrame chat = Run.getChat();

			User sender = gson.fromJson(arr[1], User.class);
			Message message = gson.fromJson(arr[2], Message.class);

			if (sender.getUserid() != chat.getConnectedUserId()) {
				chat.writeOtherMessegeToDialog(sender, message);
			}
			break;
		default:
			break;
		}

	}
}
