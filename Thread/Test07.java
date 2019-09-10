package Thread;

import Thread.Jthread.J1thread;

class Jthread extends Thread{
	int j=100;
	boolean flag=true;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(flag) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(j<0)
				break;
			System.out.println(Thread.currentThread().getName()+","+j--);
		}
	}

	class J1thread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(flag) {
				if(j>0)
					break;
				System.out.println(Thread.currentThread().getName()+","+j++);
			}
		}
	}
}
public class Test07 {
public static void main(String[] args) {
	Jthread jt=new Jthread();
	jt.setName("外部");
	jt.start();
	Jthread.J1thread j1t=jt.new J1thread();
	j1t.setName("内部");
	j1t.start();
	
	
}
}
