package internet;
/**
 * 初始化块 没有名字 不会被主动调用 只能在适当的实际自己调用
 * 		
 * 		类初始化块：当该类被真正使用的时候 执行一次
 * 						1 访问类的static的成员
 * 						2 该类作为main方法执行的主类
 * 						3 访问该类的子类
 * 						。。。
 * 						典型不是真正使用的情况 ，是用该类申明一个变量
 * 
 * 
 * 		实例初始化块：new的时候 执行构造器之前 	
 * 
 * 
 * @author UEK-N
 *
 */
public class TestGrade {
	
	public static void main(String[] args) {
		
		TestA t;//这个不是真正的使用TestA这个类！！！
		
		TestA t1 = new TestA();
		
		TestA t2 = new TestA();
		t2.s();
		
		System.out.println(TestA.a);
	}
}


class TestA
{
	
	//其实我们看源代码很多是假象的 其实初始化块会合并
	//类初始化会合并 
	//实例初始化块也会合并 合并到每个构造器开头
	static void s(){
		a = 100;
		System.out.println("类初始化块1执行....");
	}
	
	static {
		a = 1000;
		
		System.out.println("类初始化块2执行....");
	}
	
	static int a = 10;
	
	
	{
		
		System.out.println("实例初始化块1执行....");
	}
	
	{
		
		System.out.println("实例初始化块2执行....");
	}
	
	public TestA()
	{
		System.out.println("构造器执行....");
		
	}
}

