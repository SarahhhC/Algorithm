import java.io.IOException;
import java.util.Scanner;

public class backTracking_2 {
	static int number;
	static int[] X = new int[11];
	static int[] a;
	static int i = 0;
	static int getTotalVisitingNodes = 0;
	
	public static void main(String[] args) {
		
		Scanner c = new Scanner(System.in);
		System.out.print("4에서 10까지의 정수를 입력하세요: ");
		number = c.nextInt();
		a = new int[number + 1];
		for (int i = 0; i <= number; i++)
			a[i] = i;
		
		backTracking(number);
		System.out.print(":(" + getTotalVisitingNodes + ", " + getTotalNodes() + ", ");
		System.out.print((float)getTotalVisitingNodes/getTotalNodes() + ")");
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
		i = X[k];
		
		while(i < number) {
			i++;
			X[k] = a[i];
			getTotalVisitingNodes++;
			if(bound(k) == true)
				return;
		}
		if(i == number)
			X[k] = a[0];
	}
	
	static boolean bound(int k) {
		for (int i = 1; i < k; i++) {
			if ((X[k] == X[i]) || (k - i == Math.abs(X[k] - X[i])))
				return false;
		}
		return true;
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