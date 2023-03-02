package baekjoon;

import java.io.*;
import java.util.*;

public class P15686 {
	// Member Variable
	static int n;
	static int m;
	static List<Point> chicken;
	static List<Point> home;
	static int[][] dist;
	static Deque<Integer> selected;
	static int min;
	static boolean[] visited;

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// 변수 선언 및 초기화
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		chicken = new ArrayList<Point>();
		home = new ArrayList<Point>();
		selected = new ArrayDeque<Integer>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				int v = Integer.parseInt(st.nextToken());
				if (v == 1) { // 집
					home.add(new Point(i, j));
				} else if (v == 2) { // 치킨집
					chicken.add(new Point(i, j));
				}
			}
		}

		// 각 집에서 각 치킨집까지의 치킨 거리 저장할 배열
		dist = new int[home.size()][chicken.size()];
		visited = new boolean[chicken.size()];
		min = Integer.MAX_VALUE;

		// 알고리즘
		calDist();
		select(0, 0);

		bw.write(min + "\n");
		bw.flush();

		br.close();
		bw.close();
	}

	// 각 집에서 각 치킨집까지의 치킨 거리 저장
	static void calDist() {
		for (int i = 0; i < home.size(); i++) {
			for (int j = 0; j < chicken.size(); j++) {
				Point h = home.get(i);
				Point ch = chicken.get(j);
				dist[i][j] = Math.abs(h.x - ch.x) + Math.abs(h.y - ch.y);
			}
		}
	}

	// M개의 치킨 집 뽑기
	static void select(int s, int d) {
		// 종료 조건
		if (d == m) {
			int v = calCityDist();
			if (min > v) {
				min = v;
			}
			return;
		} else {
			for (int i = s; i < chicken.size(); i++) {
				if (!visited[i]) {
					selected.addLast(i);
					visited[i] = true;
					select(i + 1, d + 1);
					selected.pollLast();
					visited[i] = false;
				}
			}
		}
	}

	// 도시의 치킨 거리 구하기
	static int calCityDist() {
		int result = 0;

		for (int i = 0; i < home.size(); i++) {
			// 각 집의 치킨 거리 구하기
			int min = Integer.MAX_VALUE;

			for (int idx : selected) {
				if (dist[i][idx] < min) {
					min = dist[i][idx];
				}
			}

			// 도시의 치킨 거리 계산
			result += min;
		}

		return result;
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
