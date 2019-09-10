package Thread;



class PrinterLetter extends Thread{
	 private Printer p;
	 public PrinterLetter(Printer p) {
		 this.p=p;
	 }
	 @Override
		public void run() {
		 int i=1;
			// TODO Auto-generated method stub
		 while(true) {
			 p.printerletter();
		    if(i>52) {
		    	break;
		    }
		 }		
		}
}
class NumLetter extends Thread{ 
	private Printer p;
	  public NumLetter(Printer p) {
		  this.p=p;
	  }
	  @Override
	public void run() {
		  int j=65;
		while(true) {
			p.numletter();
		  if(j>97) {
			  break;
		  }
		}
	
	}
}
class Printer{
	private int index=1;
	int i=1;
	int j=65;
	public synchronized void printerletter() {
		
		if(index%3==0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(i++);
		index++;
		this.notifyAll();
	}
	public synchronized void numletter() {
	
		if(index%3!=0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println((char)j++);
		index++;
		this.notifyAll();
	}
	
}
public class Test15 {
public static void main(String[] args) {
	
	Printer p=new Printer();
	PrinterLetter pl=new PrinterLetter(p);
	pl.start();
	NumLetter nl=new NumLetter(p);
	nl.start();
	
}
}
