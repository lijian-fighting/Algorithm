import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lijian on 2016/11/18.
 */
public class Prim {
    private static int size = 6;
    private static int max = Integer.MAX_VALUE;
    private static int [][]cost = new int[size+1][size+1];

    //返回cost最小的边的两个点
    public  static void backmin(int [][]temp){
        int min = max;
        int m=0,n=0;
        for(int i= 1;i<=size;i++){
            for(int j = 1;j<=size;j++){
                if(cost[i][j]<min){
                    min = cost[i][j];
                    m = i;
                    n = j;
                }
            }
        }
        temp[0][0] = m;
        temp[0][1] = n;
    }

    //初始化cost成本矩阵
    public static void inia(){
        for(int i = 1;i<=size;i++){
            for(int j= 1;j<=size;j++){
                cost[i][j]=max;
            }
        }
    }

    public static void back(int [][]temp,int []near){
        int min = max;
        int m = 0,n=0;
        for(int i = 0;i<near.length;i++){
            if(near[i]!=0){
                if(cost[i][near[i]]<min){
                    min = cost[i][near[i]];
                    m = i;
                    n = near[i];
                }
            }
        }
        temp[0][0] = m;
        temp[0][1] = n;
    }

    public static void prim(){
        int []near = new int[size+1];
        int [][]T = new int[size+1][2];
        int [][]temp = new int[1][2];
        backmin(temp);
        int mincost = cost[temp[0][0]][temp[0][1]];
        T[1][0] = temp[0][0];
        T[1][1] = temp[0][1];
        for(int i = 1;i<=size;i++){
            if(cost[i][T[1][0]]<cost[i][T[1][1]]){
                near[i] = T[1][0];
            }else{
                near[i] = T[1][1];
            }
        }
        near[T[1][0]] = 0;
        near[T[1][1]] = 0;
        for(int i = 2;i < size;i++ ){
            back(temp,near);
            T[i][0] = temp[0][0];
            T[i][1] = temp[0][1];
            mincost = mincost + cost[temp[0][0]][temp[0][1]];
            near[temp[0][0]] = 0;
            for( int k = 1;k<=size;k++){
                if((near[k]!=0)&&(cost[k][near[k]]>cost[k][temp[0][0]])){
                    near[k] = temp[0][0];
                }
            }
        }
        if(mincost>=max){
            System.out.println("no spanning tree");
        }else{
            for(int i = 1;i<T.length;i++){
                if(T[i][0]!=0&&T[i][1]!=0){
                    System.out.println("edge:"+T[i][0]+"-->"+T[i][1]);
                }
            }
            System.out.println("mincost = "+ mincost );
        }
    }
    public static void iniacost(int a,int b,int dis){
        if((a <= size)&&(a>=1)&&(b<=size)&&(b>=1)){
            if(a != b) {
                cost[a][b] = dis;
                cost[b][a] = dis;
            }else{
                cost[a][b] = max;
                cost[b][a] = max;
            }
        }
    }
    public static void main(String[] args)throws IOException{
        String judge = "y";
        while(judge.equals("y")||judge.equals("Y")){
            inia();
            BufferedReader ss = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请以此输入点与点(总共"+size+"个点)之间的路径(例如1 2 57，表示1和2之间的距离为57)，并输入end表示结束输入");
            String temp = ss.readLine();
            while(!temp.equals("end")){
                String []cost = temp.split(" ");
                if((Integer.parseInt(cost[0])>size)||(Integer.parseInt(cost[1])>size)){
                    System.out.println("不存在该点");
                    return;
                }else {
                    iniacost(Integer.parseInt(cost[0]), Integer.parseInt(cost[1]), Integer.parseInt(cost[2]));
                }
                temp = ss.readLine();
            }
            prim();
            System.out.println("continue?(y/n)");
            judge = ss.readLine();
        }
    }
}
