package swea;

import java.io.*;
import java.util.*;

public class P1233_swea {
	// Member Variable
	static int n;
	static String[] tree;
	static boolean flag;
	static String operators = "+-*/";

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= 10; test_case++) {
			// 변수 선언 및 초기화
			n = Integer.parseInt(br.readLine());

			// 짝수면 무조건 유효하지 않음
			if (n % 2 == 0) {
				sb.append("#" + test_case + " 0\n");
				for (int i = 0; i < n; i++) {
					br.readLine();
				}
				continue;
			}

			tree = new String[n + 1];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int idx = Integer.parseInt(st.nextToken());
				tree[idx] = st.nextToken();
			}

			// 알고리즘
			flag = false;
			bfs(1);
			
			sb.append("#" + test_case + " ");
			if (flag) {
				sb.append("0\n");
			} else {
				sb.append("1\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
	
	// BFS (트리 탐색)
	static void bfs(int a) {
		if (flag) {
			return;
		}
		
		if (a * 2 <= n) {
			// Leaf node가 아님 -> 연산자여야함
			if (!operators.contains(tree[a])) {
				flag = true;
				return;
			} else {
				bfs(a * 2);
				bfs(a * 2 + 1);
			}
		} else {
			// Leaf Node
			if (operators.contains(tree[a])) {
				flag = true;
				return;
			}
		}
	}
}
