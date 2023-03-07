package baekjoon;

import java.io.*;
import java.util.*;

public class P13458 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		
		int n = Integer.parseInt(br.readLine());
		int[] students = new int[n];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			students[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		long result = 0;
		
		for (int i = 0; i < n; i++) {
			// 주 감독관
			students[i] = Integer.max(students[i] - b, 0);
			result++;
			
			// 부감독관
			if (students[i] != 0) {
				if (students[i] % c == 0) {
					result += students[i] / c;
				} else {
					result += students[i] / c + 1;
				}
			}
		}
		
		bw.write(result + "\n");
		bw.flush();
		
		br.close();
		bw.close();
	}
}
