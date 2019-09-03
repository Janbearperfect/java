package internet;

import java.util.Random;
import java.util.Scanner;



public class Chess {
public static void main(String[] args) {
	String[][] chess=new String[11][11];
	for(int i=0;i<11;i++) {
		for(int j=0;j<11;j++) {	
			chess[i][j]="口";
		}	
	}
	chessFunction(chess);

	chessFunction(chess);
	
	Scanner input=new Scanner(System.in);
	while(true) {
		int x=input.nextInt(chess.length);
		int y=input.nextInt(chess[x].length);
		
		if(chess[x][y].equals("口")) {
			chess[x][y]="赢";
			chessFunction(chess);	
		}else {
			System.out.println("再次输入");
		}	
		while(true) {
			System.out.println("电脑下子");
			Random random=new Random();
			int a=random.nextInt(chess.length);
			int b=random.nextInt(chess.length);
			if(chess[a][b].equals("口")) {
				chess[a][b]="输";
				chessFunction(chess);
				break;
			}
			else {
				System.out.println("再次输入");
			}
		}
		for (String[] strings : chess) {
			String line=null;
			for (String string : strings) {
				line+=string;
			}
			if(line.contains("赢赢赢赢赢")) {
				System.out.println("获得胜利");
				break;
			}
		}
		for(int i=0;i<chess.length-4;i++) {
			for(int j=0;j<chess.length;j++) {
				if(chess[i][j].equals("赢") && chess[i+1][j].equals("赢") && chess[i+2][j].equals("赢")&&chess[i+3][j].equals("赢")
						&&chess[i+4][j].equals("赢")){
					System.out.println("获得胜利");
					break;
				}
				else if(chess[i][j].equals("赢") && chess[i+1][j+1].equals("赢") && chess[i+2][j+2].equals("赢")&&chess[i+3][j+3].equals("赢")
							&&chess[i+4][j+4].equals("赢"))
				{
					System.out.println("获得胜利");
					break;
				}
				else if(i>=4&&j<=6) {
					if(chess[i][j].equals("赢") && chess[i-1][j-1].equals("赢") && chess[i-2][j-2].equals("赢")&&chess[i-3][j-3].equals("赢")
						&&chess[i-4][j-4].equals("赢"))
			{
				System.out.println("获得胜利");
				break;
			}
			}
		}
	}
	}
	
	
	
	}

private static void chessFunction(String[][] chess) {
	for (String[] strings : chess) {
		System.out.println();
		for (String string : strings) {
			System.out.print(string);
		}
	}
}


	           }

