package Thread;
class Thread3 implements Runnable{
    int tickets=100;
    boolean flag=true;
    @Override
    public void run() {
    	// TODO Auto-generated method stub
    	while(flag) {
    		sellTicket();
    	}
    }
    	private synchronized void sellTicket() {
    		if(tickets<=0) {
    			System.out.println("票卖完了");
    			flag=false;
    			return;
    		}
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		System.out.println(Thread.currentThread().getName()+"卖了一张,余额"+(tickets--));
		}	
  
    }


public class Test04 {
public static void main(String[] args) {
	Thread3 a=new Thread3();
	Thread t=new Thread(a);
	Thread t1=new Thread(a);
	Thread t2=new Thread(a);
	t.start();
	t1.start();
	t2.start();

}
}
