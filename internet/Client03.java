package internet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client03{
	public static void main(String[] args) throws UnknownHostException ,IOException {

		Socket socket=new Socket("localhost",8888);
	    OutputStream os=socket.getOutputStream();
	    PrintStream ps=new PrintStream(os);
	    ps.println("我是传奇");
	    ps.flush();
	    
		
		
	}
}




