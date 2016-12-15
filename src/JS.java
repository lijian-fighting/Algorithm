import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lijian on 2016/11/17.
 */
public class JS {
    private static int size = 6;
    private static int []D = new int[size+1]; //用于存放作业的期限
    private static int []P = new int[size+1]; //用于存放作业的效益
    private static int []J = new int[size+1]; //用于存放最优解的第i个作业

    //时间期限相同，但效益不同的情况不需要进行判断，因为已经按效益排序，肯定是先取效益
    //大的进行插入，如果下一个时间相同的进来，因为时间相同，会无法插入
    public static void js(){
        D[0] = 0;
        J[0] = 0;
        int k = 1,r;
        J[1] = 1;
        for(int i = 2;i <= size;i++){
            r = k;
            while((D[J[r]]>D[i])&&(D[J[r]]!=r)){
                r=r-1;
            }
            if((D[J[r]]<=D[i])&&(D[i]>r)){
                for(int j = k;j>=r+1;j--){
                    J[j+1] = J[j];
                }
                J[r+1] = i;
                k = k+1;
            }
        }
    }

    public static void sort(){
        for (int i = 2; i <= size; i++) {
            int currentDate = D[i];
            int currentProfit = P[i];
            int position = i;
            for (int j = i - 1; j >= 1; j--) {
                if (P[j] < currentProfit) {
                    D[j + 1] = D[j];
                    P[j + 1] = P[j];
                    position -= 1;
                } else {
                    break;
                }
            }
            D[position] = currentDate;
            P[position] = currentProfit;
        }
    }
    public static void main(String[] args) throws IOException {
        String judge = "y";
        while(judge.equals("y")||judge.equals("Y")){
            BufferedReader ss = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入"+size+"个工作期限(并以空格隔开)");
            String date = ss.readLine();
            String[] temp = date.split(" ");
            for(int i = 0;i< temp.length;i++){
                D[i+1] = Integer.parseInt(temp[i]);
            }
            System.out.println("请输入"+size+"个效益(并以空格隔开)");
            String profit = ss.readLine();
            String []temp1 = profit.split(" ");
            for(int i = 0;i<temp1.length;i++){
                P[i+1] = Integer.parseInt(temp1[i]);
            }
            sort();
            System.out.println("按效益排序后为:");
            for(int i = 1;i<= size;i++){
                System.out.println("作业"+i+"：效益="+P[i]+" 时间期限="+D[i]);
            }
            js();
            System.out.println("作业排序贪心算法结果为:");
            for(int i = 1 ;i <= size;i++){
                if(J[i]!=0){
                    System.out.print("作业"+J[i]+"  ");
                }
            }
            System.out.println();
            System.out.println("continue?(y/n)");
            judge = ss.readLine();
        }
    }
}
