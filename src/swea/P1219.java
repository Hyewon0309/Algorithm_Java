package swea;

import java.io.*;
import java.util.*;

public class P1219 {
	
	static int isConnected;
	
	static int[] a;
	static int[] b;
	static boolean[] visited;
	
	static Deque<Integer> stack = new ArrayDeque<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			// Input
			st = new StringTokenizer(br.readLine(), " ");
			if (test_case != Integer.parseInt(st.nextToken())) {
				System.err.println("Input Error!");
			}
		
			a = new int[100];
			b = new int[100];
			visited = new boolean[100];
			
			int n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				int start_node = Integer.parseInt(st.nextToken());
				int end_node = Integer.parseInt(st.nextToken());
				
				if (a[start_node] == 0) {
					a[start_node] = end_node;
				} else {
					b[start_node] = end_node;
				}
			}
			
			// Algorithm
			isConnected = 0;
			stack.push(0);
			visited[0] = true;
			solve();
			
			// Output
			bw.write("#" + test_case + " " + isConnected + "\n");
 		}
		
		br.close();
		bw.close();
	}
	
	public static void solve() {
		// 종료 조건 - 길 찾을 수 없음
		if (stack.isEmpty() || isConnected == 1) {
			return;
		}
		
		// 종료 조건 - B로 가는 길 찾음
		if (stack.peek() == 99) {
			isConnected = 1;
			return;
		}
		
		int cur = stack.peek();

		// A 배열 먼저 찾기
		if (isConnected == 0 && a[cur] != 0 && !visited[a[cur]]) {
			stack.push(a[cur]);
			visited[a[cur]] = true;
			solve();
			visited[a[cur]] = false;
			stack.pop();
		}
		
		if (isConnected == 0 && b[cur] != 0 && !visited[b[cur]]) {
			stack.push(b[cur]);
			visited[b[cur]] = true; 
			solve();
			visited[b[cur]] = false;
			stack.pop();
		}
	}
}
