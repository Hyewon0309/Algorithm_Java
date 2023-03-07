package baekjoon;

import java.io.*;
import java.util.*;

public class P15685 {
	static boolean[][] map;
	
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		// 변수 선언 및 초기화
		int n = Integer.parseInt(br.readLine());
		Curve[] dc = new Curve[n];
		map = new boolean[101][101];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			dc[i] = new Curve(x, y, d, g);
		}
		
		// 알고리즘
		for (int i = 0; i < n; i++) {
			dc[i].draw();
		}
		
		// 개수 세기
		
		br.close();
		bw.close();
	}
	
	static class Curve {
		int x, y, g, d;
		
		Curve(int x, int y, int g, int d) {
			this.x = x;
			this.y = y;
			this.g = g;
			this.d = d;
		}
		
		void draw() {
			
		}
	}
}
