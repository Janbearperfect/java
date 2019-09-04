package polym;
class Student{
	private int No;
	private String school;
	Student(){
		
	}
	Student(int No,String school){
		this.No=No;
		this.school=school;
	}
	public void setNo(int No) {
		this.No=No;	
	}
	public void setSchool(String school) {
		this.school=school;
	}
	public int getNo() {
		return No;
	}
	public String getSchool() {
		return school;
	}
	public void learn() {
		System.out.println(No+school+"学习");
	}
}
class Zhangsan extends Student{
	private String name;
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public Zhangsan(int No,String school,String name) {
		super(No,school);
		this.name=name;
	}
	public Zhangsan() {
		
	}
	public void learn() {
		System.out.println(this.getName()+"学习"+this.getSchool()+this.getNo());
	}
	public void play() {
		System.out.println(this.getSchool()+"玩耍"+this.getNo()+this.getName());
	}
}
class Lisi extends Student{
	private String book;
	public void setBook(String book) {
		this.book=book;
	}
	public String getBook() {
		return book;
	}
	
	public Lisi() {
		super();
	}
	public Lisi(int No,String school,String book) {
		super(No,school);
		this.book=book;
	}
	public void learn() {
		System.out.println(this.getBook()+"学习"+this.getNo()+this.getSchool());
	}
	public void showBook() {
		System.out.println(book);
	}
	
}
public class Test02 {
public static void test(Student student) {
	student.learn();
}
public static void main(String[] args) {
	Zhangsan a=new Zhangsan(10,"山西农业大学","张三");
	test(a);
	Lisi b=new Lisi(10,"山西农业大学","西游记");
	test(b);
			
	
}
}
