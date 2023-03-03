package baekjoon;

import java.io.*;
import java.util.*;

public class P14889 {
	static int n;
	static int[][] synergy;
	static boolean[] visited;
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n];
		
		synergy = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				synergy[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		
		find(0, 0);
		
		bw.write(min + "\n");
		
		br.close();
		bw.close();
	}
	
	static void find(int s, int d) {
		if (d == n / 2) {
			min = Math.min(min, cal());
		} else {
			for (int i = s; i < n; i++) {
				visited[i] = true;
				find(i + 1, d + 1);
				visited[i] = false;
			}
		}
	}
	
	static int cal() {
		int result = 0;
		
		int[] a = new int[n / 2];
		int[] b = new int[n / 2];
		
		int cnt_a = 0;
		int cnt_b = 0;
		
		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				a[cnt_a++] = i;
			} else {
				b[cnt_b++] = i;
			}
		}
		
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < n / 2; j++) {
				result += synergy[a[i]][a[j]];
				result -= synergy[b[i]][b[j]];
			}
		}
		
		return Math.abs(result);
	}
}
