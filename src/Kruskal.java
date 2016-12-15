import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
/**
 * Created by lijian on 2016/11/21.
 */
public class Kruskal {
    private static int size = 6;
    private static int max = Integer.MAX_VALUE;
    private static int[][] cost = new int[size + 1][size + 1];
    private static int[] parent = new int[size + 1];
    private static PriorityQueue<Edge> edgeList=new PriorityQueue<Edge>();

    //初始化，将所有的cost赋值为最大值
    public static void inia() {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                cost[i][j] = max;
            }
        }
    }

    //根据用户的输入，为对应cost赋值
    public static void iniacost(int a, int b, int dis) {
        if ((a <= size) && (a >= 1) && (b <= size) && (b >= 1)) {
            if (a != b) {
                cost[a][b] = dis;
            } else {
                cost[a][b] = max;
            }
        }
    }

    //构造堆栈
    public static void min() {
        //构造cost的min-堆(相当于进行排序)
        int rlength=cost.length;
        int clength=cost[0].length;
        for(int i=1;i<rlength;i++) {
            for (int j = 1; j < clength; j++) {
                if (cost[i][j] < max) {
                    Edge e = new Edge(i, j, cost[i][j]);
                    edgeList.add(e);
                }
            }
        }
    }

    //在parent中查找该节点的父节点
    public static int Find(int u) {
        int j = u;
        while(parent[j]>0){
            j = parent[j];
        }
        return j;
    }

    //将两个集合合并
    public static void union(int i,int j){
        parent[i] = j;
    }

    //kruskal算法
    public static void kruskal() {
     int [][]T = new int[size+1][2];
        for (int i = 1; i < parent.length; i++) {
            parent[i] = -1;
        }
        min();
        int mincost=0,count =1,judge =1;
        //kruskal算法计算最小生成树
        while(count <= size && !edgeList.isEmpty()){
            Edge temp = edgeList.poll();
            int j = Find(temp.getU());
            int k = Find(temp.getV());
            if( j != k){
                count = count + 1;
                T[count][0] = temp.getU();
                T[count][1] = temp.getV();
                mincost = mincost + temp.getweight();
                union(j,k);
            }
        }
        if(count!=size){
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

    public static void main(String[] args) throws IOException {
        String judge = "y";
        while (judge.equals("y") || judge.equals("Y")) {
            inia();
            BufferedReader ss = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请以此输入点与点(总共" + size + "个点)之间的路径(例如1 2 57，表示1和2之间的距离为57)，并输入end表示结束输入");
            String temp = ss.readLine();
            while (!temp.equals("end")) {
                String[] cost = temp.split(" ");
                if ((Integer.parseInt(cost[0]) > size) || (Integer.parseInt(cost[1]) > size)) {
                    System.out.println("不存在该点");
                    return;
                } else {
                    iniacost(Integer.parseInt(cost[0]), Integer.parseInt(cost[1]), Integer.parseInt(cost[2]));
                }
                temp = ss.readLine();
            }
            kruskal();
            System.out.println("continue?(y/n)");
            judge = ss.readLine();
        }
    }
}
class Edge implements Comparable<Edge> {
    int u;
    int v;
    int weight;
    public Edge(int u, int v, int weight) {
        super();
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int getU(){
        return u;
    }
    public int getV(){
        return v;
    }
    public int getweight(){
        return weight;
    }
    @Override
    public int compareTo(Edge e) {
        if(e.getweight()==weight)
            return 0;
        else if(weight<e.getweight())
            return -1;
        else
            return 1;

    }
}
