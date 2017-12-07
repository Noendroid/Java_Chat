package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;

import tre.RequestType;
import tre.User;

public class ClientWorkingThread {
	private Socket soc;
	private InputStream in;
	private OutputStream out;

	private DataInputStream read;
	private DataOutputStream outData;
	private ClientReader readerThread;
	private PrintWriter print;

	ClientComunicator comunicator;

	public ClientWorkingThread(Socket soc, ClientComunicator listen) {
		this.soc = soc;
		this.comunicator = listen;
		try {

			init(soc);
			readerThread = new ClientReader(read, listen);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	private void init(Socket soc) throws IOException {
		out = soc.getOutputStream();
		in = soc.getInputStream();

		outData = new DataOutputStream(out);
		read = new DataInputStream(in);

	}

	public void write(String content) {
		try {
			outData.writeUTF(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String read() throws IOException {
		return read.readUTF();
	}

	public void login(String username, String password) throws IOException {
		String dataToTransfer = RequestType.LOGIN + "|" + username + "|" + password;
		System.out.println("sending:\t" + dataToTransfer);
		write(dataToTransfer);

//		String response;
//		response = read();
//		String[] arr = response.split("|");
//		if (arr[0].equals('0')) {
//			return null;
//		} else {
//			Gson gson = new Gson();
//			User ans = gson.fromJson(arr[1], User.class);
//			return ans;
//		}
	}

	public boolean Register(User newUser) throws IOException {
		Gson gson = new Gson();

		String userInJson = gson.toJson(newUser);
		String dataToTransfer = RequestType.REGISTER + "|" + userInJson;
		write(dataToTransfer);

		String response;
		response = read();
		String[] arr = response.split("|");
		System.out.println("clientWorkingThread");
		if (arr[0].equals(RequestType.SUCCESS)) {
			return false;
		} else {
			return true;
		}
	}
	
}
