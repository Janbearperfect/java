package Thread;
class ticketThread implements Runnable{
int ticket=100;
boolean flag=true;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(flag) {
			tickteFun();
		}
	}
	private synchronized void tickteFun() {
		if(ticket<=0) {
			flag=false;
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+","+(ticket--));
	}
	
	
	
}
public class Test16 {
public static void main(String[] args) {
	
}
}

