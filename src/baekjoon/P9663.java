package baekjoon;

import java.io.*;
import java.util.*;

public class P9663 {
	// Member Variable
	static int n;
	static int[] rows;
	static int count;

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 변수 선언 및 초기화
		n = Integer.parseInt(br.readLine());
		count = 0;
		rows = new int[n];
		
		// 알고리즘
		nQueen(0);

		bw.write(count + "\n");
		bw.flush();

		br.close();
		bw.close();
	}

	// Check Possible column at row x
	static void nQueen(int x) {
		if (x == n) {
			count++;
			return;
		}
		
		for (int i = 0; i < n; i++) {
			boolean flag = false;
			
			// 세로 확인
			for (int j = 0; j < x; j++) {
				if (rows[j] == i) {
					flag = true;
					break;
				}
			}
			
			if (flag) {
				continue;
			}
			
			// 대각선 확인
			int d = x;
			flag = false;

			for (int j = 0; j < x; j++) {
				if (rows[j] + d == i || rows[j] - d == i) {
					flag = true;
					break;
				}
				d--;
			}
			
			if (flag) { // 대각선에 퀸 존재
				continue;
			} else { // 가능한 위치
				rows[x] = i;
				nQueen(x+1);
			}
		}
	}
}
	
