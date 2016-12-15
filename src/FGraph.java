/**
 * @Author: LiJian
 * @Description: 多段图向前处理算法
 * @Date: Created in 11:58 2016/12/8
 * @Modified By: Lijian
 */
public class FGraph {
    /**
     * 定义一个图
     */
    private Graph graph;

    /**
     * 定义k段
     */
    private int k ;
    /**
     * 最小成本路径
     */
    private int []P;
    /**
     * 每个状态所做的决策
     */
    private int []D;
    /**
     *
     */
    private int []COST;

    /**
     * 初始化图以及变量
     * @param g
     * @param k
     */
    public FGraph(Graph g,int k){
        this.graph = g;
        this.k = k;
        this.P = new int[k+1];
        this.D = new int[g.getPointnum()];
        this.COST = new int[g.getPointnum()];
    }

    /**
     * 求出每个段之间任意两点的最短距离，并将后面一个节点放到数组中
     */
    public void FGRAPH(){
        int n = this.graph.getPointnum();
        this.COST[n -1] = 0;
        for(int j = n - 2 ;j >= 0;j--){
            int min = Integer.MAX_VALUE;
            int flag = 0;
            for(int i = 0; i <= n - 1; i++){
                if(this.graph.getCost(j+1,i+1) < this.graph.getMAX()){
                    if( this.graph.getCost(j+1,i+1) + this.COST[i] < min){
                        min = this.graph.getCost(j+1,i+1) + this.COST[i];
                        flag = i;
                    }
                }
            }
            COST[j] = min;
            D[j] = flag;
        }
        P[1] = 0;P[k] = n-1;
        for(int j=2;j<=k-1;j++){
            P[j] = D[P[j-1]];
        }
    }

    public void print(){
        System.out.println("最短路径:");
        for(int i = 1;i<P.length;i++){
            System.out.print(P[i]+1);
            if(i != P.length-1){
                System.out.print("-->");
            }
        }
    }


    public static void main(String[] args) {
        Graph g = new Graph(12,21,true);
        g.addEdge(1,2,9).addEdge(1,3,7).addEdge(1,4,3).addEdge(1,5,2)
                .addEdge(2,6,4).addEdge(2,7,2).addEdge(2,8,1)
                .addEdge(3,6,2).addEdge(3,7,7)
                .addEdge(4,8,11)
                .addEdge(5,7,11).addEdge(5,8,8)
                .addEdge(6,9,6).addEdge(6,10,5)
                .addEdge(7,9,4).addEdge(7,10,3)
                .addEdge(8,10,5).addEdge(8,11,6)
                .addEdge(9,12,4).addEdge(10,12,2).addEdge(11,12,5);
        FGraph fg = new FGraph(g,5);
        fg.FGRAPH();
        fg.print();
    }
}
