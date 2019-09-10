package Thread;
class ticketThread implements Runnable{
	int ticket=100;
	boolean flag=true;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(flag) {
			ticketFunction();
		}
		
	}
	private synchronized void ticketFunction() {
		if(ticket<=0) {
			flag=false;
			return;
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+","+ticket--);
	}
}
public class Test06 {
public static void main(String[] args) {
	ticketThread tf=new ticketThread();
	Thread t1=new Thread(tf);
	Thread t2=new Thread(tf);
	Thread t3=new Thread(tf);
	Thread t4=new Thread(tf);
	Thread t5=new Thread(tf);
	t1.setName("窗口1");
	t2.setName("窗口2");
	t3.setName("窗口3");
	t4.setName("窗口4");
	t5.setName("窗口5");
	t1.start();
	t2.start();
	t3.start();
	t4.start();
	t5.start();
}
}
