package internet;

public class test模板设计模式 {
public static void main(String[] args) {
	
}
}
abstract class CommonSpeeder{
	private double Turnrate;
	public CommonSpeeder() {
		
	}
	public CommonSpeeder(double Turnrate) {
		this.Turnrate=Turnrate;
	}
	public double computerSpeeder()
	{
		return computerZC()/Turnrate;
	}
	public abstract double computerZC();	
}

