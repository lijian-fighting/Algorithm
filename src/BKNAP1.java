/**
 * @Author: LiJian
 * @Description:
 * @Date: Created in 15:34 2016/12/13
 * @Modified By: lijian
 */
public class BKNAP1 {
    /**
     * 物品的重量数组
     */
    private int []W;
    /**
     * 物品的效益数组
     */
    private int []P;
    /**
     * 背包容量M
     */
    private int M;
    /**
     * 物品数量n
     */
    private int N;
    /**
     * 最终效益
     */
    private int fp;
    /**
     * 最终重量
     */
    private int fw;
    /**
     * 用于判断是否放入背包,1表示放入,0表示
     */
    private int []X;
    /**
     * 初始化
     * @param n
     * @param m
     */
    public BKNAP1(int n,int m){
        this.W = new int[n+1];
        this.P = new int[n+1];
        this.X = new int[n+1];
        this.M = m;
        this.N = n;
        this.addW(1,1,11).addW(2,11,21).addW(3,21,31)
                .addW(4,23,33).addW(5,33,43).addW(6,43,53)
                .addW(7,45,55).addW(8,55,65);
    }
    /**
     * 两个数组的赋值
     * @param a
     * @param b
     */
    public void equal(int []a,int []b){
        for(int i = 1;i<=N;i++){
            a[i] = b[i];
        }
    }

    /**
     * 返回界限
     * @param p
     * @param w
     * @param k
     * @return
     */
    public int BOUND(int p,int w,int k){
        int b = p;
        int c = w;
        for(int i = k+1;i<=N;i++){
            c = c+W[i];
            if(c<M){
                b = b + P[i];
            }else{
                return (b+(1-(c-M)/W[i])*P[i]);
            }
        }
        return b;
    }
    public void print(){
        for(int i = 1;i<=N;i++){
            System.out.print("X"+i+"="+X[i]+" ");
        }
    }
    /**
     *
     */
    public void BKNAP1(int cw,int cp,int k,int []Y){
        if (k >= N) {
            if (cp > fp) {
                fp = cp;
                fw = cw;
                equal(X,Y);
            }
            return;
        }
        if (cw + W[k] <= M) {//向左孩子节点移动
            Y[k] = 1;
//            ++count;
            BKNAP1(cw + W[k], cp + P[k], k + 1,Y);
            Y[k] = 0;
        }
        //向右孩子节点移动
        //由限界函数检测是否有必要移动
        if (BOUND(cp, cw, k) > fp) {
//            ++count;
            BKNAP1(cw, cp, k + 1, Y);
        }
    }

    public static void main(String[] args) {
        BKNAP1 b = new BKNAP1(8,110);
        int []Y = new int[b.N+1];
        b.BKNAP1(0,0,1,Y);
        b.print();
    }
    /**
     * 增加物品重量和效益
     * @param i
     * @param w
     * @param p
     * @return
     */
    public BKNAP1 addW(int i,int w,int p){
        W[i] = w;
        P[i] = p;
        return this;
    }
}
