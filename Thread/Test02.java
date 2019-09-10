package Thread;

public class Test02 {
	public static void main(String[] args) {
	PriorityDemo a=new PriorityDemo("A");
	a.setPriority(Thread.MIN_PRIORITY);
	a.start();
	PriorityDemo b=new PriorityDemo("B");
	b.setPriority(Thread.NORM_PRIORITY);
	b.start();
	PriorityDemo v=new PriorityDemo("V");
	v.setPriority(Thread.MAX_PRIORITY);
	v.start();
	}
}
class PriorityDemo extends Thread{
	public PriorityDemo(String name) {
		super(name);
	}
	@Override
	public void run() {
		for(int i=1;i<=100;i++){
			System.out.println(Thread.currentThread()+"-----"+i);
		}
	}
}
