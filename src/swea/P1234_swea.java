package swea;

import java.io.*;
import java.util.*;

public class P1234_swea {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		for (int test_case = 1; test_case <= 10; test_case++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			char[] nums = st.nextToken().toCharArray();

			Deque<Character> q = new ArrayDeque<Character>();

			for (char c : nums) {
				if (q.isEmpty()) {
					q.addLast(c);
				} else {
					if (q.peekLast() == c) {
						while (!q.isEmpty() && q.peekLast() == c) {
							q.pollLast();
						}
					} else {
						q.addLast(c);
					}
				}
			}

			bw.write("#" + test_case + " ");
			while (!q.isEmpty()) {
				bw.write(q.pollFirst());
			}
			bw.write("\n");
			bw.flush();
		}

		br.close();
		bw.close();
	}
}
