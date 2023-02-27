package baekjoon;

import java.io.*;
import java.util.*;

public class P2636 {
	// Member Variable
	static int[][] map;
	static boolean[][] visited;
	static int[][] cheese;
	static Deque<Point> q;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	static int n;
	static int m;

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// 변수 선언 및 초기화
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new boolean[n][m];
		cheese = new int[n][m];
		q = new ArrayDeque<Point>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;
		int result = -1;

		while (!mapIsEmpty()) {
			result = bfs();
			cnt++;
			removeCheese();
		}
		
		bw.write(cnt + "\n" + result + "\n");
		bw.flush();

		br.close();
		bw.close();
	}

	static int bfs() {
		int result = 0;
		visited[0][0] = true;
		q.addLast(new Point(0, 0));

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point cur = q.pollFirst();
				for (int j = 0; j < 4; j++) {
					int next_x = cur.x + dx[j];
					int next_y = cur.y + dy[j];

					if (check(next_x, next_y)) {
						visited[next_x][next_y] = true;
						if (map[next_x][next_y] == 1) { // 치즈가 있는 점
							cheese[next_x][next_y] = -1;
							result++;
						} else { // 치즈가 없는 점
							q.addLast(new Point(next_x, next_y));
						}
					}
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

		if (!visited[x][y]) {
			return true;
		} else {
			return false;
		}
	}

	// Remove Cheese & Re-Initialize Cheese to 0 & Re-Initialize Visited to false
	static void removeCheese() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] += cheese[i][j];
				cheese[i][j] = 0;
				visited[i][j] = false;
			}
		}
	}

	// Check Map is Empty
	static boolean mapIsEmpty() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	// Point class
	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
