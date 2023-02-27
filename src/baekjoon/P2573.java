package baekjoon;

import java.io.*;
import java.util.*;

public class P2573 {
	// Member Variable
	static int n;
	static int m;

	static int[][] map;
	static int[][] minus;
	static boolean[][] visited;
	static Deque<Point> q;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// 변수 선언 및 초기화
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		minus = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		int cnt = 0;

		// 알고리즘
		while (!mapIsEmpty()) {
			calMinus();
			reduceHeights();
			cnt++;

			if (dividedIntoTwo()) {
				result = cnt;
				break;
			}
			initialize();
		}

		bw.write(result + "\n");

		br.close();
		bw.close();
	}

	// Initialize
	static void initialize() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				minus[i][j] = 0;
				visited[i][j] = false;
			}
		}
	}

	// Reduce Heights
	static void reduceHeights() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] - minus[i][j] <= 0) {
					map[i][j] = 0;
				} else {
					map[i][j] -= minus[i][j];
				}
			}
		}
	}

	// Calculate heights of each cell to be reduced
	static void calMinus() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0) {
					minus[i][j] = calHeights(i, j);
				}
			}
		}
	}

	// Calculate heights to be reduced (for one cell)
	static int calHeights(int x, int y) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			int next_x = x + dx[i];
			int next_y = y + dy[i];
			if (check(next_x, next_y)) {
				if (map[next_x][next_y] == 0) {
					result++;
				}
			}
		}
		return result;
	}

	// Point Validation Check
	static boolean check(int x, int y) {
		if (x < 0 || x >= n) {
			return false;
		}
		if (y < 0 || y >= m) {
			return false;
		}
		return true;
	}

	// Check if map is empty
	static boolean mapIsEmpty() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] >= 1) {
					return false;
				}
			}
		}
		return true;
	}

	// Check if ice is divided into two or more pieces
	static boolean dividedIntoTwo() {
		boolean flag = false;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {
					if (flag) {
						return true;
					} else {
						visited[i][j] = true;
						dfs(i, j);
						flag = true;
					}
				}
			}
		}
		return false;
	}

	static void dfs(int start_x, int start_y) {
		for (int i = 0; i < 4; i++) {
			int next_x = start_x + dx[i];
			int next_y = start_y + dy[i];
			if (check(next_x, next_y)) {
				if (map[next_x][next_y] >= 1 && !visited[next_x][next_y]) {
					visited[next_x][next_y] = true;
					dfs(next_x, next_y);
				}
			}
		}
	}

	// Point Class
	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
