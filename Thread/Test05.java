package Thread;
class rabbit extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for(int i=1;i<30;i++) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i%10==0) {
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName()+","+i);
		}
	}
}
class turtle extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for(int i=1;i<30;i++) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i%10==0) {
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName()+","+i);
		}
	}
}
public class Test05 {
public static void main(String[] args) {
	rabbit r=new rabbit();
	r.setName("兔子");
	r.start();
	turtle t=new turtle();
	t.setName("乌龟");
	t.start();
}
}
