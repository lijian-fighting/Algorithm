import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @Author: LiJian
 * @Description:
 * @Date: Created in 16:44 2016/12/12
 * @Modified By: Lijian
 */
public class Art {
    /**
     *  DFN数组:深度优先树(深度优先检索的访问次序)
     */
    private int []DFN;
    /**
     *  L数组:通过一条子孙路径且至多后随一条逆边所可能到达的最低深度优先树
     */
    private int []L;
    /**
     * 用于生成graph
     */
    private Graph g;
    /**
     * num
     */
    private  int num = 1;
    /**
     * 全程栈
     */
    private Stack<int []> S;
    /**
     * 初始化函数
     * @param n
     */
    public Art(int n){
        this.DFN = new int[n];
        this.L = new int[n];
        Arrays.fill(DFN,0);
        g = new Graph(12,13,false);
        g.addEdge(1,2,1).addEdge(1,4,1)
                .addEdge(2,3,1).addEdge(2,5,1).addEdge(2,7,1).addEdge(2,8,1)
                .addEdge(3,4,1).addEdge(3,9,1).addEdge(3,10,1)
                .addEdge(5,7,1).addEdge(5,6,1).addEdge(5,8,1)
                .addEdge(7,8,1);
        S = new Stack<int []>();
    }

    /**
     * ART函数
     * @param u
     * @param v
     */
    public void ART(int u,int v){
        DFN[u] = num;
        L[u] = num;
        num = num + 1;
        ArrayList<Integer> near = new ArrayList<Integer>();
        near.addAll(g.getNebor(u));
        for(Integer w:near){
            if( v != w && DFN[w] <DFN[u]){
                int []temp = new int[]{u,w};
                S.push(temp);
            }
            if(DFN[w] == 0){
                ART(w,u);
                if(L[w] >= DFN[u]){
                    System.out.println("new biconnected component");
                    int x , y;
                    do{
                        int []temp = S.pop();
                        x = temp[0];
                        y = temp[1];
                        System.out.println((x+1)+","+(y+1));
                    }while((x!=u||y!=w)&&(x!=w||y!=u));
                }
                L[u] = L[u]>L[w]?L[w]:L[u];
            }else if( w != v){
                L[u] = L[u]>DFN[w]?DFN[w]:L[u];
            }
        }
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
        Art art = new Art(12);
        art.ART(0,0);
    }
}
