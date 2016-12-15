import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lijian on 2016/11/23.
 */
public class OBST {
    private static int size = 5;
    private static int []P = new int[size+1];
    private static int []Q = new int[size+1];
    private static String []A = new String[size+1];

    public static int knuth(int i,int j,int [][]C){
        int min = Integer.MAX_VALUE;
        int flag = 0;
        for(int k =i+1;k<=j;k++){
            if((C[i][k-1]+C[k][j])<min){
                min = C[i][k-1]+C[k][j];
                flag = k;
            }
        }
        return flag;
    }

    public static void print(int start,int end ,int [][]R){
        for(int i = 0;i<=size;i++){
            for(int j = 0;j<=size - i;j++){
                if( j == start && (j+i) == end) {
                    System.out.print("T" + start + end + "的根节点:" + A[R[j][j+i]]+",");
                    boolean flag1,flag2;
                    if((R[j][j+i]-1) != start){
                        System.out.print("它有左子树为T"+start+(R[j][j+i]-1)+",");
                        flag1 =true;
                    }else{
                        System.out.print("无左子树"+",");
                        flag1 = false;
                    }
                    if(R[j][j+i]!=end){
                        System.out.println("它有右子树为T"+R[j][j+i]+end);
                        flag2 = true;
                    }else{
                        System.out.println("无右子树");
                        flag2 = false;
                    }
                    if(flag1){
                        print(start,R[j][j+i]-1,R);
                    }
                    if(flag2){
                        print(R[j][j+i],end,R);
                    }
                    if(!flag1&&!flag2){
                        return;
                    }
                }
            }
        }
    }

    public static void obst(){
        int [][]C = new int[size+1][size+1];
        int [][]W = new int[size+1][size+1];
        int [][]R = new int[size+1][size+1];
        for(int i = 0;i<size;i++){
            W[i][i] = Q[i];
            R[i][i] = 0;
            C[i][i] = 0;
            W[i][i+1] = P[i+1]+Q[i+1]+Q[i];
            R[i][i+1] = i+1;
            C[i][i+1] = P[i+1]+Q[i+1]+Q[i];
        }
        W[size][size] = Q[size];
        R[size][size] = 0;
        C[size][size] = 0;
        for(int m =2;m<=size;m++){
            for(int i = 0;i<=size-m;i++){
                int j = i+m;
                W[i][j] = W[i][j-1] + P[j] + Q[j];
                int k = knuth(i,j,C);
                C[i][j] = W[i][j] + C[i][k-1] +C[k][j];
                R[i][j] = k;
            }
        }
        for(int i = 0;i<=size;i++){
            for(int j = 0;j<=size - i;j++){
                System.out.printf("%-8s",W[j][j+i]+ ","+C[j][j+i]+","+R[j][j+i]);
            }
            System.out.println();
        }
        print(0,size,R);
    }

    public static void main(String []args) throws IOException {
        String judge = "y";
        while(judge.equals("y")||judge.equals("Y")){
            BufferedReader ss = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入需要"+size+"个构造检索树的标识符(用空格键隔开)");
            String flag = ss.readLine();
            String []temp = flag.split(" ");
            for(int i = 0;i<temp.length;i++){
                A[i+1] = temp[i];
            }
            System.out.println("请输入需要"+size+"个对标志符检索的概率((用空格键隔开)");
            flag = ss.readLine();
            temp = flag.split(" ");
            for(int i = 0;i<temp.length;i++){
                P[i+1] = Integer.parseInt(temp[i]);
            }
            System.out.println("请输入需要"+(size+1)+"个正被检索的概率((用空格键隔开)");
            flag = ss.readLine();
            temp = flag.split(" ");
            for(int i = 0;i<temp.length;i++){
                Q[i] = Integer.parseInt(temp[i]);
            }
            obst();
            System.out.println("continue?(y/n)");
            judge = ss.readLine();
        }
    }
}
