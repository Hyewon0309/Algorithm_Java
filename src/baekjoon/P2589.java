package baekjoon;

import java.io.*;
import java.util.*;

public class P2589 {
	// Member Variable
	static int n;
	static int m;
	static char[][] map;
	static boolean[][] visited;
	static Deque<Point> q;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	// Main Method
	public static void main(String[] args) throws IOException {
		// 변수 선언 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		visited = new boolean[n][m];
		q = new ArrayDeque<Point>();

		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int max = -1;

		// 알고리즘
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'L') {
					int res = bfs(i, j);
					max = (max < res) ? res : max;
					initialize();
				}
			}
		}

		bw.write(max + "\n");
		bw.flush();

		br.close();
		bw.close();
	}

	// Initialize
	static void initialize() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = false;
			}
		}
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

	// BFS Algorithm -> Return Final Level
	static int bfs(int start_x, int start_y) {
		int level = -1;

		q.addLast(new Point(start_x, start_y));
		visited[start_x][start_y] = true;

		while (!q.isEmpty()) {
			level++;
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Point cur = q.pollFirst();
				for (int k = 0; k < 4; k++) {
					int next_x = cur.x + dx[k];
					int next_y = cur.y + dy[k];

					if (check(next_x, next_y)) {
						if (!visited[next_x][next_y] && map[next_x][next_y] == 'L') {
							q.addLast(new Point(next_x, next_y));
							visited[next_x][next_y] = true;
						}
					}
				}
			}
		}

		return level;
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
