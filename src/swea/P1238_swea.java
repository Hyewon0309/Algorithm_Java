package swea;

import java.io.*;
import java.util.*;

public class P1238_swea {
	static ArrayList<HashSet<Integer>> graph;
	static int[] dist;
	static Deque<Integer> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			// 변수 선언 및 초기화
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int start_node = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<HashSet<Integer>>();
			for (int i = 0; i <= 101; i++) {
				graph.add(new HashSet<Integer>());
			}
			dist = new int[101];
			q = new ArrayDeque<Integer>();
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n / 2; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				graph.get(start).add(end);
			}
			
			bfs(start_node);
			
			int max = -1;
			int max_idx = -1;
			
			for (int i = 1; i <= 100; i++) {
				if (dist[i] >= max) {
					max = dist[i];
					max_idx = i;
				}
			}
			
			sb.append("#" + test_case + " " + max_idx + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void bfs(int start) {
		int d = 1;
		dist[start] = d;
		q.addLast(start);
		
		while (!q.isEmpty()) {
			d++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int cur = q.pollFirst();
				for (int next : graph.get(cur)) {
					if (dist[next] == 0) {
						q.addLast(next);
						dist[next] = d;
					}
				}
			}
		}
	}
}
	