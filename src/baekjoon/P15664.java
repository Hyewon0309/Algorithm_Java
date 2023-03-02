package baekjoon;

import java.io.*;
import java.util.*;

public class P15664 {
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
		
		st = new StringTokenizer(br.readLine(), " ");
		
		nums = new int[n];
		list = new int[m];
		
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		find(0, 0);
		
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
		} else {
			int past = -1;
			for (int i = s; i < n; i++) {
				if (past != nums[i]) {
					list[d] = nums[i];
					past = nums[i];
					find(i+1, d+1);
				}
			}
		}
	}
}
