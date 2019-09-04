package window;





import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import internet.Client08;
import utils.FrameUtils;

public class MainView extends JFrame{
	
	private JTabbedPane jtp;
	
	private JPanel jp1 , jp2 ,jp3, jp32 , jp4;
	
	private JPanel jp11;
	private JLabel jl11 ,jl12;
	private JTextField jtf11;
	
	private JButton jb41 , jb42;
	
	public void init(String[] selfAndFriends)
	{
		String[] self = selfAndFriends[0].split(",");
		
		String name = self[1];
		String signature = self[2];
		String header = self[3];
		
		//设置JFrame的布局方式成为绝对布局 根据控件在容器中的location定位
		this.setLayout(null);
		
		jp1 = new JPanel();
		
		//给JPanel设置边框 用于调试
		//jp1.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		//设置宽度时候需要考虑border本身也有宽度
		jp1.setSize(235 , 100);
		//设置jp1在父容器也就是JFrame中的定位 左上角和父容器左上角的距离
		jp1.setLocation(5, 5);
		
		jp11 = new JPanel();
	//	jp11.setBorder(BorderFactory.createLineBorder(Color.RED));
		jp11.setSize(100 , 80);
		jp11.setLocation(10, 10);
		
		
		ImageIcon image = new ImageIcon("image//"+header);
		jl11 = new JLabel(image);
		
		jp11.add(jl11);
		
		jl12 = new JLabel(name);
	//	jl12.setBorder(BorderFactory.createLineBorder(Color.RED));
		jl12.setSize(60 , 30);
		jl12.setLocation(120, 20);
		
		jtf11 = new JTextField(signature);
		//jtf11.setBorder(BorderFactory.createLineBorder(Color.RED));
		jtf11.setSize(100 , 30);
		jtf11.setLocation(120, 60);
		
		//设置jp1为绝对布局
		jp1.setLayout(null);
		jp1.add(jp11);
		jp1.add(jl12);
		jp1.add(jtf11);
		
		//jp1加到父容器之前把jp1的内容设置好
		this.add(jp1);
		
		jp2 = new JPanel();
		
		//给JPanel设置边框 用于调试
	//	jp2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		//设置宽度时候需要考虑border本身也有宽度
		jp2.setSize(235 , 30);
		//设置jp1在父容器也就是JFrame中的定位 左上角和父容器左上角的距离
		jp2.setLocation(5, 110);
		
		this.add(jp2);
		
		
		jtp = new JTabbedPane();
		//给JPanel设置边框 用于调试
	//	jtp.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		//设置宽度时候需要考虑border本身也有宽度
		jtp.setSize(235 , 360);
		//设置jp1在父容器也就是JFrame中的定位 左上角和父容器左上角的距离
		jtp.setLocation(5, 145);
		
		jp3 = new JPanel();
		
		//给JPanel设置边框 用于调试
	//	jp3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		//设置宽度时候需要考虑border本身也有宽度
		jp3.setSize(220 , 340);
		//设置jp1在父容器也就是JFrame中的定位 左上角和父容器左上角的距离
		jp3.setLocation(10, 10);
		
		jp3.setLayout(null);
		
		//初始化好友列表
		initFriendList(selfAndFriends);
		
		jtp.add("好友",jp3);
		
		
		jp32 = new JPanel();
		
		//给JPanel设置边框 用于调试
	//	jp32.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		//设置宽度时候需要考虑border本身也有宽度
		jp32.setSize(220 , 340);
		//设置jp1在父容器也就是JFrame中的定位 左上角和父容器左上角的距离
		jp32.setLocation(10, 10);
		
		jp32.setLayout(null);
		
		String[] crowds = new String[]{"1,q1,c1,gsl.png","2,q2,c2,gsl.png"};
		
		//初始化群列表
		initCrowdList(crowds);
		
		jtp.add("群",jp32);
		
		//给选项卡加一个切换选项卡的监听器，需要实现ChangeListener接口
		jtp.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				
				//如果点击是群的选项卡 ， 从服务器获取最新的群列表
				if(jtp.getSelectedIndex() == 1)
				{
					String message = "9,"+Client08.selfUser.getId();
					
					PrintStream ps;
					try {
						ps = new PrintStream(Client08.selfSocket.getOutputStream());
						ps.println(message);
						ps.flush();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				
			}
		});
		this.add(jtp);
		
		
		
		jp4 = new JPanel();
		
		//给JPanel设置边框 用于调试
	//	jp4.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
		//设置宽度时候需要考虑border本身也有宽度
		jp4.setSize(235 , 50);
		//设置jp1在父容器也就是JFrame中的定位 左上角和父容器左上角的距离
		jp4.setLocation(5, 515);
		
		jb41 = new JButton("搜索");
		jb41.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SearchView sv = new SearchView();
				sv.init();
				
