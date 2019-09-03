package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server05 {
public static void main(String[] args) throws IOException {
	ServerSocket ss=new ServerSocket(8888);
	while(true) {
		Socket socket=ss.accept();
		PrintStream ps;
		ps=new PrintStream(socket.getOutputStream());
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream())); 
		String line=null;
		while((line=br.readLine())!=null)
		{
			System.out.println("服务器收到客户端的数据: " + line);
			ps.println("echo " + line);
			ps.flush();
			
		}
		
	}
}
}