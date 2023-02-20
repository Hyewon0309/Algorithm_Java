package swea;

import java.io.*;
import java.util.*;

public class P1206_swea {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int[] dx = {-2, -1, 1, 2};
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int result = 0;
			
			int[] heights = new int[n];
			
			for (int i = 0; i < n; i++) {
				heights[i] = (Integer.parseInt(st.nextToken()));
			}
			
			// 완전 탐색
			for (int i = 0; i < n; i++) {
				// 확인할 필요도 없음
				if (heights[i] == 0) {
					continue;
				}
				
				int max = 0;
				List<Integer> num_list = new ArrayList<Integer>(); 
				
				for (int j = 0; j < 4; j++) {
					num_list.add(heights[i + dx[j]]);
				}
				
				max = find_max(num_list);
				
				if (max < heights[i]) {
					result += heights[i] - max;
				}
			}
			
			bw.write("#" + test_case + " " + result + "\n");
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
	
	public static int find_max(List<Integer> nums) {
		int max = 0;
		
		for (int i = 0; i < nums.size(); i++) {
			if (max < nums.get(i)) {
				max = nums.get(i);
			}
		}
		
		return max;
	}
}
