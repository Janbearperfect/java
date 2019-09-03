package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server03 {
public static void main(String[] args)throws IOException {
	ServerSocket ss=new ServerSocket(8888);
	Socket socket=ss.accept();
	InputStream is=socket.getInputStream();
	InputStreamReader isr=new InputStreamReader(is);
	BufferedReader br=new BufferedReader(isr);
	String receiveMessage=br.readLine();
	String returnMessage="echo: hello"+receiveMessage.substring(receiveMessage.indexOf("am")+3);
	PrintStream ps=new PrintStream(socket.getOutputStream());
	ps.println(returnMessage);
	ps.flush();
	
	
	
	
}
}
