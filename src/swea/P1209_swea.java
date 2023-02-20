package swea;

import java.io.*;
import java.util.*;

public class P1209_swea {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int test_case = 1; test_case <= 10; test_case++) {
			br.readLine();
			int[][] nums = new int[100][100];
			
			StringTokenizer st = null;
			
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 100; j++) {
					nums[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int max = -1;
			int sum_diag1 = 0;
			int sum_diag2 = 0;

			for (int i = 0; i < 100; i++) {
				int sum_r = 0;
				int sum_c = 0;
				sum_diag1 += nums[i][i];
				sum_diag2 += nums[i][100 - i - 1];
				for (int j = 0; j < 100; j++) {
					sum_r += nums[i][j];
					sum_c += nums[j][i];
				}

				if (sum_r >= max) {
					max = sum_r;
				}

				if (sum_c >= max) {
					max = sum_c;
				}
			}

			if (sum_diag1 >= max) {
				max = sum_diag1;
			}

			if (sum_diag2 >= max) {
				max = sum_diag2;
			}
			
			bw.write("#" + test_case + " " + max + "\n");
		}

		br.close();
		bw.close();
	}
}
