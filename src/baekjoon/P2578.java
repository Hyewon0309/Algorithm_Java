package baekjoon;

import java.io.*;
import java.util.*;

public class P2578 {
	static int[][] map;
	static int[][] isChecked = new int[5][5];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		map = new int[5][5];
		int[] nums = new int[25];
		
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				nums[i * 5  + j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		int bingo = 0;
		// Algorithm
		for (int i = 0; i < 25; i++) {
			bingo += checkBingo(checkNum(nums[i]));
			if (bingo >= 3) {
				result = i + 1;
				break;
			}
		}
		
		// Output
		bw.write(result + "\n");
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	// 빙고판 체크
	public static int checkNum(int num) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (map[i][j] == num) {
					isChecked[i][j] = 1;
					return i * 5 + j;
				}
			}
		}
		return -1;
	}
	
	// 빙고 개수 확인
	public static int checkBingo(int num) {
		int result = 2;
		int x = num / 5;
		int y = num % 5;
		
		// 가로 체크
		for (int i = 0; i < 5; i++) {
			if (isChecked[x][i] == 0) {
				result--;
				break;
			}
		}
		
		// 세로 체크
		for (int i = 0; i < 5; i++) {
			if (isChecked[i][y] == 0) {
				result--;
				break;
			}
		}
		
		// 대각선 체크
		if (x == y) {
			result++;
			for (int i = 0; i < 5; i++) {
				if (isChecked[i][i] == 0) {
					result--;
					break;
				}
			}
		}
		
		if (x + y == 4) {
			result++;
			for (int i = 0; i < 5; i++) {
				if (isChecked[i][4-i] == 0) {
					result--;
					break;
				}
			}
		}
		
		return result;
	}
}
