package baekjoon;

import java.io.*;
import java.util.*;

public class P2559 {
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < n - k + 1; i++) {
			max = Integer.max(max, sum(i, k));
		}
		
		bw.write(max + "\n");
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static int sum(int start_idx, int k) {
		int result = 0;
		
		for (int i = 0; i < k; i++) {
			result += arr[start_idx + i];
		}
		
		return result;
	}
}
