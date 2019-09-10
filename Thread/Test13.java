package Thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadForpools implements Runnable{
	private Integer index;
	public ThreadForpools(){
		
	}
	public ThreadForpools(Integer index) {
		this.index=index;
	}
	@Override
	public void run() {
		 try {
	            System.out.println("开始处理线程！");
	            Thread.sleep(index * 100);
	            System.out.println("我的线程标识：" + this.toString());
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		
	}
}
public class Test13 {
/*public static void main(String[] args) {
	ExecutorService cashedThreadpool=Executors.newCachedThreadPool();
	for (int i = 0; i < 4; i++) {
		final int index=i;
		cashedThreadpool.execute(new ThreadForpools(index));
		
	}
}*/
	public static void main(String[] args) {
		ExecutorService fixedThreadpool=Executors.newFixedThreadPool(2);
		for (int i = 0; i < 4; i++) {
			final int index=i;
			fixedThreadpool.execute(new ThreadForpools(index));
		}
	}
}
