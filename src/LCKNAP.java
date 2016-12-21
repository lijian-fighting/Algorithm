import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: LiJian
 * @Description:
 * @Date: Created in 19:35 2016/12/20
 * @Modified By: Lijian
 */
public class LCKNAP {
    /**
     *  背包的重量
     */
    private int M;
    /**
     * 物品的数量
     */
    private int N;
    /**
     *  每个物品的重量
     */
    private int []W;
    /**
     *  每个物品的效益
     */
    private int []P;
    /**
     * 很小的正常数
     */
    private double e;
    /**
     * 存放结果
     */
    private int []X;
    /**
     * 活节点表
     */
    private PriorityQueue<Node> livenode;

    /**
     * 初始化
     * @param m
     * @param n
     * @param e
     */
    public LCKNAP(int m,int n,double e,int []p,int []w){
        this.M = m;
        this.N = n;
        this.e = e;
        this.W = w;
        this.P = p;
        this.X = new int[n];
    }

    /**
     * 求上下界
     * @param rw
     * @param cp
     * @param k
     * @return
     */
    private double[] LUBOUND(double rw, double cp, int k) {
        double l = cp;
        double u = 0;
        double c = rw;
        for (int i = k; i < N; i++) {
            if (c < this.W[i]) {
                u = l + c * P[i] / W[i];
                for (int j = i + 1; j < N; j++) {
                    if (c >= W[j]) {
                        c -= W[j];
                        l += P[j];
                    }
                }
                return new double[]{l, u};
            }
            c -= W[i];
            l += P[i];
        }
        u = l;
        return new double[]{l, u};
    }

    /**
     * 创建新节点
     * @param par
     * @param lev
     * @param t
     * @param cap
     * @param prof
     * @param ub
     */
    public void NEWNODE(Node par,int lev,int t,double cap,double prof,double ub){
        Node node = new Node();
        node.parent = par;
        node.level = lev;
        node.tag = t;
        node.capcity = cap;
        node.prof = prof;
        node.upperBound = ub;
        livenode.add(node);
    }

    /**
     * 初始化活结点表
     */
    public void init(){
        livenode = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.upperBound > o2.upperBound){
                    return -1;
                }else if(o1.upperBound == o2.upperBound){
                    return 0;
                }else{
                    return 1;
                }

            }
        });
    }

    public void LCKNAP(){
        init();
        Node ans = null;
        NEWNODE(null, 0, -1, M, 0, 0);
        Node point = livenode.poll();
        double[] lubound = LUBOUND(M, 0, 0);
        double l = lubound[0] - e;
        point.upperBound = lubound[1];
//        System.out.println(peek);
        do{
            int i = point.level;
            double cap = point.capcity;
            double prof = point.prof;
            if(i == N){//解节点
                if(prof > l){
                    l = prof;
                    ans = point;
                }
            }else{
                if(cap>=W[i]){
                    NEWNODE(point,i+1,1,cap - W[i],prof+P[i],point.upperBound);
                }
                lubound = LUBOUND(cap,prof,i+1);
                if(lubound[1] > l){
                    NEWNODE(point,i+1,0,cap,prof,lubound[1]);
                    l = Math.max(l,lubound[0] - e);
                }
            }
            if(livenode.isEmpty())
                break;
            else
                point = livenode.poll();
//            System.out.println(peek);
        }while (point.upperBound > l);
        finish(l,ans);
    }

    private void finish(double l, Node ans) {
        System.out.println("max prof:" + l);
        for (int i = N-1; i >=0 ; i--) {
            if(ans == null)
                break;
            if(ans.tag == 1) {
                System.out.print(i + " ");
                X[i] = 1;
            }
            ans = ans.parent;
        }
        System.out.println();

    }

    public void print() {
        System.out.print("X: ");
        for (int i = 0; i < N; i++) {
            System.out.print(X[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LCKNAP l = new LCKNAP(15,4,0.01,new int[]{10,10,12,18},new int[]{2,4,6,9});
        l.LCKNAP();
        l.print();
    }
}

class Node {
    Node parent;
    int level;
    int tag;
    double capcity;
    double prof;
    double upperBound;

    @Override
    public String toString() {
        return "Node{" +
                "level=" + level +
                ", tag=" + tag +
                ", capcity=" + capcity +
                ", prof=" + prof +
                ", upperBound=" + upperBound +
                '}';
    }
}

