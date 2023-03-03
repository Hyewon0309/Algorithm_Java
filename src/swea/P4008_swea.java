package swea;

import java.io.*;
import java.util.*;

public class P4008_swea {
	static int n;
	static StringBuilder sb;
	static int[] operators;
	static int[] nums;
	static int[] list;
	static boolean[] visited;
	static int max;
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine(), " ");
			operators = new int[n-1];
			int idx = 0;
			nums = new int[n];
			list = new int[n-1];
			
			for (int i = 0; i < 4; i++) {
				int cnt = Integer.parseInt(st.nextToken());
				for (int j = 0; j < cnt; j++) {
					operators[idx++] = i;
				}
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			visited = new boolean[n-1];
			
			// 알고리즘
			find(0);
			
			sb.append("#" + test_case + " " + (max - min) + "\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void find(int d) {
		if (n-1 == d) {
			int v = cal();
			max = Math.max(max, v);
			min = Math.min(min, v);
			return;
		} else {
			int past = -1;
			for (int i = 0; i < n-1; i++) {
				if (!visited[i]) {
					visited[i] = true;
					if (past != operators[i]) {
						list[d] = operators[i];
						past = operators[i];
						find(d+1);
					}
					visited[i] = false;
				}
			}
		}
	}
	
	static int cal() {
		int result = nums[0];
		
		for (int i = 0; i < n-1; i++) {
			switch (list[i]) {
			case 0:
				result += nums[i + 1];
				break;
			case 1:
				result -= nums[i + 1];
				break;
			case 2:
				result *= nums[i + 1];
				break;
			case 3:
				result /= nums[i + 1];
				break;
			}
		}
		
		return result;
	}
}
