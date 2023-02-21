package baekjoon;

import java.io.*;
import java.util.*;

public class P1244 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[] switches = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int num_switch = Integer.parseInt(st.nextToken());
			
			// 남자
			if (sex == 1) {
				int product_idx = 1;
				int temp;
				while ((temp = num_switch * product_idx++) <= n) {
					switches[temp - 1] = (switches[temp - 1] + 1) % 2;
				}
			} else { // 여자
				int right = num_switch;
				int left = num_switch - 2;
				switches[num_switch - 1] = (switches[num_switch - 1] + 1) % 2;
				
				while (right < n && left >= 0) {
					if (switches[right] == switches[left]) {
						switches[right] = (switches[right++] + 1) % 2;
						switches[left] = (switches[left--] + 1) % 2;
					} else {
						break;
					}
				}
			}
		}
		
		for (int i = 0; i <= n / 20; i++) {
			if (i == n / 20) {
				for (int j = i * 20; j < n; j++) {
					bw.write(switches[j] + " ");
				}
			} else {
				for (int j = 0; j < 20; j++) {
					bw.write(switches[i * 20 + j] + " ");
				}
			}
			bw.write("\n");
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
}
