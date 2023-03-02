package baekjoon;

import java.io.*;
import java.util.*;

public class P15651 {
	// Member Variable
	static StringBuilder sb;
	static int n;
	static int m;
	static Deque<Integer> q;
	
	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		q = new ArrayDeque<Integer>();
		
		find(0);
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void find(int d) {
		if (d == m) {
			for (int t : q) {
				sb.append(t + " ");
			}
			sb.append("\n");
			return;
		} else {
			for (int i = 1; i <= n; i++) {
				q.addLast(i);
				find(d+1);
				q.pollLast();
			}
		}
	}
}
