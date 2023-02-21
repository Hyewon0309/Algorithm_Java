package swea;

import java.io.*;
import java.util.*;

public class P1220_swea {
	static int[] dx = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 0일 때는 그대로, 1일 때는 s극에 이끌려 한 줄 밑으로 2일 때는 n극에 이끌려 한 줄 위로

		for (int test_case = 1; test_case <= 10; test_case++) {
			int n = Integer.parseInt(br.readLine());
			int[][] magnetics = new int[n][n];
			int result = 0;

			StringTokenizer st = null;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					magnetics[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 알고리즘
			for (int i = 0; i < n; i++) {
				result += cal(magnetics[i]);
			}

			bw.write("#" + test_case + " " + result + "\n");
		}

		br.close();
		bw.close();
	}

	public static int cal(int[] nums) {
		int result = 0;

		List<Integer> polarN = new ArrayList<Integer>();
		List<Integer> polarS = new ArrayList<Integer>();

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 1) {
				polarN.add(i);
			} else if (nums[i] == 2) {
				polarS.add(i);
			}
		}

		if (polarN.size() == 0 || polarS.size() == 0) {
			return 0;
		} else {
			boolean isMove = true;
			while (isMove) {
				isMove = false;

				List<Integer> next_polarN = new ArrayList<Integer>();
				List<Integer> next_polarS = new ArrayList<Integer>();

				// N극 이동
				for (int i = 0; i < polarN.size(); i++) {
					int next = polarN.get(i) + 1;
					// 판 밖으로 떨어지는 경우
					if (next == 100) {
						continue;
					} else {
						if (polarS.contains(next) || polarN.contains(next)) {
							next_polarN.add(polarN.get(i));
							continue;
						} else {
							next_polarN.add(next);
							isMove = true;
						}
					}
				}

				polarN = next_polarN;

				// S극 이동
				for (int i = 0; i < polarS.size(); i++) {
					int next = polarS.get(i) - 1;
					// 판 밖으로 떨어지는 경우
					if (next == -1) {
						continue;
					} else {
						if (polarS.contains(next) || polarN.contains(next)) {
							next_polarS.add(polarS.get(i));
							continue;
						} else {
							next_polarS.add(next);
							isMove = true;
						}
					}
				}
				polarS = next_polarS;
			}
		}

		// 총 몇 번의 충돌이 있었는지 확인
		for (int i = 0; i < polarN.size(); i++) {
			if (polarS.contains(polarN.get(i) + 1)) {
				result += 1;
			}
		}

		return result;
	}
}
