package swea;

import java.io.*;
import java.util.*;

public class P5644_swea {
	// Member Variable
	static StringBuilder sb;
	static BC[] chargers;

	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			// 변수 선언 및 초기화
			st = new StringTokenizer(br.readLine(), " ");
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			int[] a = new int[m + 1];
			int[] b = new int[m + 1];

			a[0] = 0;

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= m; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}

			b[0] = 0;

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= m; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}

			chargers = new BC[n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				chargers[i] = new BC(y - 1, x - 1, c, p);
			}

			// 알고리즘
			int a_x = 0;
			int a_y = 0;
			int b_x = 9;
			int b_y = 9;

			int result = 0;

			for (int i = 0; i <= m; i++) {
				a_x += dx[a[i]];
				a_y += dy[a[i]];
				b_x += dx[b[i]];
				b_y += dy[b[i]];
		
				int[] a_list = new int[n];
				int[] b_list = new int[n];

				int a_cnt = 0;
				int b_cnt = 0;

				// 배터리 체크
				for (int c = 0; c < n; c++) {
					BC bc = chargers[c];
					if (dist(a_x, a_y, bc) <= bc.C) {
						a_list[a_cnt++] = c;
					}
					
					if (dist(b_x, b_y, bc) <= bc.C) {
						b_list[b_cnt++] = c;
					}
				}

				int charge = 0;
				
				if (a_cnt != 0 && b_cnt != 0) {
					for (int a_idx = 0; a_idx < a_cnt; a_idx++) {
						for (int b_idx = 0; b_idx < b_cnt; b_idx++) {
							if (a_list[a_idx] == b_list[b_idx]) {
								charge = Math.max(charge, chargers[a_list[a_idx]].P);
							} else {
								charge = Math.max(charge, chargers[a_list[a_idx]].P + chargers[b_list[b_idx]].P);
							}
						}
					}
				} else if (a_cnt == 0) {
					for (int b_idx = 0; b_idx < b_cnt; b_idx++) {
						charge = Math.max(charge, chargers[b_list[b_idx]].P);
					}
				} else {
					for (int a_idx = 0; a_idx < a_cnt; a_idx++) {
						charge = Math.max(charge, chargers[a_list[a_idx]].P);
					}
				}
				
				result += charge;
			}
			
			sb.append("#" + test_case + " " + result + "\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	// calculate distance
	static int dist(int x, int y, BC bc) {
		return Math.abs(x - bc.x) + Math.abs(y - bc.y);
	}

	// Battery Charger class
	static class BC {
		int x, y, C, P;

		BC(int x, int y, int C, int P) {
			this.x = x;
			this.y = y;
			this.C = C;
			this.P = P;
		}
	}
}
