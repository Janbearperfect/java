	package window;
	
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import internet.Client08;
import utils.FrameUtils;




/**
 * 最佳实践
 * 
 * 1 当前类继承JFrame类 成为一个界面类 
 * 		extends 目的不是为了复用代码 而是确实存在is a的关系
 * 
 * 2 所有的控件声明到成员变量 private
 * 3 写一个init方法 用来初始化成员控件，并且设置界面的常用信息
 *   最后显示界面
 * 
 * @author UEK-N
 * 
 * class ITTeacher extends Teacher 
 * 
 * 大部分class包含静态特征和动态特征
 * 
 * field
 * 
 * method
 * 
 */
public class LoginView extends JFrame {
	
	
	
	private JLabel jl1 , jl2;
	private JTextField jtf;
	private JPasswordField jpf;
	private JButton jb1 , jb2;
	private ImageIcon background;
	private JPanel jp0;
	
	//这个是面板 是把一个JFrame可以划分为多个区域 面板
	//不能独立显示
	
	
	public void init()
	{
		

		ImageIcon icon = new ImageIcon("image/loginView.jpg");
		jp0 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Image img = icon.getImage();
				g.drawImage(img, 0,0,icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());				
			}};
		jp0.setLayout(null);	
		
		jl1 = new JLabel("用户名");
		jl1.setSize(60, 20);
		jl1.setLocation(130, 90);
		jp0.add(jl1);
		

	
		jl2 = new JLabel("密  码");
		jl2.setSize(60, 20);
		jl2.setLocation(130, 120);
		jp0.add(jl2);
	
		
		
		jtf = new JTextField(20);
		jtf.setSize(120, 25);
		jtf.setLocation(200, 90);
		jp0.add(jtf);
		
		jpf = new JPasswordField(20);
		jpf.setSize(120, 25);
		jpf.setLocation(200, 120);
		jp0.add(jpf);
		
		jb1 = new JButton("注册");
		jb1.setSize(80, 30);
		jb1.setLocation(130, 180);
		jb1.setBackground(Color.decode("#4D4EFE"));//5,186,251#05BAFB  77,78,254#4D4EFE
		jp0.add(jb1);	
		
		//给jb1这个按钮绑定一个事件监听器，监听jb1按钮的单击事件
		//jb1.addActionListener(new RegisterListener());
		
		//接口本来不可以new，所以以下的代码实际是new了一个实现了接口的类
		//但是这个类的名字被省略了，也就是匿名了，这就是java中著名的匿名内部类
		//语法就是 new 抽象类|接口(){}
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("匿名内部类的监听器。。。");
				
				System.out.println("输入的用户名"+jtf.getText());
				
				//匿名内部类中的this是匿名内部类的对象
				FrameUtils.loginView.setVisible(false);
				
				RegisterView registerView = new RegisterView();
				
				registerView.init();
				
				//注册界面存到公共的类中，便于后续访问
				FrameUtils.registerView = registerView;
			}
		});
		
		jb2 = new JButton("登录");
		jb2.setSize(80, 30);
		jb2.setLocation(240, 180);
		jb2.setBackground(Color.decode("#79FBFE"));
		jp0.add(jb2);
		
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String name = jtf.getText();
				String password = jpf.getText();
				
				String message = "2,"+name+","+password;
				
				
				try {
					PrintStream ps = new PrintStream(Client08.selfSocket.getOutputStream());
					
					ps.println(message);
					ps.flush();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		
		
		
		
		
		
		
	
		
		
		
	

		
		this.setTitle("登录 ");
		this.add(jp0);
		this.setSize(icon.getIconWidth(), icon.getIconHeight());
		this.setLocation(460, 220);	//470x220位置
		this.setSize(480,350);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}
	


	//类的内部还可以定义类，就做内部类 内部类分为4种，但是常用的就是匿名内部类
	//内部u类的好处，可以方便的访问外部类的成员 包括private的成员
	//如果内部类只用一次，就是只是一个地方注册，那么定义成匿名内部类就很合适
	class RegisterListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("我被点击了");
			

			System.out.println("输入的用户名"+jtf.getText());
			
			LoginView.this.setVisible(false);
			
			new RegisterView().init();
			
		}
		
	}

	
	
	
	
	public JTextField getJtf() {
		return jtf;
	}





	public static void main(String[] args) {
		
		
		LoginView lv = new LoginView();
		lv.init();
		
	}
	
}

//外部类不方便访问内部类的field method 对于图形用户界面GUI，内部类
//往往要操作界面的field，比如输入框，密码框等，外部类做不到
class RegisterListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {
		

//		System.out.println("输入的用户名"+jtf.getText());
		
		System.out.println("我被点击了");
		
		
	}
	
}
