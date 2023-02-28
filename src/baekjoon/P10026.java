package baekjoon;

import java.io.*;
import java.util.*;

public class P10026 {
	// Member Variable
	static char[][] painting;
	static boolean[][] visited;
	static Deque<Point> q;
	static int n;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 변수 선언 및 초기화
		n = Integer.parseInt(br.readLine());
		painting = new char[n][n];
		visited = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			painting[i] = br.readLine().toCharArray();
		}
		
		int count = 0;
		q = new ArrayDeque<Point>();
		
		// 알고리즘
		// 1. 적록 색약이 아닌 사람이 본 경우 -> Painting 원본 그대로
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
					count++;
				}
			}
		}
		
		bw.write(count + " ");
		
		// 2. 적록 색약인 사람이 본 경우 -> Painting 변경
		// Painting 변경 및 변수 초기화
		count = 0;
		initialization();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
					count++;
				}
			}
		}
		
		bw.write(count + "\n");
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	// 적록 색약 Painting 변경 및 변수 초기화
	static void initialization() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				visited[i][j] = false;
				if(painting[i][j] == 'G') {
					painting[i][j] = 'R';
				}
			}
		}
	}
	
	// BFS
	static void bfs(int start_x, int start_y) {
		q.addLast(new Point(start_x, start_y));
		char RGB = painting[start_x][start_y];
		visited[start_x][start_y] = true;
		
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point cur = q.pollFirst();
				for (int k = 0; k < 4; k++) {
					int next_x = cur.x + dx[k];
					int next_y = cur.y + dy[k];
					if (check(next_x, next_y) && !visited[next_x][next_y]) {
						if (painting[next_x][next_y] == RGB) {
							q.addLast(new Point(next_x, next_y));
							visited[next_x][next_y] = true;
						}
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
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
