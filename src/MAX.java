import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MAX {
	public static int size = 10;
	public static int []A = new int[size];
	public static int max(int i){
		int j = 0,k;
		if(i < size-1){
			j = max(i+1);
			if(A[i]>A[j]){
				k = i;
			}else{
				k = j;
			}
		}else{
			k = size-1;
		}
		return k;
	}
	public static void main(String[] args) throws IOException {
		 Scanner in = new Scanner(System.in);
		 String judge = "y";
		 while(judge.equals("y")||judge.equals("Y")){
			 System.out.println("用户输入"+size+"个数字(并以空格键隔开):");
			 BufferedReader ss = new BufferedReader(new InputStreamReader(System.in));
			 String num = ss.readLine();
			 String []temp = num.split(" ");
			 for(int i = 0; i < temp.length; i++){
				 A[i] = Integer.parseInt(temp[i]);
			 }
			 System.out.println("最大值为:");
			 System.out.println(A[max(0)]);
			 System.out.println("是否继续测试?(y/n)");
			 BufferedReader ee = new BufferedReader(new InputStreamReader(System.in));
			 judge =ee.readLine();
		 }
		 in.close();
	}
}
