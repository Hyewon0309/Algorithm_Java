package swea;

import java.io.*;
import java.util.*;

public class P1208_swea {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int dump_count = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int[] heights = new int[100];
			
			for (int i = 0; i < 100; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < dump_count; i++) {
				Arrays.sort(heights);
				heights[0]++;
				heights[99]--;
			}
			
			Arrays.sort(heights);
			
			bw.write("#" + test_case + " " + (heights[99] - heights[0]) + "\n");
		}
		
		br.close();
		bw.close();
	}
}
