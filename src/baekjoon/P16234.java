package baekjoon;

import java.io.*;
import java.util.*;

public class P16234 {
	// Member Variable
	static int n;
	static int[][] map;
	static int[][] visited;
	static Deque<Point> q;
	static int L;
	static int R;
	static boolean flag;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// 변수 선언 및 초기화
		n = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int count = -1;
		flag = false;
		q = new ArrayDeque<Point>();

		// 알고리즘
		while (!flag) {
			visited = new int[n][n];
			flag = true;
			int cnt = 1;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visited[i][j] == 0) {
						bfs(i, j, cnt++);
					}
				}
			}
			count++;
		}

		bw.write(count + "\n");

		br.close();
		bw.close();

	}

	// BFS
	static void bfs(int x, int y, int r) {

		q.addLast(new Point(x, y));
		visited[x][y] = r;

		int result = 0;
		int cnt = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point cur = q.pollFirst();

				result += map[cur.x][cur.y];
				cnt++;
				for (int j = 0; j < 4; j++) {
					int next_x = cur.x + dx[j];
					int next_y = cur.y + dy[j];

					if (check(next_x, next_y) && visited[next_x][next_y] == 0) {
						int v = Math.abs(map[next_x][next_y] - map[cur.x][cur.y]);
						if (v <= R && v >= L) {
							visited[next_x][next_y] = r;
							q.addLast(new Point(next_x, next_y));
						}
					}
				}
			}
		}

		if (cnt > 1) {
			flag = false;
			result = result / cnt;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visited[i][j] == r) {
						map[i][j] = result;
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

	// Point Class
	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
