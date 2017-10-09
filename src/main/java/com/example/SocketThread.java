package com.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

public class SocketThread extends Thread {
	Socket socket = null;

	public SocketThread(Socket socket) {
		this.socket = socket;

	}

	public void run() {
		System.out.println("Start listening");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				String message = in.readLine();
				if (message != null) {
					if (message.equals("getMap")) {
						break;
					}
					String data[] = message.split(",");
					int location = Integer.parseInt(data[1]) * -1 - 59;
					if (location < 5) {
						Data.hm.put(data[0], "-1");
					} else {
						Data.hm.put(data[0], Integer.toString(location));
					}
				}
			}
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			Iterator it = Data.hm.keySet().iterator();
			String key;
			while (it.hasNext()) {
				key = (String) it.next();
				System.out.println(key + "," + Data.hm.get(key) + "\n");
				pw.write(key + "," + Data.hm.get(key) + "\n");
				pw.flush();
			}
			os.close();
			pw.close();
			stdIn.close();
			in.close();
			socket.close();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
		}

	}
}