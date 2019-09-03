package internet;



public class SingletonTest {
public static void main(String[] args) {
    
}
}//饿汉
class Singleton{
    private Singleton() {}
    private static Singleton single = new Singleton();
    public static Singleton getInstance() {
        return single;
    }
}//懒汉
class Singleton1{
	private Singleton1() {}
	private static Singleton1 ss=null;
	public static Singleton1 getInstance() {
		if(ss==null) {
			ss=new Singleton1();
		}
		return ss;
	}
}
