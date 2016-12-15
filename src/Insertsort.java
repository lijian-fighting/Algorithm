import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lijian on 2016/11/23.
 */
public class Insertsort {
    private static int size = 10;
    private static int min = Integer.MIN_VALUE;
    private static int []A =new int[size+1];
    public static void insertsort(){
        A[0] = min;
        for(int j = 2;j <= size;j++){
            int temp = A[j];
            int i = j - 1;
            while(temp < A[i]){
                A[i+1] = A[i];
                i = i - 1;
            }
            A[i+1] = temp;
        }
    }
    public static void main(String[] args)throws IOException {
        String judge = "y";
        while(judge.equals("y")||judge.equals("Y")){
            BufferedReader ss = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please input "+size+" numbers(splited by a blank)");
            String num = ss.readLine();
            String []temp = num.split(" ");
            for(int i = 0;i<temp.length;i++){
                A[i+1] = Integer.parseInt(temp[i]);
            }
            insertsort();
            for(int i =1;i<A.length;i++){
                System.out.print(A[i] + " ");
            }
            System.out.println();
            System.out.println("continue?(y/n)");
            judge = ss.readLine();
        }
    }
}
