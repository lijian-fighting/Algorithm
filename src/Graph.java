import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: LiJian
 * @Description:
 * @Date: Created in 21:31 2016/12/7
 * @Modified By: Lijian
 */
public class Graph {
    /**
     * 无穷取最大值的一半
     */
    private static int MAX = Integer.MAX_VALUE / 2;
    /**
     * 图的点数
     */
    private int pointnum;
    /**
     * 图的边数
     */
    private int egdenum;
    /**
     * 有向图或无向图,false表示无向图，true表示有向图，默认为无向图
     */
    private boolean isdirect = false;
    /**
     * 边的权重
     */
    private int[][]cost;
    /**
     * 初始化图
     * @param pn
     * @param en
     * @param type
     */
    public Graph(int pn,int en,boolean type) {
        this.pointnum = pn;
        this.egdenum = en;
        this.isdirect = type;
        this.cost = new int[pn][pn];
        for (int i = 0; i < pn; i++) {
            Arrays.fill(cost[i], MAX);
        }
    }

    /**
     * 为图的边增加权重
     * @param i
     * @param j
     * @param cost
     * @return
     */
    public Graph addEdge(int i,int j,int cost){
        this.cost[i-1][j-1] = cost;
        if(!this.isdirect){
            this.cost[j-1][i-1] = cost;
        }
        return this;
    }

    /**
     * 获取点的数量
     * @return
     */
    public int getPointnum(){
        return pointnum;
    }

    /**
     * 获取边的数量
     * @return
     */
    public int getEgdenum(){
        return egdenum;
    }

    /**
     * 获得某条边的代价
     * @param i
     * @param j
     * @return
     */
    public int getCost(int i,int j){
        return cost[i-1][j-1];
    }

    /**
     * 获得最大值
     * @return
     */
    public int getMAX(){
        return MAX;
    }
    public ArrayList<Integer> getNebor(int u){
        ArrayList<Integer> temp= new ArrayList<Integer>();
        for(int i = 0;i<pointnum;i++){
            if(getCost(u+1,i+1)<MAX){
                temp.add(i);
            }
        }
        return temp;
    }
}
