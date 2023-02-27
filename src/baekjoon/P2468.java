package baekjoon;

import java.io.*;
import java.util.*;

public class P2468 {
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static Set<Integer> heights;
	static Deque<Point> q;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		heights = new TreeSet<Integer>();
		q = new ArrayDeque<Point>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				heights.add(map[i][j]);
			}
		}
		
		int max = 1;
		
		for (int h : heights) {
			// visited 초기화
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] <= h) {
						visited[i][j] = true;
					} else {
						visited[i][j] = false;
					}
				}
			}
			
			int cnt = 0;
			// 개수 찾기
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j]) {
						cnt++;
						bfs(i, j);
					}
				}
			}
			
			// 최대 비교
			if (max <= cnt) {
				max = cnt;
			}
		}
		
		bw.write(max + "\n");
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void bfs(int start_x, int start_y) {
		visited[start_x][start_y] = true;
		q.addLast(new Point(start_x, start_y));
		
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point cur = q.pollFirst();
				for (int j = 0; j < 4; j++) {
					int next_x = cur.x + dx[j];
					int next_y = cur.y + dy[j];
					if (check(next_x, next_y) && !visited[next_x][next_y]) {
						visited[next_x][next_y] = true;
						q.addLast(new Point(next_x, next_y));
					}
				}
			}
		}
	}
	
	static class Point {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static boolean check(int x, int y) {
		if (x < 0 || x >= n) {
			return false;
		}
		
		if (y < 0 || y >= n) {
			return false;
		}
		
		if (visited[x][y]) {
			return false;
		} else {
			return true;
		}
	}
}
