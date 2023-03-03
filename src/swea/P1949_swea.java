package swea;

import java.io.*;
import java.util.*;

public class P1949_swea {
	// Member Variable
	static StringBuilder sb;
	static int n;
	static int k;
	static int[][] map;
	static boolean[][] visited;
	static Point[] starts;
	static int num_starts;
	static boolean flag;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			// 변수 입력 및 초기화
			st = new StringTokenizer(br.readLine(), " ");
			
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			map = new int[n][n];
			starts = new Point[5];
			num_starts = 0;
			int max_start = 0;
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > max_start) {
						num_starts = 1;
						max_start = map[i][j];
						starts[0] = new Point(i, j);
					} else if (map[i][j] == max_start) {
						starts[num_starts] = new Point(i, j);
						num_starts++;
					}
				}
			}
			
			int max = 0;
			
			// 알고리즘
			for (int i = 0; i < num_starts; i++) {
				visited = new boolean[n][n];
				Point p = starts[i];
				
				visited[p.x][p.y] = true;
				int v = dfs(p.x, p.y) + 1;

				max = Math.max(max, v);
			}
			
			sb.append("#" + test_case + " " + max + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
	
	// DFS
	static int dfs(int x, int y) {
		int result = 0;
		
		for (int i = 0; i < 4; i++) {
			int next_x = x + dx[i];
			int next_y = y + dy[i];
			
			if (check(next_x, next_y) && !visited[next_x][next_y]) {
				int v = map[next_x][next_y];
				
				if (v < map[x][y]) {
					visited[next_x][next_y] = true;
					result = Math.max(result, dfs(next_x, next_y) + 1);
					visited[next_x][next_y] = false;
				} else {
					if (!flag) {
						for (int d = v - map[x][y] + 1; d <= k; d++) {
							flag = true;
							visited[next_x][next_y] = true;
							map[next_x][next_y] = v - d;
							result = Math.max(result, dfs(next_x, next_y) + 1);
							map[next_x][next_y] = v;
							flag = false;
							visited[next_x][next_y] = false;
						}
					}
				}
			}
		}
		
		return result;
	}
	
	// Check Point Validation
	static boolean check(int x, int y) {
		if (x < 0 || x >= n) {
			return false;
		}
		if (y < 0 || y >= n) {
			return false;
		}
		return true;
	}

	// Point Class
	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
