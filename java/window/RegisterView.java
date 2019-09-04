package window;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import internet.Client08;


//1 extends JFrame
public class RegisterView extends JFrame{
	
	
	//2 声明控件
	private JLabel jl1 , jl2 ,jl3 ,jl4,jl5;
	private JTextField jtf1 ,jtf2 , jtf3;
	private JPasswordField jpf1 ,jpf2;
	private ImageIcon background;
	private JPanel jp0;
	
	private JButton jb1 , jb2;
	
	
	//3 init方法  new 控件 设置控件的布局 相互的位置和包含
	public void init()
	{
		ImageIcon icon = new ImageIcon("image/register.jpg");
		jp0 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Image img = icon.getImage();
				g.drawImage(img, 0,0,icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());				
			}};
		jp0.setLayout(null);	
		
		JLabel jl11 = new JLabel("每一天，");
		jl11.setLocation(60, 50);
		jl11.setSize(180, 100);
		jl11.setFont(new Font("楷体", Font.ITALIC, 30));
		jp0.add(jl11);
		JLabel jl12 = new JLabel("乐在沟通");
		jl12.setLocation(80, 150);
		jl12.setSize(180, 100);
		jl12.setFont(new Font("楷体", Font.ITALIC, 30));
		jp0.add(jl12);
		
		
		
		JLabel jl1 = new JLabel("用户名");
		jl1.setLocation(250, 50);
		jl1.setSize(250, 30);
		jl1.setFont(new Font("宋体", Font.BOLD, 20));
		
		
		
		jl2 = new JLabel("密码");
		jl2.setLocation(250, 100);
		jl2.setSize(100, 30);
		jl2.setFont(new Font("宋体", Font.BOLD, 20));
		
		
		jl3 = new JLabel("确认密码");
		jl3.setLocation(250, 150);
		jl3.setSize(100, 30);
		jl3.setFont(new Font("宋体", Font.BOLD, 20));
		
		
		jl4 = new JLabel("签名");
		jl4.setLocation(250, 200);
		jl4.setSize(100, 30);
		jl4.setFont(new Font("宋体", Font.BOLD, 20));
		
		
		jl5 = new JLabel("头像");
		jl5.setLocation(250, 250);
		jl5.setSize(100, 30);
		jl5.setFont(new Font("宋体", Font.BOLD, 20));
		
		
		
		jtf1 = new JTextField(20);
		jtf1.setLocation(330, 50);
		jtf1.setSize(100, 30);
		
		jtf2 = new JTextField(20);
		jtf2.setLocation(330, 200);
		jtf2.setSize(100, 30);
		
		
		jtf3 = new JTextField(20);
		jtf3.setLocation(330, 250);
		jtf3.setSize(100, 30);
		
		
		jpf1 = new JPasswordField(20);
		jpf1.setLocation(330, 100);
		jpf1.setSize(100, 30);
		
		
		jpf2 = new JPasswordField(20);
		jpf2.setLocation(330, 150);
		jpf2.setSize(100, 30);
		
		jb1 = new JButton("重置");
		jb2 = new JButton("注册");
		jb2.setLocation(30, 250);
		jb2.setSize(100, 30);
		jb2.setContentAreaFilled(false);
		jb2.setBorder(BorderFactory.createRaisedBevelBorder());

		
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				//接收界面的输入
				String name = jtf1.getText();
				String password = jpf1.getText();
				String repassword = jpf2.getText();
				String signature = jtf2.getText();
				String header = jtf3.getText();
				
				if(password.equals(repassword))
				{
					
					PrintStream ps;
					try {
						ps = new PrintStream(Client08.selfSocket.getOutputStream());
						ps.println("1,"+name+","+password+","+signature+","+header);
						ps.flush();
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
							
					
				}else
				{
					//第一个参数是弹出框的相对位置 设置null先
					JOptionPane.showMessageDialog(null, "2次密码不一致");
				}
				
				
			}
		});

		//改变JFrame默认的布局方式BordeLayout成为表格布局 GridLayout
		jp0.add(jl1);
		jp0.add(jtf1);
		jp0.add(jl2);
		jp0.add(jpf1);
		jp0.add(jl3);
		jp0.add(jpf2);
		jp0.add(jl4);
		jp0.add(jtf2);
		jp0.add(jl5);
		jp0.add(jtf3);
		jp0.add(jb1);
		jp0.add(jb2);

		// jp0.setSize(500,500);

		this.add(jp0);
		this.setTitle("注册界面");
		this.setLocation(500, 500);
		this.setSize(icon.getIconWidth() + 30, icon.getIconHeight() + 50);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	
	public JTextField getJtf1() {
		return jtf1;
	}



	public static void main(String[] args) {
		
		
		RegisterView rv = new RegisterView();
		rv.init();
		
		
		System.out.println(rv.getJtf1().getText());
	}
	
	
	
	
}
