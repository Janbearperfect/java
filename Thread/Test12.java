package Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Thread4 implements Callable{
	int sum=0;
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < 100; i++) {
			if(i%2==0) {
				System.out.println(i);
				sum+=i;
			}
		}
		return sum;
	}
}
public class Test12 {
public static void main(String[] args) {
	Thread4 t4=new Thread4();
	FutureTask futuretask=new FutureTask(t4);
	Thread thread =new Thread(futuretask);
	thread.start();
	try {
		Object sum=futuretask.get();
		System.out.println(sum);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
