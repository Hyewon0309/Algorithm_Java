package swea;

import java.io.*;
import java.util.*;

public class P1952_swea {
	// Member Variable
	static StringBuilder sb;
	static int[] price;
	static int[] plan;
	static int[] memory;

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			// 변수 선언 및 초기화
			price = new int[4];
			plan = new int[12];
			memory = new int[12];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}

			// 알고리즘
			int result = cal(0);
			result = Math.min(result, price[3]);

			// 출력
			sb.append("#" + test_case + " " + result + "\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static int cal(int m) {
		int result = 0;

		if (m >= 12) {
			return 0;
		}

		// 1일권 계산
		result = price[0] * plan[m];

		// 1달권 계산
		result = Math.min(result, price[1]);

		if (m + 1 < 12 && memory[m + 1] != 0) {
			result += memory[m + 1];
		} else {
			result += cal(m + 1);
		}

		// 3달권 계산
		int three_month = price[2];

		if (m + 3 < 12 && memory[m + 3] != 0) {
			three_month += memory[m + 3];
		} else {
			three_month += cal(m + 3);
		}

		result = Math.min(result, three_month);

		memory[m] = result;

		return result;
	}
}
