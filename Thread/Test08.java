package Thread;
class Shandong extends Thread{
   int person=10;
	int i=1;
@Override
public void run() {
		synchronized (this) {	
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+","+i+++","+(--person));		
		}
	}
}

public class Test08 {
public static void main(String[] args) {
	Shandong sd = new Shandong();
Thread t1=new Thread(sd,"赵");
Thread t2=new Thread(sd,"钱");
Thread t3=new Thread(sd,"孙");
Thread t4=new Thread(sd,"李");
Thread t5=new Thread(sd,"周");
Thread t6=new Thread(sd,"吴");
Thread t7=new Thread(sd,"郑");
Thread t8=new Thread(sd,"王");
Thread t9=new Thread(sd,"孟");
Thread t10=new Thread(sd,"唐");
t1.start();
t2.start();
t3.start();
t4.start();
t5.start();
t6.start();
t7.start();
t8.start();
t9.start();
t10.start();
}
}
