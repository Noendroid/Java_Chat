
package server;

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

public class ServerWorkingThread {
	private Socket soc;
	private InputStream in;
	private OutputStream out;

	private DataInputStream read;
	private DataOutputStream outData;
	private ServerReader readerThread;
	private PrintWriter print;
	private ServerComunicator serverComunicator;
	private int indexInHash;

	public ServerWorkingThread(Socket soc, ServerComunicator a, int indexInHash) {
		this.indexInHash = indexInHash;
		this.serverComunicator = a;
		this.soc = soc;
		try {
			init(soc);
			readerThread = new ServerReader(outData, read, serverComunicator, this);
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

	public void write(String what) {
		try {
			outData.writeUTF(what);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void disconnect() throws IOException {
		in.close();
		out.close();
		read.close();
		outData.close();

		readerThread.disconnect();
		//serverComunicator.disconnect(this.indexInHash);

	}

}
