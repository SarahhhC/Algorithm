
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class sort_comparison {

	static int comparison_exchage=0;
	static int comparison_merge=0;
	static int comparison_quick=0;

	static int[] Result_merge = new int[10];
	static int[] Result_quick = new int[10];

	public static void main(String[] args) throws IOException 
	{
		int[][] Lists1 = new int[20][10];
		int[][] Lists2 = new int[20][10];
		int[][] Lists3 = new int[20][10];

		BufferedReader b = new BufferedReader(new FileReader("/Users/Sarahhh/Documents/test.txt"));
		String line;
		for(int i=0;i<20;i++){
			line = b.readLine();
			StringTokenizer tk = new StringTokenizer(line);
			while(tk.hasMoreTokens()){
				for(int j=0;j<10;j++){		
					String token = tk.nextToken();
					Lists1[i][j] = Integer.parseInt(token);
					Lists2[i][j] = Integer.parseInt(token);
					Lists3[i][j] = Integer.parseInt(token);
				}
			}
		}
		b.close(); 
		
		for(int i=0;i<Lists1.length;i++){
			exchageSort(Lists1[i].length-1, Lists1[i]);
		}
		for(int i=0;i<Lists2.length;i++){
			mergeSort(0,Lists2[i].length-1,Lists2[i]);
		}
		for(int i=0;i<Lists3.length;i++){
			quickSort(0,Lists3[i].length-1,Lists3[i]);
		}
		
		System.out.print("\nAverage # of comparisons for Exchange Sort: "+comparison_exchage/Lists1.length);
		System.out.print("\nAverage # of comparisons for Merge Sort: "+comparison_merge/Lists2.length);
		System.out.print("\nAverage # of comparisons for Quick Sort: "+comparison_quick/Lists3.length);

	}

	public static void exchageSort(int n, int[] S){

		for(int i=0; i<n; i++){
			for(int j=i+1; j<=n; j++){
				if(S[j] < S[i]){
					int temp = S[i];
					S[i] = S[j];
					S[j] = temp;
				}
				comparison_exchage++;
			}
		}
	
	}

	public static void mergeSort(int low, int high,int[] S){

		if(low<high){
			int mid = (low+high)/2;

			mergeSort(low,mid,S);
			mergeSort(mid+1,high,S);
			merge(low,mid,high,S);
		}
	}

	static void merge(int low,int mid, int high,int[] S){

		int h = low;
		int i = low;
		int j = mid+1;

		while(h<=mid && j<=high){
			comparison_merge++;
			if(S[h] <= S[j]){
				Result_merge[i] = S[h];
				h++;
			}
			else{
				Result_merge[i] = S[j];
				j++;
			}
			i++;
		}

		if(h>mid){
			for(int k=j; k<=high; k++){
				Result_merge[i] = S[k];
				i++;
			}
		}
		else{
			for(int k=h; k<=mid; k++){
				Result_merge[i] = S[k];
				i++;
			}
		}

		for(int k=low; k<=high; k++){
			S[k] = Result_merge[k];
		}

	}

	public static void quickSort(int m,int p,int[] S){

		if(m<p){
			int j = partition(m,p,S);	
			quickSort(m,j-1,S);
			quickSort(j+1,p,S);
		}
		for(int k=0; k<S.length; k++){
			Result_quick[k] = S[k];
		}
	}

	static int partition(int m, int p,int[] S){

		int x = S[m];
		int i=m;

		while(true){
			while(S[i]<=x && i<=p){
				
				if(i == S.length-1){
					comparison_quick++;
					break;
				}
				i=i+1;
				comparison_quick++;
			}
			while(S[p]>x){	
				p=p-1;
				comparison_quick++;
			}

			if(i<p) {
				comparison_quick++;
				int temp = S[i];
				S[i] = S[p];
				S[p] = temp;
			}
			else 
				break;
		}
		S[m] = S[p];
		S[p] = x;

		return p;

	}

}
