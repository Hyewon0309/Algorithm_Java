package swea;

import java.io.*;
import java.util.*;

public class P1232_swea {
	// Member Variable
	static Node[] tree;
	
	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			// 변수 선언 및 초기화
			int n = Integer.parseInt(br.readLine());
			tree = new Node[n+1];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int idx = Integer.parseInt(st.nextToken());
				String value = st.nextToken();
				if (st.hasMoreTokens()) {
					int left = Integer.parseInt(st.nextToken());
					int right = Integer.parseInt(st.nextToken());
					tree[idx] = new Node(left, right, value);
				} else {
					tree[idx] = new Node(value);
				}
			}
			
			// 알고리즘
			sb.append("#" + test_case + " " + cal(1) + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	// 사칙연산
	static int cal(int a) {
		int result = 0;
		Node n = tree[a];
		String value = n.value;
		
		if (value.equals("+")) {
			result = cal(n.l_child) + cal(n.r_child);
		} else if (value.equals("-")) {
			result = cal(n.l_child) - cal(n.r_child);
		} else if (value.equals("*")) {
			result = cal(n.l_child) * cal(n.r_child);
		} else if (value.equals("/")) {
			result = cal(n.l_child) / cal(n.r_child);
		} else {
			result = Integer.parseInt(value);
		}
		
		return result;
	}
	
	// Node Class
	static class Node {
		int l_child;
		int r_child;
		String value;
		
		Node(String value) {
			this.value = value;
		}
		
		Node(int l_child, int r_child, String value) {
			this.l_child = l_child;
			this.r_child = r_child;
			this.value = value;
		}
	}
}
