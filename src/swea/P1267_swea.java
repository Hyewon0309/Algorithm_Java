package swea;

import java.io.*;
import java.util.*;

public class P1267_swea {
	static Deque<Integer> q;
	static int v;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] cnt;
	static boolean[] visited;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		// 변수 선언 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		for (int test_case = 1; test_case <= 10; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			q = new ArrayDeque<Integer>();
			graph = new ArrayList<ArrayList<Integer>>();
			cnt = new int[v + 1];
			visited = new boolean[v + 1];
			sb = new StringBuilder();

			for (int i = 0; i <= v; i++) {
				graph.add(new ArrayList<Integer>());
			}

			st = new StringTokenizer(br.readLine(), " ");
			// 그래프 입력 받기
			for (int i = 0; i < e; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				graph.get(end).add(start);
				cnt[start]++;
			}

			// 알고리즘
			for (int i = 1; i <= v; i++) {
				if (!visited[i]) {
					dfs(i);
				}
			}

			while (!q.isEmpty()) {
				sb.append(q.pollLast() + " ");
			}

			bw.write("#" + test_case + " " + sb.toString() + "\n");
			bw.flush();
		}

		br.close();
		bw.close();
	}

	public static void dfs(int cur) {
		if (cnt[cur] != 0) {
			return;
		} else {
			q.addLast(cur);
			visited[cur] = true;
			for (int next : graph.get(cur)) {
				if (!visited[next]) {
					cnt[next]--;
					dfs(next);
				}
			}
		}
	}
}