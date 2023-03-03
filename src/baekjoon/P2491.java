package baekjoon;

import java.io.*;
import java.util.*;

public class P2491 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int nums[] = new int[n];
		
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		int plus_cnt = 1;
		int minus_cnt = 1;
		
		for (int i = 1; i < n; i++) {
			if (nums[i] < nums[i-1]) {
				plus_cnt++;
				max = Math.max(max, minus_cnt);
				minus_cnt = 1;
			} else if (nums[i] == nums[i-1]) {
				plus_cnt++;
				minus_cnt++;
			} else {
				minus_cnt++;
				max = Math.max(max, plus_cnt);
				plus_cnt = 1;
			}
		}
		
		max = Math.max(max, plus_cnt);
		max = Math.max(max, minus_cnt);
		
		bw.write(max + "\n");
		bw.flush();
		
		br.close();
		bw.close();
	}
}
