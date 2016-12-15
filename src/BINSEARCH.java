import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class BINSEARCH{
	public static int size = 10;
	public static int []A = new int[size];
	public static int bin_search(int key,int start,int end){
		if(start < end){
			int mid = (start+end)/2;
			if(key == A[mid]){
				return mid;
			}else if(key > A[mid]){
				return bin_search(key,mid+1,end);
			}else{
				return bin_search(key,start,mid);
			} 
		}else{
			return -1;
		}
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
			 Arrays.sort(A);
			 System.out.println("输入数据排序后:");
			 for(int i = 0;i<A.length;i++){
				 if(i == A.length-1){
					 System.out.println(A[i]);
				 }else{
					 System.out.print(A[i]+" ");
				 }
			 }
			 System.out.println("用户输入查找的数:");
			 int b = in.nextInt();
			 int position = bin_search(b,0,size-1);
			 if(position<0){
				 System.out.println("没有这个数");
			 }else{
				 System.out.println("该数位于排序后的第"+(position+1)+"位");
			 }
			 System.out.println("是否继续测试?(y/n)");
			 BufferedReader ee = new BufferedReader(new InputStreamReader(System.in));
			 judge =ee.readLine();
		 }
		 in.close();
	}
}