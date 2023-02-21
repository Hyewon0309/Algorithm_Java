package swea;

import java.io.*;
import java.util.*;

public class P1218_swea {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			br.readLine();
			char[] chars = br.readLine().toCharArray();
			Deque<Character> blankets = new ArrayDeque<Character>();
			
			int result = 1;
			
			for (char c : chars) {
				switch (c) {
				case ']':
					if (blankets.pollLast() != '[') {
						result = 0;
					}
					break;
				case '}':
					if (blankets.pollLast() != '{') {
						result = 0;
					}
					break;
				case '>':
					if (blankets.pollLast() != '<') {
						result = 0;
					}
					break;
				case ')':
					if (blankets.pollLast() != '(') {
						result = 0;
					}
					break;
				default:
					blankets.addLast(c);
					break;
				}
				
				if (result == 0) {
					break;
				}
			}
			
			bw.write("#" + test_case + " " + result + "\n");
		}
		
		br.close();
		bw.close();
	}
}
