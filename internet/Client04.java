package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client04 {
public static void main(String[] args) throws UnknownHostException, IOException {
	Socket socket=new Socket("local",8888);
	OutputStream os=socket.getOutputStream();
	PrintStream ps=new PrintStream(os);
	InputStream is=System.in;
	InputStreamReader isr=new InputStreamReader(is);
	BufferedReader br=new BufferedReader(isr);
	String line=null;
	while((line=br.readLine())!=null) {
		ps.print(line);
		ps.flush();
	}
}
}
