package internet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client01 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket("localhost" , 8888);
		
		//根据socket获取字节输出流 已经可以给服务器写数据了
		OutputStream os = socket.getOutputStream();
		
		//但是字节输出流写起来比较麻烦，所以用一个好用的字符输出流工具
		//可以形象理解为细的水管套一个粗点的水管，水流大一点
		PrintStream ps = new PrintStream(os);
		
		ps.println("i am zs");
		
		ps.flush();//flush是刷新 就是把数据从源头A（客户端）刷到B目的地（服务器）
				   //为什么要刷呢？因为有的时候不满的时候不给目的地 高效 刷
					//立马过去	
	}
}
