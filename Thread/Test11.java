package Thread;
class Consumer1 extends Thread{
	private cleark c;
	public Consumer1(cleark c) {
		this.c=c;
	}
	@Override
	public void run() {
	
		while(true) {
			c.cumsumer();
		}
	}
}
class Product extends Thread{
	private cleark c;

	public Product(cleark c) {
      this.c=c;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			c.product();
		}
	}
}
class cleark{
  int count=0;
  public synchronized void product(){//默认锁对象，this
	  if(count>=100) {
		 try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
	  }
	  System.out.println("消费"+","+(++count));
		 this.notify();
  }
  public synchronized void cumsumer() {
	  if(count<=0) {
		  try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	  }
	  System.out.println((--count));
	  this.notify();
  }
}
 public class Test11 {
public static void main(String[] args) {
cleark c=new cleark();
Product p=new Product(c);
p.start();
Consumer1 c1=new Consumer1(c);
c1.start();

	
}
}
