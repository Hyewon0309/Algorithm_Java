package swea;

import java.io.*;
import java.util.*;

public class P1216_swea {
	static char[][] chars = new char[100][100];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int test_case = 1; test_case <= 10; test_case++) {
			br.readLine();

			for (int i = 0; i < 100; i++) {
				chars[i] = br.readLine().toCharArray();
			}

			int max = 0;

			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					int temp1 = cal_palindrome_odd(i, j);
					int temp2 = cal_palindrome_even(i, j);
					max = max < temp1 ? temp1 : max;
					max = max < temp2 ? temp2 : max;
				}
			}
			
			bw.write("#" + test_case + " " + max + "\n");
			bw.flush();
		}

		br.close();
		bw.close();
	}

	public static int cal_palindrome_odd(int x, int y) {
		int result_x = 1;

		int result_y = 1;

		int right_x = x + 1;
		int left_x = x - 1;
		int up_y = y - 1;
		int down_y = y + 1;

		// 가로 탐색
		while (right_x < 100 && left_x >= 0) {
			if (chars[right_x++][y] == chars[left_x--][y]) {
				result_x += 2;
			} else {
				break;
			}
		}

		// 세로 탐색
		while (up_y >= 0 && down_y < 100) {
			if (chars[x][up_y--] == chars[x][down_y++]) {
				result_y += 2;
			} else {
				break;
			}
		}

		return result_x > result_y ? result_x : result_y;
	}
	
	public static int cal_palindrome_even(int x, int y) {
		int result_x = 0;

		int result_y = 0;

		int right_x = x;
		int left_x = x - 1;
		int up_y = y;
		int down_y = y + 1;

		// 가로 탐색
		while (right_x < 100 && left_x >= 0) {
			if (chars[right_x++][y] == chars[left_x--][y]) {
				result_x += 2;
			} else {
				break;
			}
		}

		// 세로 탐색
		while (up_y >= 0 && down_y < 100) {
			if (chars[x][up_y--] == chars[x][down_y++]) {
				result_y += 2;
			} else {
				break;
			}
		}

		return result_x > result_y ? result_x : result_y;
	}
}
