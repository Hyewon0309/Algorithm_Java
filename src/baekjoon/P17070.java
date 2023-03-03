package baekjoon;

import java.io.*;
import java.util.*;

public class P17070 {
	// Member Variable
	static int n;
	static int[][] map;
	static int cnt;

	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 1, 0};
	
	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		// 변수 선언 및 초기화
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cnt = 0;
		// 알고리즘
		int start_x = 0;
		int start_y = 1;
		
		dfs(start_x, start_y, 0);
		
		bw.write(cnt + "\n");
		bw.flush();

		br.close();
		bw.close();
	}

	// DFS
	static void dfs(int x, int y, int d) {
		if (x == n - 1 && y == n - 1) {
			cnt++;
			return;
		} else {
			int start = 0;
			int end = 3;
			
			if (d == 0) {
				end = 2;
			} else if (d == 2) {
				start = 1;
			}

			for (int i = start; i < end; i++) {
				int next_x = x + dx[i];
				int next_y = y + dy[i];
			
				if (check(next_x, next_y)) {
					if (i == 1) {
						if (map[x][next_y] == 1 || map[next_x][y] == 1) {
							continue;
						}
					}
					
					if (map[next_x][next_y] == 0) {
						dfs(next_x, next_y, i);
					}
				}
			}
		}
	}

	// Point Validation Check
	static boolean check(int x, int y) {
		if (x < 0 || x >= n) {
			return false;
		}
		if (y < 0 || y >= n) {
			return false;
		}
		return true;
	}
}
