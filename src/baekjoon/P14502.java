package baekjoon;

import java.io.*;
import java.util.*;

public class P14502 {
	// Member Variable
	static int n;
	static int m;

	static int[][] map;
	static boolean[][] visited;
	static int max;
	static Deque<Point> q;

	static List<Point> virus;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new boolean[n][m];

		virus = new ArrayList<Point>();
		max = Integer.MIN_VALUE;
		q = new ArrayDeque<Point>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new Point(i, j));
				}
			}
		}

		find(0, 0, 0);
		bw.write(max + "\n");

		br.close();
		bw.close();
	}

	// 3개의 벽을 세우는 모든 경우의 수 찾기
	static void find(int d, int x, int y) {
		if (d == 3) {
			// 계산
			max = Math.max(max, cal());
			return;
		} else {
			if (x >= n) {
				return;
			}

			// (x, y) -> (x, m)까지 탐색
			for (int i = y; i < m; i++) {
				if (map[x][i] == 0) {
					map[x][i] = 3;
					if (i == m - 1) {
						find(d + 1, x + 1, 0);
					} else {
						find(d + 1, x, i + 1);
					}
					map[x][i] = 0;
				}
			}

			// (x+1, 0) -> (n, m)까지 탐색
			for (int i = x + 1; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 0) {
						map[i][j] = 3;
						if (j == m - 1) {
							find(d + 1, i + 1, 0);
						} else {
							find(d + 1, i, j + 1);
						}
						map[i][j] = 0;
					}
				}
			}
		}
	}

	// 안전구역 계산
	static int cal() {
		int result = virus.size();
		visited = new boolean[n][m];
		
		for (Point p : virus) {
			q.addLast(p);
			visited[p.x][p.y] = true;
		}

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point cur = q.pollFirst();
				for (int j = 0; j < 4; j++) {
					int next_x = cur.x + dx[j];
					int next_y = cur.y + dy[j];

					if (check(next_x, next_y) && !visited[next_x][next_y]) {
						visited[next_x][next_y] = true;
						result++;
						if (map[next_x][next_y] == 0) {
							q.addLast(new Point(next_x, next_y));
						}
					}
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					result++;
				}
			}
		}

		return n * m - result;
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

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
