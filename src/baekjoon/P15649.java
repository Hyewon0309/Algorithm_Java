package baekjoon;

import java.io.*;
import java.util.*;

public class P15649 {
	static int[] results;
	static boolean[] visited;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		results = new int[m];
		visited = new boolean[n];
		
		findList(n, m, 0);
		
		br.close();
		bw.close();
	}
	
	public static void findList(int n, int m, int d) throws IOException {
		if (m == d) {
			for (int i = 0; i < m; i++) {
				bw.write(results[i] + " ");
			}
			bw.write("\n");
			bw.flush();
		} else {
			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					results[d] = i+1;
					visited[i] = true;
					findList(n, m, d+1);
					visited[i] = false;
				}
			}
		}
	}
}
