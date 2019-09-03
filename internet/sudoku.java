package internet;

import java.util.Scanner;

public class sudoku {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("������һ�����������м���");
		int num = input.nextInt();
		int[][] arr = new int[num][num];
		
		int h = 0;
		int l = num/2;
		
		for(int i = 1;i<=num*num;i++){
				if(num%2!=0){
					arr[h][l]=i;
					h--;l++;
					if(h<0&&l>=num){
						l--;
						h=h+2;
						}else if(h<0){
							h=num-1;
						}else if(l>=num){
							l=0;
						}else if(arr[h][l]!=0){
							l--;
							h=h+2;
						}
					}
				}
				
		  
					 
		//--��ӡ
		 for (int i = 0; i < num; i++)
	        {
	            for (int j = 0; j < num;j++)
	            {
	                System.out.print(arr[i][j] + "\t");
	            }
	            //����
	            System.out.println();
	        }
	    
	
	}
}
