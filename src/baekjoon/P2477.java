package baekjoon;

import java.io.*;
import java.util.*;

public class P2477 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		
		int[] x = new int[3];
		int[] y = new int[3];
		int[] dir = new int[5];
		int cur_x = 0;
		int cur_y = 0;
		int idx_x = 0;
		int idx_y = 0;
		
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int type = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			
			dir[type]++;
			
			switch (type) {
			case 1:
				cur_x += length;
				x[idx_x++] = cur_x;
				break;
			case 2:
				cur_x -= length;
				x[idx_x++] = cur_x;
				break;
			case 3:
				cur_y -= length;
				y[idx_y++] = cur_y;
				break;
			case 4:
				cur_y += length;
				y[idx_y++] = cur_y;
				break;
			}
		}
		
		Arrays.sort(x);
		Arrays.sort(y);
		
		// 큰 사각형 넓이
		int result = (x[2] - x[0]) * (y[2] - y[0]);
		int area = 0;
		
		int w = 0;
		int h = 0;
		
		// 작은 사각형 넓이 구하기
		if (dir[1] + dir[3] == 4) {
			area = (x[1] - x[0]) * (y[1] - y[0]);
		} else if (dir[1] + dir[4] == 4) {
			area = (x[2] - x[1]) * (y[1] - y[0]);
		} else if (dir[2] + dir[3] == 4) {
			area = (x[1] - x[0]) * (y[2] - y[1]);
		} else if (dir[2] + dir[4] == 4){
			area = (x[2] - x[1]) * (y[2] - y[1]);
		}
		
		bw.write(((result - area) * n)  + "\n");
		bw.flush();
		
		br.close();
		bw.close();
	}
}
