package swea;

import java.io.*;
import java.util.*;

public class P1225_swea {
	static Deque<Integer> q;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= 10; test_case++) {
			// test_case 입력 확인
			if (test_case != Integer.parseInt(br.readLine())) {
				System.err.println("Input Error");
			}
			
			// 변수 선언 및 초기화
			q = new ArrayDeque<Integer>();
			st = new StringTokenizer(br.readLine(), " ");
			flag = false;
			for (int i = 0; i < 8; i++) {
				q.addLast(Integer.parseInt(st.nextToken()));
			}
			sb.append("#" + test_case + " ");
			
			// 알고리즘
			while (!flag) {
				cycle();
			}
			
			while (!q.isEmpty()) {
				sb.append(q.pollFirst() + " ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}

	public static void cycle() {
		for (int cnt = 1; cnt <= 5; cnt++) {
			int temp = q.pollFirst();
			if (temp - cnt <= 0) {
				q.addLast(0);
				flag = true;
				break;
			} else {
				q.addLast(temp - cnt);
			}
		}
	}
}
