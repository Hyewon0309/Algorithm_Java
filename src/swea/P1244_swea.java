package swea;

import java.io.*;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.Map.Entry;

public class P1244_swea {
	static char[] nums;
	static int sort_idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		for (int test_case = 1; test_case <= T; test_case++) {
			sort_idx = 0;
			st = new StringTokenizer(br.readLine());
			nums = st.nextToken().toCharArray();
			List<Character> sorted_nums = new ArrayList<Character>();

			for (int i = 0; i < nums.length; i++) {
				sorted_nums.add(nums[i]);
			}

			Collections.sort(sorted_nums, Collections.reverseOrder());

			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == sorted_nums.get(i)) {
					sort_idx++;
				} else {
					break;
				}
			}

			int cnt = Integer.parseInt(st.nextToken());

			solve(cnt);

			String result = "";
			for (int i = 0; i < nums.length; i++) {
				result += nums[i];
			}

			bw.write("#" + test_case + " " + result + "\n");
		}

		br.close();
		bw.close();
	}

	public static void solve(int cnt) {
		if (cnt == 0) {
			return;
		}

		// 정렬이 끝난 상태
		if (sort_idx == nums.length) {
			if (cnt % 2 == 0) {
				return;
			} else {
				for (int i = 0; i < nums.length - 1; i++) {
					if (nums[i] == nums[i + 1]) {
						return;
					}
				}
				swap(nums.length - 1, nums.length - 2);
				return;
			}
		}

		List<Integer> max_idx = new ArrayList<Integer>();
		int max = -1;

		// 정렬해야할 때
		// max 구하기
		for (int i = sort_idx; i < nums.length; i++) {
			if (nums[i] - '0' > max) {
				max = nums[i] - '0';
				max_idx.clear();
				max_idx.add(i);
			} else if (nums[i] - '0' == max) {
				max_idx.add(i);
			}
		}

		if (max_idx.size() >= 2) {
			if (cnt == 1) {
				swap(max_idx.get(max_idx.size() - 1), sort_idx++);
				return;
			} else {
				Map<Character, Integer> change_target = new TreeMap<>();
				int change_num = max_idx.size();
				
				change_num = change_num > max_idx.get(0) - sort_idx? max_idx.get(0) - sort_idx : change_num;
				
				change_num = change_num >= cnt ? cnt : change_num;
				
				if (change_num == 0) {
					solve(cnt - max_idx.size());
					return;
				}

				for (int i = 0; i < change_num; i++) {
					change_target.put(nums[sort_idx + i], sort_idx + i);
				}

				int c = 0;
				for (Entry<Character, Integer> e : change_target.entrySet()) {
					swap(e.getValue(), max_idx.get(max_idx.size() - 1 - c));
					c++;
				}
				solve(cnt - change_num);
			}
		} else {
			if (max_idx.get(0) == sort_idx) {
				sort_idx++;
				solve(cnt);
			} else {
				swap(max_idx.get(0), sort_idx++);
				solve(cnt - 1);
			}
		}
	}

	// 값 두 개 변환
	public static void swap(int a, int b) {
		char temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}
}
