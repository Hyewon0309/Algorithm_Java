package swea;

import java.io.*;
import java.util.*;

public class P1861_swea {
	static StringBuilder sb;
	static int[][] map;
	static int[][] memory;
	static int n;
	
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			memory = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 알고리즘
			int max = 0;
			int max_idx = -1;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (n * n - map[i][j] < max) {
						continue;
					} else {
						int v = cal(i, j) + 1;
						if (v > max) {
							max = v;
							max_idx = map[i][j];
						} else if (v == max) {
							if (max_idx > map[i][j]) {
								max_idx = map[i][j];
							}
						}
					}
				}
			}
			
			sb.append("#" + test_case + " " + max_idx + " " + max + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static int cal(int x, int y) {
		int max = 0;
		for (int i = 0; i < 4; i++) {
			int next_x = x + dx[i];
			int next_y = y + dy[i];
			
			// 체크할 점
			if (check(next_x, next_y) && map[next_x][next_y] == map[x][y] + 1) {
				if (memory[next_x][next_y] != 0) {
					max = Math.max(max, memory[next_x][next_y] + 1);
				} else {
					max = Math.max(max, cal(next_x, next_y) + 1);
				}
			}
		}
		
		memory[x][y] = max;
		
		return max;
	}
	
	// check Point Validation
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
