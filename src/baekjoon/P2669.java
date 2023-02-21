package baekjoon;

import java.io.*;
import java.util.*;

public class P2669 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int[][] map = new int[101][101];

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x_bottom = Integer.parseInt(st.nextToken());
			int y_bottom = Integer.parseInt(st.nextToken());
			int x_top = Integer.parseInt(st.nextToken());
			int y_top = Integer.parseInt(st.nextToken());

			for (int x = x_bottom; x < x_top; x++) {
				for (int y = y_bottom; y < y_top; y++) {
					map[x][y] = 1;
				}
			}
		}

		int result = 0;

		for (int x = 0; x < 101; x++) {
			for (int y = 0; y < 101; y++) {
				result += map[x][y];
			}
		}

		bw.write(result + "\n");
		bw.flush();

		br.close();
		bw.close();
	}
}
