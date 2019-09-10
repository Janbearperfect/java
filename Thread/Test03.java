package Thread;

public class Test03 {
	public static void main(String[] args) {
		DaemonDemo dd = new DaemonDemo();
		dd.setDaemon(true);     //当main线程结束，守护线程也结束
		dd.start();
		for(int i=1;i<=100;i++){
			System.out.println("王宝强正在认真的拍戏~"+i);
		}
	}
}
class DaemonDemo extends Thread{
	@Override
	public void run() {
		while(true){
			System.out.println("宋喆和马蓉愉快的聊天呢,哈哈哈哈");
		}
	}
}
