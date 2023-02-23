package swea;

import java.io.*;
import java.util.*;

public class P1224_swea {
	static Deque<Character> q;
	static Deque<Integer> num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int test_case = 1; test_case <= 10; test_case++) {
			// 변수 선언 및 초기화
			int n = Integer.parseInt(br.readLine());
			char[] raw = br.readLine().toCharArray();
			q = new ArrayDeque<Character>();
			num = new ArrayDeque<Integer>();

			if (n != raw.length) {
				System.err.println("Input Process Error!");
			}

			int result = cal(toPostOrder(raw));
			
			bw.write("#" + test_case + " " + result + "\n");
			bw.flush();
		}

		br.close();
		bw.close();
	}

	public static char[] toPostOrder(char[] raw) {
		StringBuilder sb = new StringBuilder();
		char top;

		for (char c : raw) {
			if (c >= '0' && c <= '9') {
				sb.append(c);
			} else {
				if (c == '(') {
					q.addLast(c);
				} else if (c == ')') {
					while ((top = q.pollLast()) != '(') {
						sb.append(top);
					}
				} else if (c == '*') {
					if (q.peekLast() == '*') {
						sb.append(c);
					} else {
						q.addLast(c);
					}
				} else if (c == '+') {
					if (q.isEmpty()) {
						q.addLast(c);
					} else {
						while ((top = q.peekLast()) == '*') {
							sb.append(q.pollLast());
						}
						if (q.peekLast() == c) {
							sb.append(c);
						} else {
							q.addLast(c);
						}
					}
				} else {
					System.err.println("PostOrder Input Error!");
				}
			}
		}

		while (!q.isEmpty()) {
			sb.append(q.pollLast());
		}

		return sb.toString().toCharArray();
	}
	
	public static int cal(char[] PostOrder) {
		for (char c : PostOrder) {
			if (c >= '0' && c <= '9') {
				num.addLast(c - '0');
			} else {
				int first = num.pollLast();
				int second = num.pollLast();
				if (c == '+') {
					num.addLast(first + second);
				} else {
					num.addLast(first * second);
				}
			}
		}
		
		return num.pollLast();
	}
}
