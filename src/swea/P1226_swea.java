package swea;

import java.io.*;
import java.util.*;

public class P1226_swea {
	static int[][] map;
	static boolean[][] visited;
	static Deque<Point> q;
	
	// 위, 오, 아래, 왼
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= 10; test_case++) {
			// test_case 입력 확인
			if (test_case != Integer.parseInt(br.readLine())) {
				System.err.println("Input Error!");
			}

			// 변수 선언 및 초기화
			map = new int[16][16];
			visited = new boolean[16][16];
			q = new ArrayDeque<Point>();
			int start_x = -1;
			int start_y = -1;
			int dest_x = -1;
			int dest_y = -1;
			
			for (int i = 0; i < 16; i++) {
				String[] nums = br.readLine().split("");
				for (int j = 0; j < 16; j++) {
					map[i][j] = Integer.parseInt(nums[j]);
					if (map[i][j] == 2) {
						start_x = i;
						start_y = j;
					}
					if (map[i][j] == 3) {
						dest_x = i;
						dest_y = j;
					}
				}
			}
			
			// 알고리즘
			visited[start_x][start_y] = true;
			BFS(start_x, start_y);
			
			sb.append("#" + test_case + " ");
			if (visited[dest_x][dest_y]) {
				sb.append("1\n");
			} else {
				sb.append("0\n");
			}
		}
		
		bw.write(sb.toString());

		br.close();
		bw.close();
	}
	
	static void BFS(int start_x, int start_y) {
		q.addLast(new Point(start_x, start_y));
		
		while (!q.isEmpty()) {
			Point cur = q.pollFirst();
			visited[cur.x][cur.y] = true;
			for (int i = 0; i < 4; i++) {
				if (!visited[cur.x + dx[i]][cur.y+dy[i]] && !checkWall(cur.x + dx[i], cur.y + dy[i])) {
					q.addLast(new Point(cur.x + dx[i], cur.y + dy[i]));
				}
			}
		}
	}
	
	static boolean checkWall(int x, int y) {
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
