import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class BOJ_2668 {

	static int N;
	static int[] a, b;
	static boolean[] visited;
	static ArrayList<Integer> result;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		result = new ArrayList<>();
		a = new int[N+1];
		b = new int[N+1];

		//a 배열 초기화
		for(int i = 1; i <= N; i++) {
			a[i] = i;
		}

		//b 배열 입력
		for(int i = 1; i <= N; i++) {
			int tmp = Integer.parseInt(br.readLine());
			b[i] = tmp;
		}

		for(int i = 1; i <= N; i++) {
			visited = new boolean[N+1];
			if(dfs(i, i)) {
				result.add(i);
			}
		}

		Collections.sort(result);
		System.out.println(result.size());
		for(int x : result) {
			System.out.println(x);
		}
	}

	private static boolean dfs(int start, int cur) {
		if(!visited[cur]) {
			visited[cur] = true;
			return dfs(start, b[cur]);
		}
		return start == cur;
	}
}