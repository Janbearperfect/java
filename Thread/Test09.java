package Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockTest{
	//构建同步锁对象：
	private Lock lock = new ReentrantLock();
    /*
     * 当前线程使用完毕释放后，其他线程才能获取锁
     */
	public void lockTest(Thread thread) {
		//当前线程获取同步锁：
		lock.lock();
		System.out.println("线程：" + thread.getName() + "获取同步锁！");
		try {
			//为看出执行效果，是线程此处休眠2秒
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("线程"+thread.getName() + "发生了异常释放锁");
		}finally {
			System.out.println("线程"+thread.getName() + "执行完毕释放锁");
			//当前线程释放锁
			lock.unlock(); 
		}
	}
}
public class Test09 {
	public static void main(String[] args) {
		LockTest lockTest = new LockTest();
		//声明一个线程 “线程一”
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				lockTest.lockTest(Thread.currentThread());
			}
		} , "子线程1");
		//声明一个线程 “线程二”
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				lockTest.lockTest(Thread.currentThread());
			}
		} , "子线程2");
		thread1.start();
		thread2.start();
	}
}