package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server04 {
public static void main(String[] args) throws IOException {
	ServerSocket ss=new ServerSocket(8888);
	Socket socket=ss.accept();
	InputStream is=socket.getInputStream();
	InputStreamReader isr=new InputStreamReader(is);
	BufferedReader br=new BufferedReader(isr);
	String line=null;
	while((line=br.readLine())!=null){
		System.out.println(line);
	}
	
}
}
