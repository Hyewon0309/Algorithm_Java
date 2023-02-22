package baekjoon;

import java.io.*;
import java.util.*;

public class P2628 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		
		List<Integer> w = new ArrayList<Integer>();
		List<Integer> h = new ArrayList<Integer>();
		
		int n = Integer.parseInt(br.readLine());
		
		w.add(0);
		h.add(0);
		w.add(width);
		h.add(height);
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken());
			int line = Integer.parseInt(st.nextToken());
			
			// 가로
			if (dir == 0) {
				h.add(line);
			}
			// 세로
			if (dir == 1) {
				w.add(line);
			}
		}

		Collections.sort(w);
		Collections.sort(h);
		
		int max = -1;
		
		for (int i = 0; i < w.size() - 1; i++) {
			for (int j = 0; j < h.size() - 1; j++) {
				int temp = (w.get(i+1) - w.get(i)) * (h.get(j+1) - h.get(j));
				max = max < temp ? temp : max;
			}
		}
		
		bw.write(max + "\n");
		
		br.close();
		bw.close();
	}
}
