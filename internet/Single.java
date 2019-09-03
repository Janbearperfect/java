package internet;

public class Single {
public static void main(String[] args) {
	
}
}
//饿汉
class singleton{
	private singleton() {}
	private static singleton single=new singleton();
	public static singleton getInstance() {
		return single;
	}
}
//懒汉
class singleton1{
	private singleton1() {}
	private static singleton1 ss=null;
	public static singleton1 getInstance() {
		if(ss==null)
			ss=new singleton1();
		return ss;
		
	}
}