package swea;

import java.io.*;
import java.util.*;

public class P4012_swea {
	// Member Variable
	static StringBuilder sb;
	static int[][] synergy;
	static int n;
	static boolean[] visited;
	static int min;

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			// 변수 선언 및 초기화
			n = Integer.parseInt(br.readLine());

			synergy = new int[n][n];
			visited = new boolean[n];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 알고리즘
			find(0, 0);
			
			sb.append("#" + test_case + " " + min + "\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	// 조합 찾기 + 계산하기
	static void find(int s, int d) {
		if (d == n /2) {
			min = Math.min(min, cal());
		} else {
			for (int i = s; i < n; i++) {
				visited[i] = true;
				find(i + 1, d + 1);
				visited[i] = false;
			}
		}
	}
	
	// 시너지 차이 계산하기
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
