package baekjoon;

import java.io.*;
import java.util.*;

public class P15652 {
	static int n;
	static int m;
	static int[] list;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list = new int[m];
		
		find(1, 0);
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void find(int s, int d) {
		if (m == d) {
			for (int i : list) {
				sb.append(i + " ");
			}
			sb.append("\n");
			return;
		} else {
			for (int i = s; i <= n; i++) {
				list[d] = i;
				find(i, d + 1);
			}
		}
	}
}
