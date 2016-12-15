import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GCD {
	public static int gcd(int a,int b){
		if(b == 0){
			return a;
		}else{
			return gcd(b,a%b);
		}
	}
	public static void main(String[] args) throws IOException {
		 Scanner in = new Scanner(System.in);
		 String judge = "y";
		 while(judge.equals("y")||judge.equals("Y")){
			 System.out.println("请输入数字，并回车结束输入，连续输入两次");
			 int a = in.nextInt();
			 int b = in.nextInt();
			 System.out.println(a+"和"+b+"的最大公因子为:");
			 System.out.println(gcd(a,b));
			 System.out.println("是否继续测试?(y/n)");
			 BufferedReader ss = new BufferedReader(new InputStreamReader(System.in));
			 judge =ss.readLine();
		 }
		 in.close();
	}
}
