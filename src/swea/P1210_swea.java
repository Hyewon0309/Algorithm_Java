package swea;

import java.io.*;
import java.util.*;

public class P1210_swea {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		

		for (int test_case=1; test_case <= 10; test_case++) {
			// 변수 선언
			int[][] map = new int[100][100];
			StringTokenizer st = null;
			int cur_x = 99;
			int cur_y = -1;
			int UP = 0;
			int RIGHT = 1;
			int LEFT = 2;
			int dir = UP;
			
			// 입력
			br.readLine();
			
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					// 도착지점 찾기
					if (i == 99) {
						if (map[i][j] == 2) {
							cur_y = j;
						}
					}
				}
			}
			
			// 알고리즘
			while (cur_x != 0) {
				// 현재 위로 가는 중이었는지 체크
				if (dir == UP) {
					// 옆으로 가기
					if (cur_y - 1 >= 0 && map[cur_x][cur_y - 1] == 1) {
						cur_y--;
						dir = LEFT;
					} else if (cur_y + 1 <= 99 && map[cur_x][cur_y + 1] == 1) {
						cur_y++;
						dir = RIGHT;
					} else {
						cur_x--;
					}
				} else if (dir == RIGHT) {
					if (map[cur_x - 1][cur_y] == 1) {
						cur_x--;
						dir = UP;
					} else {
						cur_y++;
					}
				} else {
					if (map[cur_x - 1][cur_y] == 1) {
						cur_x--;
						dir = UP;
					} else {
						cur_y--;
					}
				}
			}
			
			// 출력
			bw.write("#" + test_case + " " + cur_y + "\n");
			bw.flush();
		}
		
		
		br.close();
		bw.close();
	}
}
