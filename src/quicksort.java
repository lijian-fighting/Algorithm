import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class quicksort {

	public static int size = 10;
	public static int[] A = new int[size];

	public static void INTERCHANGE(int m, int n) {
		int temp = A[m];
		A[m] = A[n];
		A[n] = temp;
	}

	public static int PARTITION(int m, int p) {
		int max = p, min = m;
		int v = A[m], i = m + 1;
		while (i < p) {
			while (A[i] < v && i <= max) {
				i = i + 1;
			}
			while (A[p] > v && min <= p) {
				p = p - 1;
			}
			if (i < p) {
				INTERCHANGE(i, p);
			} else {
				break;
			}
		}
		A[m] = A[p];
		A[p] = v;
		return p;
	}

	public static void QUICKSORT(int p, int q) {
		if (p < q) {
			int j = PARTITION(p, q);
			QUICKSORT(p, j - 1);
			QUICKSORT(j + 1, q);
		}
	}

	public static void main(String[] args) throws IOException {
		String judge = "y";
		while (judge.equals("y") || judge.equals("Y")) {
			System.out.println("用户输入" + size + "个数字(并以空格键隔开):");
			BufferedReader ss = new BufferedReader(new InputStreamReader(System.in));
			String num = ss.readLine();
			String[] temp = num.split(" ");
			for (int i = 0; i < temp.length; i++) {
				A[i] = Integer.parseInt(temp[i]);
			}
			QUICKSORT(0, size - 1);
			System.out.println("quicksort:");
			for (int i = 0; i < A.length; i++) {
				System.out.print(A[i] + " ");
			}
			System.out.println();
			System.out.println("是否继续测试?(y/n)");
			BufferedReader ee = new BufferedReader(new InputStreamReader(System.in));
			judge = ee.readLine();
		}
	}
}
