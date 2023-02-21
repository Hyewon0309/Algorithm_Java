package swea;

import java.io.*;
import java.util.*;

public class P1215_swea {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int str_len = Integer.parseInt(br.readLine());
			char[][] chars = new char[8][8];
			
			int result = 0;
			
			for (int i = 0; i < 8; i++) {
				chars[i] = br.readLine().toCharArray();
			}
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8 - str_len + 1; j++) {
					String str_r = "";
					String str_c = "";
					
					for (int k = 0; k < str_len; k++) {
						str_r += chars[i][j+k];
						str_c += chars[j+k][i];
					}
					
					result += checkPalindrome(str_r);
					result += checkPalindrome(str_c);
				}
			}
			
			bw.write("#" + test_case + " " + result + "\n");
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
	
	public static int checkPalindrome(String target) {
		for (int i = 0; i < target.length() / 2; i++) {
			if (target.charAt(i) == target.charAt(target.length() - i - 1)) {
				continue;
			} else {
				return 0;
			}
		}
		return 1;
	}
}
