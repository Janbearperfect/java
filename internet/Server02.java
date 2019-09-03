package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server02 {
	
	public static void main(String[] args) throws IOException  {
		
		ServerSocket  ss = new ServerSocket(8888);
		
		System.out.println("服务器已经启动，准备等待客户端的连接。。。");
		System.out.println("ss.accept() 会线程阻塞 ，没有客户端连接 程序卡在这里 等待客户端连接");
		
		Socket socket = ss.accept();
		
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String receiveMessage = br.readLine();
		
		System.out.println("服务器收到客户端的数据: " + receiveMessage);
		
		String retrunMessage = "echo: hello " + receiveMessage.substring(receiveMessage.indexOf("am")+3);
		PrintStream ps = new PrintStream(socket.getOutputStream());
		ps.println(retrunMessage);
		ps.flush();
		
	}
}
