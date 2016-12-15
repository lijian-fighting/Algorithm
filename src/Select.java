import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lijian on 2016/11/10.
 */
public class Select {
    private static int size = 100000;
    private static int r = 1000;
    private static int[]A = new int[size];
    private static int max = Integer.MAX_VALUE;
    public static void INTERCHANGE(int m,int n){
        int temp =A[m];
        A[m] = A[n];
        A[n] = temp;
    }
    public static int PARTITION(int m ,int p){
        int max = p,min = m;
        int v = A[m],i = m+1;
        while(i<p){
            while(A[i] <= v && i <= max){
                i=i+1;
            }
            while(A[p] >v && min<=p){
                p=p-1;
            }
            if(i<p){
                INTERCHANGE(i,p);
            }else{
                break;
            }
        }
        A[m] = A[p];
        A[p] = v;
        return p;
    }

    public static void INSERTIONSORT(int m,int n){
        for (int i = m+1; i <= n; i++) {
            int currentValue = A[i];
            int position = i;
            for (int j = i - 1; j >= 0; j--) {
                if (A[j] > currentValue) {
                    A[j + 1] = A[j];
                    position -= 1;
                } else {
                    break;
                }
            }
            A[position] = currentValue;
        }
    }

    public static int select2(int m,int p,int k){
        int n,j;
        if(p - m +1<=r) {
            INSERTIONSORT(m, p);
            return (m + k - 1);
        }else {
            while (true) {
                n = p - m + 1;
                for (int i= 1; i <= (int) Math.floor(n * 1.0 / r); i++) {
                    INSERTIONSORT(m + (i-1)*r, m + (i) * r - 1);
                    INTERCHANGE(m + i-1, (int) (m + (i-1) * r + Math.floor(r * 1.0 / 2)));
                }
                if(n>=r) {
                    j = select2(m, m + (int) Math.floor(n * 1.0 / r) - 1, (int) Math.ceil(Math.floor(n * 1.0 / r) / 2));
                    INTERCHANGE(m, j);
                    j = p;
                    j = PARTITION(m, j);
                }else{
                    INSERTIONSORT(m,p);
                    j = m +k -1;
                }
                if (j - m +1 == k) {
                    return j;
                } else if (j - m +1 > k) {
                    p = j - 1;
                } else {
                    k = k - (j - m +1);
                    m = j + 1;
                }
            }
        }
    }

    public static void main(String[] args)throws IOException{
        String judge = "y";
        while(judge.equals("y")||judge.equals("Y")){
            System.out.println("Initialize test data");
            for(int i = 0; i < size; i++) {
                A[i] =(int)(1+Math.random()*max);
            }
            System.out.println("Initialize successfully!");
            System.out.println("Please input the number k(The kth small number)");
            BufferedReader ss = new BufferedReader(new InputStreamReader(System.in));
            int k = Integer.parseInt(ss.readLine());
            long starTime=System.currentTimeMillis();
            int  m  = select2(0,A.length-1,k);
            System.out.println("The "+k+"th small number is "+A[m]);
            long endTime=System.currentTimeMillis();
            System.out.println("The time of the select2 is :"+(endTime - starTime)+"ms");
            System.out.println("continue?(y/n)");
            judge = ss.readLine();
        }
    }
}
