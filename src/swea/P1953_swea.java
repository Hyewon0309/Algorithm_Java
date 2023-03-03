package swea;

import java.io.*;
import java.util.*;

public class P1953_swea {
	// Member Variable
	static StringBuilder sb;
	static int n;
	static int m;
	static int map[][];
	static boolean visited[][];
	static Deque<Point> q;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			// 변수 선언 및 초기화
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			int start_x = Integer.parseInt(st.nextToken());
			int start_y = Integer.parseInt(st.nextToken());

			int time = Integer.parseInt(st.nextToken());
			map = new int[n][m];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 알고리즘
			int result = find(start_x, start_y, time);
			sb.append("#" + test_case + " " + result + "\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	// BFS
	static int find(int start_x, int start_y, int time) {
		int count = 0;
		q = new ArrayDeque<Point>();
		visited = new boolean[n][m];

		q.addLast(new Point(start_x, start_y));
		visited[start_x][start_y] = true;

		while (time-- > 0) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Point cur = q.pollFirst();
				count++;
				int v = map[cur.x][cur.y];

				// 상
				if (v == 1 || v == 2 || v == 4 || v == 7) {
					int next_x = cur.x + dx[0];
					int next_y = cur.y + dy[0];

					if (check(next_x, next_y) && !visited[next_x][next_y]) {
						int next_v = map[next_x][next_y];
						if (next_v == 1 || next_v == 2 || next_v == 5 || next_v == 6) {
							q.addLast(new Point(next_x, next_y));
							visited[next_x][next_y] = true;
						}
					}
				}

				// 하
				if (v == 1 || v == 2 || v == 5 || v == 6) {
					int next_x = cur.x + dx[2];
					int next_y = cur.y + dy[2];

					if (check(next_x, next_y) && !visited[next_x][next_y]) {
						int next_v = map[next_x][next_y];
						if (next_v == 1 || next_v == 2 || next_v == 4 || next_v == 7) {
							q.addLast(new Point(next_x, next_y));
							visited[next_x][next_y] = true;
						}
					}
				}

				// 좌
				if (v == 1 || v == 3 || v == 6 || v == 7) {
					int next_x = cur.x + dx[3];
					int next_y = cur.y + dy[3];

					if (check(next_x, next_y) && !visited[next_x][next_y]) {
						int next_v = map[next_x][next_y];
						if (next_v == 1 || next_v == 3 || next_v == 4 || next_v == 5) {
							q.addLast(new Point(next_x, next_y));
							visited[next_x][next_y] = true;
						}
					}
				}

				// 우
				if (v == 1 || v == 3 || v == 4 || v == 5) {
					int next_x = cur.x + dx[1];
					int next_y = cur.y + dy[1];

					if (check(next_x, next_y) && !visited[next_x][next_y]) {
						int next_v = map[next_x][next_y];
						if (next_v == 1 || next_v == 3 || next_v == 6 || next_v == 7) {
							q.addLast(new Point(next_x, next_y));
							visited[next_x][next_y] = true;
						}
					}
				}
			}
		}

		return count;
	}

	// Check Point Validation;
	static boolean check(int x, int y) {
		if (x < 0 || x >= n) {
			return false;
		}
		if (y < 0 || y >= m) {
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
