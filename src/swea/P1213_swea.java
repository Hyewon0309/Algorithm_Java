package swea;

import java.io.*;
import java.util.*;

public class P1213_swea {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			br.readLine();
			String target = br.readLine();
			String str = br.readLine();
			
			int result = 0;
			int idx;
			while ((idx = str.indexOf(target)) != -1) {
				result++;
				str = str.substring(idx + target.length() - 1);
			}
			
			bw.write("#" + test_case + " " + result + "\n");
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
}
