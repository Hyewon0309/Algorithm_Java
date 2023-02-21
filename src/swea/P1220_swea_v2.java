package swea;

import java.io.*;
import java.util.*;

public class P1220_swea_v2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = 0;
			
			for (int i = 0; i < n; i++) {
				int cur = 0;
				for (int j = 0; j < n; j++) {
					if (map[j][i] == 1) {
						cur = 1;
					} else if (map[j][i] == 2) {
						if (cur == 1) {
							result++;
						}
						cur = 2;
					}
				}
			}
			
			bw.write("#" + test_case + " " + result + "\n");
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
}
