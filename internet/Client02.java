package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client02 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket("localhost" , 8888);
		
		OutputStream os = socket.getOutputStream();
		
		PrintStream ps = new PrintStream(os);
		
		ps.println("i am zs");
		
		ps.flush();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		System.out.println("客户端收到服务器的返回的信息: " + br.readLine());
					
		
	}
}
