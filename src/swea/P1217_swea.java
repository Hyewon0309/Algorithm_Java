package swea;

import java.io.*;
import java.util.*;

public class P1217_swea {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			br.readLine();
			String[] nums = br.readLine().split(" ");
			bw.write("#" + test_case + " " + pow(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])) + "\n");
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
	
	// a^n
	public static int pow (int a, int n) {
		if (n == 1) {
			return a;
		} else {
			return a * pow(a, n-1);
		}
	}
}
