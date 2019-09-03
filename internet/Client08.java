package internet;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import domain.User;
import utils.FrameUtils;
import window.LoginView;
import window.MainView;
import window.PrivateChatView;
import window.RegisterView;
import window.SearchView;

public class Client08 {
	
	//客户端任意时刻想要和服务器通信，都可以拿这个socket
	public static Socket selfSocket;
	public static User selfUser;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket("localhost" , 8888);
		
		Client08.selfSocket = socket;
		
		//启动一条线程负责读取服务器任意时刻的返回信息
		Thread t = new ClientThread(socket);
		t.start();
		
		
		LoginView lv = new LoginView();//创建界面
		lv.init();//显示界面
		
		FrameUtils.loginView = lv;//缓存到工具类FrameUtils.loginView
		
		
	}
}

class ClientThread extends Thread
{
	private Socket socket;
	
	public ClientThread(Socket socket)
	{
		this.socket = socket;
		
	}
	
	@Override
	public void run() {
		
		PrintStream ps;
		
		//不停的读取网络（服务器）发送过来的数据 echo+原本的信息
		BufferedReader br;
		try {
			ps = new PrintStream(socket.getOutputStream());
					
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String line = null;
			
			while( (line = br.readLine()) != null )
			{
				
				System.out.println("客户端收到服务器的数据: "+line);
				
				String[] strs = line.split(",");
				
				//客户端要注册 1,ok 或 1,nook
				if("1".equals(strs[0]))
				{
					if("ok".equals(strs[1]))
					{
						JOptionPane.showMessageDialog(null, "注册成功!");
						
						//setVisiable是隐藏 内存还在
						//FrameUtils.registerView.setVisible(false);
		
						//disponse直接内存销毁
						RegisterView rv = FrameUtils.registerView;
						
						
						
						
						LoginView lv = FrameUtils.loginView;
						lv.getJtf().setText(rv.getJtf1().getText());
						
						rv.dispose();
						
						lv.setVisible(true);
						
					}else
					{
						JOptionPane.showMessageDialog(null, "注册失败!");
					}
				}
				
				
				if("2".equals(strs[0]))
				{
					if("ok".equals(strs[1]))
					{
						//line 2,ok,1,zs,iamzs,1.jpg;2,ls,iamls,1.jpg;3,ww,iamww,1.jpg
						String usersStr = line.substring(5);
						
						//包括自己和好友
						String[] users = usersStr.split(";");
						
						//缓存一下self，以便于后续方便使用
						//1,zs,iamzs,1.jpg
						String user = users[0];
						
						String[] ss = user.split(",");
						
						User u = new User(Integer.parseInt(ss[0]),ss[1],ss[2],ss[3]);
						
						Client08.selfUser = u;
						
						//从缓存界面的工具类找到登录界面
						LoginView rv = FrameUtils.loginView;
						
						//直接从内存中销毁 当然也就不显示了
						rv.dispose();
						
						
						MainView mv  = new MainView();
						mv.init(users);
						
						FrameUtils.mainView = mv;
						
					}else
					{
						JOptionPane.showMessageDialog(null, "登录失败!");
					}
				}
				
				
				
				if("3".equals(strs[0]))
				{
					if("ok".equals(strs[1]))
					{
						//line 3,ok,1,zs,iamzs,1.jpg;2,ls,iamls,1.jpg;ww,111,iamww,1.jpg
						String usersStr = line.substring(5);
						
						//usersStr 1,zs,iamzs,1.jpg;2,ls,iamls,1.jpg;ww,111,iamww,1.jpg
						String[] users = usersStr.split(";");
						
						//
						//Vector就类似与ArryList
						Vector<String> columnNames = new Vector<>();
						columnNames.add("id");
						columnNames.add("名字");
						columnNames.add("签名");
						columnNames.add("头像");
						
						Vector<Vector<String>> rows = new Vector<Vector<String>>();
						
						for (String user : users) {
							
							String[] ss = user.split(",");
							
							//user 1,zs,iamzs,1.jpg
							Vector<String> row = new Vector<>();
							row.add(ss[0]);
							row.add(ss[1]);
							row.add(ss[2]);
							row.add(ss[3]);
							
							rows.add(row);
							
						}
						
						JTable jt = new JTable(rows, columnNames);
						
						
						JScrollPane jsp = new JScrollPane(jt);
						
						SearchView searchView = FrameUtils.searchView;							
						//注意这里必须重新设置一下SearchView界面的jtable 变量jt
						//否则在搜索之后，看到的jt不是原来的jt，如果操作原来dejt会有问题
						searchView.setJt(jt);					
						JPanel jp2 = searchView.getJp2();				
						//清空面板原来的内容
						jp2.removeAll();						
						//加入新的内容
						jp2.add(jsp);						
						//调用Jpanel的更新方法 更新ui
						jp2.updateUI();
						
					}else
					{
						JOptionPane.showMessageDialog(null, "没有找到!");
					}
				}
				
				
				if("4".equals(strs[0]))
				{
					
					//有对方要加自己为好友
					//4,1,2
					if(strs.length == 3)
					{
						String selfId = strs[1];
						
						int select = JOptionPane.showConfirmDialog(null, selfId+"要加你为好友");
						
						System.out.println(select);
						
						String returnMessage = null;
						
						//同意
						if(select == 0)
						{
							returnMessage = line+",ok";
							
							//如果同意 要考虑更新自己的好友界面 把新增的好友加上
							
							ps.println(returnMessage);
							ps.flush();
						}
						
						//拒绝
						if(select == 1)
						{
							returnMessage = line+",nook";
							
							ps.println(returnMessage);
							ps.flush();
									
						}
						
					}
					
					
					//收到对方给自己加好友请求的回复
					//4,1,2,ok|nook
					if(strs.length >= 4) //nook就是4，如果是ok的话是>4
					{
						String selfId = strs[1];
						String friendId = strs[2];
						
						if("ok".equals(strs[3]))
						{
							//更新好友列表
							//4,1,2,ok,1,zs,zs,1.jpg;2,ls,ls,1.jpg
							int selfIdLength = selfId.length();
							int friendIdLength = friendId.length();
							
							String usersStr = line.substring(7+selfIdLength+friendIdLength);
							
							String[] selfAndFriends = usersStr.split(";");
							
							//首先获取到主界面 
							MainView mainView = FrameUtils.mainView;
							
							//拿到存放好友列表的面板新增一个好友
							JPanel jp3 = mainView.getJp3();
							
							jp3.removeAll();
							
							//jp3加入所有的好友
							for (int i = 1; i < selfAndFriends.length; i++) {
								
								String[] userInfo = selfAndFriends[i].split(",");
								
								JPanel jp31 = new JPanel();
								
								//jp31.setBorder(BorderFactory.createLineBorder(Color.RED));
								jp31.setSize(225 , 80);
								jp31.setLocation(5, 5 + (i-1)*85 );
								
								
								jp31.setLayout(null);
								
								
								ImageIcon img = new ImageIcon("images/"+userInfo[3]);
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
							
							
							jp3.updateUI();
							
							
						}
						
						
						if("nook".equals(strs[3]))
						{
							
							JOptionPane.showMessageDialog(null, friendId+"拒绝了您的好友请求");
							
						}
					}
					
					
				
				}
				
				
				if("5".equals(strs[0]))
				{
					String fromId = strs[1];
					String content = strs[3];
					
					//为了简化，要求双方打开聊天窗口再聊天 
//					PrivateChatView pv = FrameUtils.friendIdAndPrivateChatViews.get(fromId);
//					HashMap<Integer, String> testMap = new HashMap<>();
//					testMap.put(1, "zs");
//					testMap.put(2, "ls");
//					testMap.put(3, "ww");
//					
//					System.out.println(testMap.get(1));
//					
//					System.out.println(testMap.get("1"));
					PrivateChatView pv = FrameUtils.friendIdAndPrivateChatViews.get(Integer.parseInt(fromId));
					
					
					String oldContent = pv.getJta1().getText();
					
					pv.getJta1().setText(oldContent+"\n"+new Date().toLocaleString()+" "+fromId+"和你说："+"\n"+content);
					
					
				}

				//收到创建群的服务器反馈
				if("6".equals(strs[0]))
				{
					if("ok".equals(strs[1]))
					{
						JOptionPane.showMessageDialog(null, "创建成功");
						
						//更新自己的界面 待定
					}
					
					
					if("nook".equals(strs[1]))
					{
						JOptionPane.showMessageDialog(null, strs[2]);
					}
				}
				
				
				//收到搜索群的服务器反馈
				if("7".equals(strs[0]))
				{
					if("ok".equals(strs[1]))
					{
						//7,ok,id,name,createrName,logo;id,name,,createrName,logo;
						String crowdsStr = line.substring(5);
						
						String[] crowds = crowdsStr.split(";");
						
						//更新自己的界面 待定
						Vector<String> columnNames = new Vector<>();
						
						columnNames.add("id");
						columnNames.add("name");
						columnNames.add("createrName");
						columnNames.add("logo");
						
						Vector<Vector<String>> rows = new Vector<Vector<String>>();
 						
						
						for (int i = 0; i < crowds.length; i++) {
							
							//id,name,createrName,logo
							String crowdStr = crowds[i];
							
							String[] crowdDetailStr = crowdStr.split(",");
							
							Vector<String> row = new Vector<>();
							row.add(crowdDetailStr[0]);
							row.add(crowdDetailStr[1]);
							row.add(crowdDetailStr[2]);
							row.add(crowdDetailStr[3]);
							
							
							rows.add(row);
						}
						
						
						JTable jt = new JTable(rows , columnNames);
						
						
						JScrollPane jsp = new JScrollPane(jt);
						
						SearchView sv = FrameUtils.searchView;
						
						sv.setJt(jt);
						
						JPanel jp = sv.getJp2();
						
						jp.removeAll();
						
						
						jp.add(jsp);
						
						jp.updateUI();
					}
					
					
					if("nook".equals(strs[1]))
					{
						JOptionPane.showMessageDialog(null, "没有找到");
					}
				}
				
				if("8".equals(strs[0]))
				{
					if("ok".equals(strs[1]))
					{
						JOptionPane.showMessageDialog(null, "加入成功!");
					}
					
					
					if("nook".equals(strs[1]))
					{
						JOptionPane.showMessageDialog(null, "加入失败!");
					}
				}
				
				
				if("9".equals(strs[0]))
				{
					if("ok".equals(strs[1]))
					{
						
						//9,ok,1,ubdf1907,crowd.jpg,张三;2,高手群,crowd.jpg,张三;

						String crowdsStr = line.substring(5);
						
						String[] crowds = crowdsStr.split(";");
						
						MainView mv = FrameUtils.mainView;
						JPanel jp32 =mv.getJp32();
						
						jp32.removeAll();
						
						//jp3加入所有的好友
						for (int i = 0; i < crowds.length; i++) {
							
							String[] crowdInfo = crowds[i].split(",");
							
							JPanel jp31 = new JPanel();
							
							//jp31.setBorder(BorderFactory.createLineBorder(Color.RED));
							jp31.setSize(225 , 80);
							jp31.setLocation(5, 5 + i*85 );
							
							
							jp31.setLayout(null);
							
							
							ImageIcon img = new ImageIcon("images/"+crowdInfo[2]);
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
							
							JTextField jtf2 = new JTextField(crowdInfo[3]);
							//jtf2.setBorder(BorderFactory.createLineBorder(Color.RED));
							jtf2.setSize(100 , 30);
							jtf2.setLocation(120, 40);
							
							jp31.add(jtf2);
							
							
							//不去newMouseLinstener接口而是new一个实现了该接口的MouseAdapter
							//只需要覆盖你想要覆盖的方法，别的不用管
							jp31.addMouseListener(new MouseAdapter() {
								
								@Override
								public void mouseClicked(MouseEvent e) {
									
									int crowdId = Integer.parseInt(crowdInfo[0]);
									
									//如果缓存的群聊天界面中有
									if(FrameUtils.crowdIdAndPrivateChatViews.get(crowdId) != null)
									{
										//显示
										FrameUtils.crowdIdAndPrivateChatViews.get(crowdId).setVisible(true);
									}else//如果缓存的群聊天界面中没有
									{
										//新建并显示并缓存
										PrivateChatView pv = new PrivateChatView();
										pv.init(crowdId, crowdInfo[1]);
										
										//打开一个好友聊天界面需要缓存到我的聊天界面中
										
										FrameUtils.crowdIdAndPrivateChatViews.put(crowdId, pv);
									}
									
								
								}
									
								
							});
							
							jp32.add(jp31);
							
						}
						
						
						jp32.updateUI();
					}
					
					
					if("nook".equals(strs[1]))
					{
						JOptionPane.showMessageDialog(null, "没有创建过群也没有加入过群!");
					}
				}
				
				
				
				//接收到了系统消息
				if("100".equals(strs[0]))
				{
					String content = strs[1];
					
					JOptionPane.showMessageDialog(null, content);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
