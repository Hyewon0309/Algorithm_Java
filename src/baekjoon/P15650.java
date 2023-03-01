package baekjoon;

import java.io.*;
import java.util.*;

public class P15650 {
	static Deque<Integer> q;
	static boolean[] visited;
	static StringBuilder sb;
	static int n;
	static int m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		q = new ArrayDeque<Integer>();
		visited = new boolean[n+1];
		sb = new StringBuilder();
		
		find(1);
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void find(int s) {
		// 종료 조건
		if (q.size() == m) {
			for (int i : q) {
				sb.append(i + " ");
			}
			sb.append("\n");
		} else {
			for (int i = s; i <= n; i++) {
				if (!visited[i]) {
					visited[i] = true;
					q.addLast(i);
					find(i + 1);
					q.pollLast();
					visited[i] = false;
				}
			}
		}
	}
}