				FrameUtils.searchView = sv;
				
				
			}
		});
		
		jp4.add(jb41);
		
		
		jb42 = new JButton("创建群");
	
		
		class A implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				String nameAndDescAndLogo = JOptionPane.showInputDialog("请输入群名和群简介格式如下：群名,简介,logo");
				
				System.out.println(nameAndDescAndLogo);
				
				//获取当前时间作为群的创建时间
				String createTime = new Date().toLocaleString();
				String message = "6,"+Client08.selfUser.getId()+","+createTime+","+nameAndDescAndLogo;
				
				
				PrintStream ps;
				
				try {
					ps = new PrintStream(Client08.selfSocket.getOutputStream());
					
					ps.println(message);
					ps.flush();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
			}
			
		}
		
		ActionListener a = new A();
		
		jb42.addActionListener(a);
		
		
		jp4.add(jb42);
		
		this.add(jp4);
		
		this.setTitle(name+"的主界面");
		this.setSize(250 , 600);
		this.setLocation(300, 400);
		//实际中可能点右上角的叉只是隐藏不是真正的关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//调节大小
		//this.setResizable(false);//re -size 
		this.setVisible(true);
	}


	/**
	 * 初始化好友列表
	 * @param friends
	 */
	private void initFriendList(String[] friends) {
		
		//jp3加入所有的好友
		for (int i = 1; i < friends.length; i++) {
			
			String[] userInfo = friends[i].split(",");
			
			JPanel jp31 = new JPanel();
			
			//jp31.setBorder(BorderFactory.createLineBorder(Color.RED));
			jp31.setSize(225 , 80);
			jp31.setLocation(5, 5 + (i-1)*85 );
			
			
			jp31.setLayout(null);
			
			
			ImageIcon img = new ImageIcon("image/"+userInfo[3]);
			JLabel jl = new JLabel(img);
			
			JPanel jp311 = new JPanel();
			//jp311.setBorder(BorderFactory.createLineBorder(Color.RED));
			jp311.setSize(80 , 60);
			jp311.setLocation(10, 10);
			
			jp311.add(jl);
			
			jp31.add(jp311);
			
			
		
			
			JLabel jl2 = new JLabel(userInfo[1]);
			//jl2.setBorder(BorderFactory.createLineBorder(Color.RED));
			jl2.setSize(60 , 30);
			jl2.setLocation(120, 10);
			
			jp31.add(jl2);
			
			JTextField jtf2 = new JTextField(userInfo[2]);
			//jtf2.setBorder(BorderFactory.createLineBorder(Color.RED));
			jtf2.setSize(100 , 30);
			jtf2.setLocation(120, 40);
			
			jp31.add(jtf2);
			
			//JPanel没有addActionLister的方法 可以用AddMouseLinsteer
			//用鼠标监听器 匿名内部类 如果是实现接口有很多咱现在不用的方法，看起来很烦
			//解决这种想要实现接口，但是接口中有很多方法，我只关心极少数的方法，有一种
			//解决思路是 适配器设计模式
//			jp31.addMouseListener(new MouseListener() {
//				
//				@Override
//				public void mouseReleased(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void mousePressed(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void mouseExited(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void mouseEntered(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					PrivateChatView pv = new PrivateChatView();
//					pv.init(1, "zs");
//				}
//			});
			
			//不去newMouseLinstener接口而是new一个实现了该接口的MouseAdapter
			//只需要覆盖你想要覆盖的方法，别的不用管
			jp31.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int friendId = Integer.parseInt(userInfo[0]);
					
					//如果缓存的好友聊天界面中有
					if(FrameUtils.friendIdAndPrivateChatViews.get(friendId) != null)
					{
						//显示
						FrameUtils.friendIdAndPrivateChatViews.get(friendId).setVisible(true);
					}else//如果缓存的好友聊天界面中没有
					{
						//新建并显示并缓存
						PrivateChatView pv = new PrivateChatView();
						pv.init(friendId, userInfo[1]);
						
						//打开一个好友聊天界面需要缓存到我的聊天界面中
						
						FrameUtils.friendIdAndPrivateChatViews.put(friendId, pv);
					}
					
				
				}
					
				
			});
			
			jp3.add(jp31);
			
		}
	}
	
	
	/**
	 * 初始化群列表
	 * @param crowds
	 */
	private void initCrowdList(String[] crowds) {
		
		//jp3加入所有的好友
		for (int i = 0; i < crowds.length; i++) {
			
			String[] crowdInfo = crowds[i].split(",");
			
			JPanel jp31 = new JPanel();
			
			//jp31.setBorder(BorderFactory.createLineBorder(Color.RED));
			jp31.setSize(225 , 80);
			jp31.setLocation(5, 5 + i*85 );
			
			
			jp31.setLayout(null);
			
			
			ImageIcon img = new ImageIcon("image/"+crowdInfo[3]);
			JLabel jl = new JLabel(img);
			
			JPanel jp311 = new JPanel();
			//jp311.setBorder(BorderFactory.createLineBorder(Color.RED));
			jp311.setSize(80 , 60);
			jp311.setLocation(10, 10);
			
			jp311.add(jl);
			
			jp31.add(jp311);
			
			
			JLabel jl2 = new JLabel(crowdInfo[1]);
			//jl2.setBorder(BorderFactory.createLineBorder(Color.RED));
			jl2.setSize(60 , 30);
			jl2.setLocation(120, 10);
			
			jp31.add(jl2);
			
			JTextField jtf2 = new JTextField(crowdInfo[2]);
			//jtf2.setBorder(BorderFactory.createLineBorder(Color.RED));
			jtf2.setSize(100 , 30);
			jtf2.setLocation(120, 40);
			
			jp31.add(jtf2);
			
			
			//不去newMouseLinstener接口而是new一个实现了该接口的MouseAdapter
			//只需要覆盖你想要覆盖的方法，别的不用管
			jp31.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					
				
				}
					
				
			});
			
			jp32.add(jp31);
			
		}
	}
	
	public JPanel getJp3() {
		return jp3;
	}


	public JPanel getJp32() {
		return jp32;
	}


	public static void main(String[] args) {
		MainView mv = new MainView();
		
		String usersStr = "1,zs,iamzs,gsl.png;2,ls,iamls,gsl.png;3,ww,iamww,gsl.png";
		
		String[] selfAndFriends = usersStr.split(";");
		
		mv.init(selfAndFriends);
	}
	
}


