package polym;

class Aniaml{
	private String color;
	private int age;
	private String name;
	//功能：以字符串的形式返回对象封装的数据：
	@Override
	public String toString() {
		return "Aniaml [color=" + color + ", age=" + age + ", name=" + name + "]";
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Aniaml(String color, int age, String name) {
		super();
		this.color = color;
		this.age = age;
		this.name = name;
	}

	public Aniaml() {
		super();
	}

	public void eat() {
		System.out.println(name+",吃！");
	}
	
	public void sleep() {
		System.out.println("为"+color+"颜色的动物在睡觉1");
		
	}
}

class Dog extends Aniaml{
	private String eyeColor;
	public String getEyeColor() {
		return eyeColor;
	}
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	public Dog(String color, int age, String name, String eyeColor) {
		//调用父类构造方法：
		super(color, age, name);
		this.eyeColor = eyeColor;
	}

	public Dog() {
		super();
	}
	@Override
	public void eat() {
		System.out.println("小黑狗在吃肉！");
	}
	@Override
	public void sleep() {
		System.out.println("小黑狗在睡觉");
	}

	public void cry() {
		System.out.println(this.getName()+"的狗正在叫！");
	}
}

class Cat extends Aniaml{
	private int length;

	public Cat() {
		super();
	}
	public Cat(String color, int age, String name, int length) {
		super(color, age, name);
		this.length = length;
	}

	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	@Override
	public String toString() {
		return "Cat [length=" + length + "]";
	}
	
	public void run() {
		System.out.println(length+"的花猫在活蹦乱跳！");
	}
}

public class Test01 {
	public static void main(String[] args) {
	Aniaml a=new Dog("黑色",2,"小花","黑红色");
	a.eat();
	a = new Cat("黄色" , 3 , "小黄" , 20);
	a.eat();
			
		
	}
}
