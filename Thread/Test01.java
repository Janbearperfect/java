package Thread;

public class Test01 {
public static void main(String[] args) {
	Thread1 t=new Thread1();
	t.setName("子线程1");
	t.start();
    Thread2 m=new Thread2() ;
    m.start();
    
    
}
}
class Thread1 extends Thread{
	public void run() {
		for(int i=0;i<1000;i++) {
			if(i%2==0) {
				System.out.println(i);
			}
		}
	}	
}
class Thread2 extends Thread{
	public void run() {
		for(int i=1;i<1000;i++) {
			if(i%2!=0) {	  
				System.out.println(i);
			}
		}
	}
}

