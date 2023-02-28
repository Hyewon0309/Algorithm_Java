package baekjoon;

import java.io.*;
import java.util.*;

public class P17471 {
	// Member Variable
	static int[] population;
	static ArrayList<ArrayList<Integer>> graph;
	static ArrayList<Integer> region1;
	static ArrayList<Integer> region2;
	static Deque<Integer> q;

	// Main Method
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		// 변수 선언 및 초기화
		int n = Integer.parseInt(br.readLine());
		int count_zero = 0;
		int idx_zero = -1;
		population = new int[n + 1];
		region1 = new ArrayList<Integer>();
		region2 = new ArrayList<Integer>();
		q = new ArrayDeque<Integer>();
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			if (num == 0) {
				count_zero++;
				idx_zero = i;
			}
			while (st.hasMoreTokens()) {
				graph.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 알고리즘
		if (count_zero >= 3) { // 인접한 구역이 없는 구역이 3개 이상 존재 => 2개로 나눌 수 없음
			bw.write("-1\n");
		} else if (count_zero == 2) { // 인접한 구역이 없는 구역이 2개 존재 => 구역이 총 2개가 아닌 이상 나눌 수 없음
			if (n == 2) { // 총 2개인 경우는 각각이 선거구가 되어 인구 차이의 최솟값 구할 수 있음
				bw.write(Math.abs(population[2] - population[1]) + "\n");
			} else {
				bw.write("-1\n");
			}
		} else if (count_zero == 1) { // 인접한 구역이 없는 구역 하나가 선거구 하나가 되고 (idx_zero), 나머지들이 하나의 선거구가 됨
			for (int i = 1; i <= n; i++) {
				if (i != idx_zero) {
					region1.add(i);
				}
			}
			
			int sum = check(region1);
			
			if (sum == -1) {
				bw.write("-1\n");
			} else {
				bw.write(Math.abs(sum - population[idx_zero]) + "\n");
			}
		} else { // 모든 조합을 찾아서 최소값 찾아내기
			int min = Integer.MAX_VALUE;
			boolean flag = false;
			
			// 모든 조합 찾기
			for (int i = 1; i < Math.pow(2, n - 1); i++) {
				int temp = i;
				region1.clear();
				region2.clear();
				for (int j = 1; j <= n; j++) {
					int t = temp % 2;
					if (t == 0) {
						region1.add(j);
					} else {
						region2.add(j);
					}
					temp /= 2;
				}
				
				// 각 조합 확인
				int sum1 = check(region1);
				int sum2 = check(region2);
				
				if (sum1 != -1 & sum2 != -1) {
					flag = true;
					int result = 0;
					if (sum1 >= sum2) {
						result = sum1 - sum2;
					} else {
						result = sum2 - sum1;
					}

					min = min > result ? result : min;
				}
			}
			
			if (flag) {
				bw.write(min + "\n");
			} else {
				bw.write("-1\n");
			}
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	// 선거구들이 다 이어져있는지 확인
	// 이어져있지 않으면 -1 반환
	// 이어져있으면 인구수 합 반환
	static int check(List<Integer> temp) {
		List<Integer> list = new ArrayList<Integer>(temp);
		int result = 0;
		
		int start = list.get(0);
		q.addLast(start);
		list.remove((Integer) start);
		
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int cur = q.pollFirst();
				result += population[cur];
				for (int next : graph.get(cur)) {
					if (list.contains(next)) {
						list.remove((Integer) next);
						q.addLast(next);
					}
				}
			}
		}
		
		if (list.isEmpty()) {
			return result;
		} else {
			return -1;
		}
	}
}
