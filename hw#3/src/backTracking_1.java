import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class backTracking_1 {
	static int[] w = new int[50];
	static int number;
	static int MAX_CAPACITY = 0;
	static int[] X = new int[50];
	static int[] a = {-1,0,1};
	static int i = 0;
	static int getTotalVisitingNodes = 0;

	public static void main(String[] args) throws IOException {
		int[] inputList = readInput();
		number = inputList[0];
		for (int i = 1; i <= number; i++)
			w[i] = inputList[i];
		MAX_CAPACITY = inputList[number+1];

		backTracking(number);
		System.out.print(":(" + getTotalVisitingNodes + ", " + getTotalNodes() + ", ");
		System.out.print((float)getTotalVisitingNodes/getTotalNodes() + ")");
	}

	static int[] readInput() throws IOException {
		int[] List = new int[100];
		int p=0;

		BufferedReader b = new BufferedReader(new FileReader("test3.txt"));
		StringTokenizer tk = new StringTokenizer(b.readLine());

		while(tk.hasMoreTokens()) {
			String read_token = tk.nextToken();
			StringTokenizer tk2= new StringTokenizer(read_token);
			while(tk2.hasMoreTokens()) {
				int token = Integer.parseInt(tk2.nextToken());
				List[p] = token;
				p++;
			}
		}
		b.close(); 

		return List;
	}

	static void backTracking(int n) {
		int k = 1;
		for (int i = 1; i <= n; i++)
			X[i] = a[0];
		while(0 < k && k <= n) {
			getNext(k);
			if (X[k] == a[0])
				k--;
			else {
				if (k == n)
					print(X, k);
				else
					k++;
			}
		}
	}

	static void getNext(int k) {
		i = X[k] + 1;
		int l = 2;
		while(i < l) {
			i++;
			X[k] = a[i];
			getTotalVisitingNodes++;
			if(bound(k) == true)
				return;
		}
		if(i == l)
			X[k] = a[0];
	}

	static boolean bound(int k) {
		int sum = 0;
		int w_sum = 0;
		for (int i = 1; i <= k; i++)
			sum += (X[i])*w[i];
		for (int i = k + 1; i <= number; i++)
			w_sum += w[i];
		
		if (sum <= MAX_CAPACITY && sum + w_sum >= MAX_CAPACITY)
			return true;
		else
			return false;
	}

	static void print(int[] list, int k) {
		System.out.print("(");
		for (int i = 1; i <= number; i++) {
			System.out.print(X[i]);
			if (i != number)
				System.out.print(",");
		}
		System.out.print(")");
	}
	
	static int getTotalNodes() {
		int m = a.length - 1;
		int sum = 0;
		for (int i = 0; i <= number; i++)
			sum += Math.pow(m, i);
		
		return sum;
	}
}
