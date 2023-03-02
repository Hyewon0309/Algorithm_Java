package baekjoon;

import java.io.*;
import java.util.*;

public class P15654 {
	static int n;
	static int m;
	static int[] nums;
	static StringBuilder sb;
	static int[] list;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		nums = new int[n];
		visited = new boolean[n];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		list = new int[m];

		Arrays.sort(nums);

		find(0, 0);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static void find(int s, int d) {
		if (d == m) {
			for (int i : list) {
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			return;
		} else {
			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					visited[i] = true;
					list[d] = i;
					find(s + 1, d + 1);
					visited[i] = false;
				}
			}
		}
	}
}
