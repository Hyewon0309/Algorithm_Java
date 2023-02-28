package baekjoon;

import java.io.*;
import java.util.*;

public class P2606 {
	static ArrayList<ArrayList<Integer>> graph;
	static Deque<Integer> q;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		// 변수 선언 및 초기화
		int n = Integer.parseInt(br.readLine());
		int e = Integer.parseInt(br.readLine());
		
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		q = new ArrayDeque<Integer>();
		visited = new boolean[n + 1];
		
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph.get(start).add(end);
			graph.get(end).add(start);
		}
		
		bw.write(bfs(1) + "\n");
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static int bfs(int start) {
		int count = 0;
		visited[start] = true;
		q.addLast(start);
		
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int cur = q.pollFirst();
				count++;
				for (int next : graph.get(cur)) {
					if (!visited[next]) {
						q.addLast(next);
						visited[next] = true;
					}
				}
			}
		}
		
		return count - 1;
	}
}
