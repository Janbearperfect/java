package window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import internet.Server08;

public class ServerView extends JFrame {

	private JTabbedPane jtp;// 选项卡

	private JPanel jp1, jp2;

	private JTextArea jta1, jta2;

	private JButton jb1 , jb2 ;

	public void init() {

		jtp = new JTabbedPane();

		jp1 = new JPanel();
		jp2 = new JPanel();

		jta1 = new JTextArea();
		jta2 = new JTextArea();

		jb1 = new JButton("发送系统消息");
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//遍历所有在线的用户，给每个在线用户发送一条系统消息
				String content = jta1.getText();
				
				String message = "100,"+content;
				
				for (Socket socket : Server08.userIdAndSockets.values()) {
					
					PrintStream ps;
					try {
						ps = new PrintStream(socket.getOutputStream());
						ps.println(message);
						ps.flush();
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					
				}
				
			}
		});

		
		jp1.setLayout(null);
		
		jta1.setSize(460 , 300);
		jta1.setLocation(5, 5);
		jta1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		jp1.add(jta1);
		
		jb1.setSize(460 , 30);
		jb1.setLocation(5, 310);
		jb1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		jp1.add(jb1);
		
		jtp.addTab("系统消息", jp1);
		
		
		jp2.setLayout(null);
		
		jta2.setSize(460 , 300);
		jta2.setLocation(5, 5);
		jta2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		jp2.add(jta2);
		
		jb2 = new JButton("保存到本地");
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//想要读写文件用FileOutputStream和FileInputStream
				File file = new File("monitor.txt");
				
				try {
					FileOutputStream fos = new FileOutputStream(file);
					
					String content = jta2.getText();

					byte[] bytes= content.getBytes();
					
					//把字节数组写到fos，fos关联到的是monitor.txt
					fos.write(bytes);
						
					fos.close();
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		jb2.setSize(460 , 30);
		jb2.setLocation(5, 310);
		jb2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		jp2.add(jb2);
		
		
		jtp.addTab("监控", jp2);
		
		this.add(jtp);
		
		this.setTitle("服务器端界面");
		this.setSize(500, 400);
		this.setLocation(200, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	
	public JTextArea getJta2() {
		return jta2;
	}



	public static void main(String[] args) {

		ServerView sv = new ServerView();
		sv.init();

	}

}
