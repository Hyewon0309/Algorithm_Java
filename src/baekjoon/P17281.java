package baekjoon;

import java.io.*;
import java.util.*;

public class P17281 {
	// Member Variable
	static int[][] pred;
	static Deque<Integer> players;
	static List<Integer> scores;
	static boolean[] visited;
	static int n;

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		// 변수 선언 및 초기화
		n = Integer.parseInt(br.readLine());
		pred = new int[n][9];
		players = new ArrayDeque<Integer>();
		scores = new ArrayList<Integer>();
		visited = new boolean[9];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				pred[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 알고리즘
		permutation();
		Collections.sort(scores);
		
		bw.write(scores.get(scores.size() - 1) + "\n");

		br.close();
		bw.close();
	}

	// 가능한 모든 조합 구하기
	static void permutation() {
		// 타순 다 정해지면 시뮬레이션
		if (players.size() == 8) {
			// 시뮬레이션
			play();
			return;
		} else { // 타순 덜 정해졌으면 계속 돌기
			for (int i = 1; i < 9; i++) {
				if (!visited[i]) {
					visited[i] = true;
					players.addLast(i);
					permutation();
					players.pollLast();
					visited[i] = false;
				}
			}
		}
	}

	// 시뮬레이션
	static void play() {
		int score = 0;
		Deque<Integer> temp = new ArrayDeque<Integer>();
		
		for (int i = 0; i < 9; i++) {
			if (i == 3) {
				temp.add(0);
			} else {
				int t = players.pollFirst();
				players.addLast(t);
				temp.add(t);
			}
		}
		
		// n이닝까지 실행
		for (int i = 0; i < n; i++) {
			int out = 0;
			int first = 0;
			int second = 0;
			int third = 0;

			while (out < 3) {
				int cur = temp.pollFirst();
				temp.addLast(cur);

				int res = pred[i][cur];

				switch (res) {
				case 0:
					out++;
					break;
				case 4: // 홈런
					score += first + second + third + 1;
					first = 0;
					second = 0;
					third = 0;
					break;
				case 3: // 3루타
					score += first + second + third;
					third = 1;
					second = 0;
					first = 0;
					break;
				case 2: // 2루타
					score += second + third;
					third = first;
					second = 1;
					first = 0;
					break;
				case 1: // 안타
					score += third;
					third = second;
					second = first;
					first = 1;
					break;
				}
			}
		}
		scores.add(score);
	}
}