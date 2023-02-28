package swea;

import java.io.*;
import java.util.*;

public class P1231_swea {
	// Member Variable
	static char[] tree;
	static StringBuilder sb = new StringBuilder();
	static int n;

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		for (int test_case = 1; test_case <= 10; test_case++) {

			// 변수 선언 및 초기화
			n = Integer.parseInt(br.readLine());
			tree = new char[n + 1];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int idx = Integer.parseInt(st.nextToken());
				tree[idx] = st.nextToken().charAt(0);
			}
			
			sb.append("#" + test_case + " ");
			inorder(1);
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
	
	// InOrder
	public static void inorder(int a) {
		// Left Child
		if (a * 2 <= n) {
			inorder(a * 2);
		}
		// Root
		sb.append(tree[a]);
		
		// Right Child
		if (a * 2 + 1 <= n) {
			inorder(a * 2 + 1);
		}
	}
}
