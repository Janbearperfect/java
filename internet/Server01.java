package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server01 {
	
	public static void main(String[] args) throws IOException  {
		
		//在本机（127.0.0.1 192）启动一个网络服务，因为本机可以启动好多的网络服务，所以需要
		//说明端口号，也就是在本机的8888端口号监听 等待客户端的链接
		// Unhandled exception type IOException 未处理的异常 编译不通过
		ServerSocket  ss = new ServerSocket(8888);
		
	/*	System.out.println("服务器已经启动，准备等待客户端的连接。。。");
		System.out.println("ss.accept() 会线程阻塞 ，没有客户端连接 程序卡在这里 等待客户端连接");*/
		//接收客户端的连接 开门迎客
		Socket socket = ss.accept();
		
		//根据socket获取输入流 字节流  字节流什么数据都可以读 比如zip image
		// 细水管
		InputStream is = socket.getInputStream();
	
		//字节流转为字符流 字符流只就可以读纯文本 读不了image
		// 中等粗细水管
		InputStreamReader isr = new InputStreamReader(is);
		
		//带缓冲功能的字符流 有一读一行的好用方法
		// 粗水管
		BufferedReader br = new BufferedReader(isr);
		
		String line = null;
		
		while( (line = br.readLine()) != null )
		{
			
			System.out.println(line);
		}
		
	}
}
