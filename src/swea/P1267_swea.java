package swea;

import java.io.*;
import java.util.*;

public class P1267_swea {
	static ArrayList<Integer> q;
	static int v;
	static ArrayList<ArrayList<Integer>> graph;
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
			
			q = new ArrayList<>();
			graph = new ArrayList<ArrayList<Integer>>();
			visited = new boolean[v+1];
			sb = new StringBuilder();
			
			for (int i = 0; i <= v; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			// 그래프 입력 받기
			for (int i = 0; i < e; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				graph.get(start).add(end);
			}
			
			// 알고리즘
			for (int i = 1; i <= v; i++) {
				if (visited[i]) {
					continue;
				} else {
					visited[i] = true;
					DFS(i);
				}
			}
			
			for (int i = 0; i < q.size(); i++) {
				sb.append(q.get(i) + " ");
			}
			
			bw.write("#" + test_case + " " + sb.toString() + "\n");
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
	
	public static void DFS(int cur) {
		System.out.println("Cur : " + cur);
		if (graph.get(cur).size() == 0) {
			q.add(cur);
			return;
		} else {
			int min_idx = v + 1;
			for (int next : graph.get(cur)) {
				int idx = q.indexOf(next);
				if (idx != -1) {
					min_idx = Integer.min(min_idx, idx);
				}
			}
			
			System.out.println("Min_idx : " + min_idx);
			
			if (min_idx == v + 1) {
				q.add(cur);
			} else {
				q.add(min_idx, cur);
			}
			
			System.out.print("Cur Stack : ");
			for (int i = 0; i < q.size(); i++) {
				System.out.print(q.get(i) + " ");
			}
			System.out.println();
			
			for (int next : graph.get(cur)) {
				if (!visited[next]) {
					visited[next] = true;
					DFS(next);
				}
			}
		}
	}
}