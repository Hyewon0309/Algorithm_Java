package baekjoon;

import java.io.*;
import java.util.*;

public class P15656 {
	static int n;
	static int m;
	static int[] nums;
	static int[] list;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		nums = new int[n];
		list = new int[m];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		find(0);
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void find(int d) {
		if (d == m) {
			for (int i : list) {
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
		} else {
			for (int i = 0; i < n; i++) {
				list[d] = i;
				find(d+1);
			}
		}
	}
}
