package swea;

import java.io.*;
import java.util.*;

public class P1204_swea {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case=1; test_case <= T; test_case++) {
			// test_case 숫자 건너뛰기
			br.readLine();
			
			// 0~100 빈도 수 세기 위해 선언
			int[] counts = new int[101];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for (int i = 0; i < 1000; i ++) {
				counts[Integer.parseInt(st.nextToken())]++;
			}
			
			int max = -1;
			int max_cnt = 0;
			
			for (int i = 0; i < 101; i++) {
				if (counts[i] >= max_cnt) {
					max = i;
					max_cnt = counts[i];
				}
			}
			
			bw.write("#" + test_case + " " + max + "\n");
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
}