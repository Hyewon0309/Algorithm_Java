package baekjoon;

import java.io.*;
import java.util.*;

public class P14501 {
	static int[] time;
	static int[] price;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		n = Integer.parseInt(br.readLine());

		time = new int[n];
		price = new int[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}

		int result = find(0);

		bw.write(result + "\n");

		br.close();
		bw.close();
	}

	static int find(int d) {
		int result = 0;

		if (d >= n) {
			return 0;
		} else {
			if (d + time[d] <= n) {
				result = Math.max(price[d] + find(d + time[d]), result);
			}
			result = Math.max(result, find(d + 1));
		}

		return result;
	}
}
