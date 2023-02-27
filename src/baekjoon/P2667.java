package baekjoon;

import java.io.*;
import java.util.*;

public class P2667 {
	static int[][] map;
	static boolean[][] visited;
	static List<Integer> cnt;
	static Deque<Point> q;
	
	static int n;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		cnt = new ArrayList<Integer>();
		q = new ArrayDeque<Point>();
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			String[] inputs = br.readLine().split("");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {	// 아직 단지로 분류 안 된 집
					bfs(i, j);
				}
			}
		}
		
		Collections.sort(cnt);
		
		sb.append(cnt.size() + "\n");
		for (int i = 0; i < cnt.size(); i++) {
			sb.append(cnt.get(i) + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void bfs(int start_x, int start_y) {
		int count = 1;
		visited[start_x][start_y] = true;
		q.addLast(new Point(start_x, start_y));
		
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point cur = q.pollFirst();
				for (int j = 0; j < 4; j++) {
					if (checkHouse(cur.x + dx[j], cur.y + dy[j]) && !visited[cur.x + dx[j]][cur.y + dy[j]]) {
						q.addLast(new Point(cur.x + dx[j], cur.y + dy[j]));
						visited[cur.x + dx[j]][cur.y + dy[j]] = true;
						count++;
					}
				}
			}
		}
		
		cnt.add(count);
	}
	
	static boolean checkHouse(int x, int y) {
		if (x < 0 || y < 0) {
			return false;
		}
		
		if (x >= n || y >= n) {
			return false;
		}
		
		if (map[x][y] == 1) {
			return true;
		} else {
			return false;
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
}
