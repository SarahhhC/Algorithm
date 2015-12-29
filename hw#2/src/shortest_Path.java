import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class shortest_Path {

	static int number = 0;
	static int[][] D = new int[10][10];
	static int[][] W = new int[10][10];
	static int[][] P = new int[10][10];
	static int k = 0;
	static int[] s = new int[50];
	static int[] d = new int[50];
	static int lastPathIndex = 1;
	static int[] shortestPathNodeList = new int[50];

	public static void main(String[] args) throws IOException {
		int[] List = readInput();
		setListToAssign_InfinityNumber(List);
		number = List[0];
		setListToAssign_SuitableNumber(List);

		floyd(number,D,W,P);

		System.out.println("D");
		floydResultPrint(D);
		System.out.println("P");
		floydResultPrint(P);

		for(int i=1 ; i<=k ; i++){
			System.out.print(s[i] + "-");
			path(s[i],d[i],P);
			System.out.print(d[i]);
			
			shortestPathNodeList[0] = s[i];
			shortestPathNodeList[lastPathIndex] = d[i];
			System.out.print(" length: " + calculatePathLength());

			System.out.println();
		}
	}

	static int[] readInput() throws IOException {
		int[] List = new int[100];
		int p=0;

		BufferedReader b = new BufferedReader(new FileReader("test.txt"));
		StringTokenizer tk = new StringTokenizer(b.readLine());

		while(tk.hasMoreTokens()) {
			String read_token = tk.nextToken();
			StringTokenizer tk2= new StringTokenizer(read_token,"\"");
			while(tk2.hasMoreTokens()) {
				String first_token = tk2.nextToken();
				StringTokenizer tk3 = new StringTokenizer(first_token,",");
				while(tk3.hasMoreTokens()) {
					int second_token = Integer.parseInt(tk3.nextToken());
					List[p] = second_token;
					p++;
				}
			}
		}
		b.close(); 

		return List;
	}

	static void setListToAssign_InfinityNumber(int[] List) {
		for(int i=0 ; i<List.length ; i++)
			if(List[i]==-1)
				List[i] = 88888;	//-1 -> infinity number
	}

	static void setListToAssign_SuitableNumber(int[] List) {
		int p=1;
		for(int i=1 ; i<=number ; i++)
			for(int j=1 ; j<=number ; j++)
				W[i][j] = List[p++];
		k = List[p];
		for(int i=1 ; i<=k ; i++) {
			s[i] = List[p+1];
			d[i] = List[p+2];
			p = p+2;
		}
	}

	static void floyd(int n, int[][] D,int[][] W, int[][] P) {
		int i,j,k;
		for(i=1 ; i<=n ; i++) {
			for(j=1 ; j<=n ; j++) {
				D[i][j] = W[i][j];
				P[i][j] = 0;
			}
		}
		for(k=1 ; k<=n ; k++) {
			for(i=1 ; i<=n ; i++) {
				for(j=1 ; j<=n ; j++) {
					if(D[i][j] > D[i][k]+D[k][j]) {
						D[i][j] = min(D[i][j], D[i][k]+D[k][j]);
						P[i][j] = k;
					}
				}
			}
		}

	}

	static int min(int a,int b) {
		int min;
		if(a < b) min=a;
		else min=b;

		return min;
	}

	static void floydResultPrint(int[][] R) {
		for(int i=1 ; i<=number ; i++) {
			for(int j=1 ; j<=number ; j++)
				System.out.print(R[i][j]+" ");
			System.out.println();
		}
	}

	static void path(int q, int r, int[][] P) {
		if(P[q][r] != 0) {
			path(q, P[q][r], P);
			print(P[q][r],lastPathIndex);
			path(P[q][r], r, P);
		}
	}

	static void print(int pathNode,int i) {
		System.out.print(pathNode + "-");
		shortestPathNodeList[i] = pathNode;
		i++;
	}
	
	static int calculatePathLength() {
		int length = 0;
		for(int i=0 ; i< lastPathIndex; i++) {
			int s = shortestPathNodeList[i];
			int d = shortestPathNodeList[i+1];
			length += D[s][d];
			
			shortestPathNodeList[i] = 0;
		}
		
		return length;
	}

}

